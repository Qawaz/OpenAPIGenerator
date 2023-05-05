package com.wakaztahir.openapi

import com.reprezen.jsonoverlay.JsonOverlay
import com.reprezen.jsonoverlay.Overlay
import com.reprezen.kaizen.oasparser.model3.Schema
import com.wakaztahir.kate.dsl.ModelObjectImpl
import com.wakaztahir.kate.model.*
import com.wakaztahir.kate.model.model.*

fun Schema.toKATEValue(allowNested: Boolean): KATEValue {
    return when (this.getType()) {
        "string" -> {
            StringValue("")
        }

        "number" -> {
            DoubleValue(0.0)
        }

        "integer" -> {
            if (this.getFormat() == "int64") {
                LongValue(0)
            } else {
                IntValue(0)
            }
        }

        "boolean" -> {
            BooleanValue(false)
        }

        "array" -> {
            KATEListImpl(
                listOf(this.getItemsSchema()!!.toKATEValue(allowNested = allowNested)),
                itemType = KATEType.Any
            )
        }

        "object" -> {
            toMutableKATEObject(allowNested = allowNested)
        }

        else -> {
            println((this as JsonOverlay<*>)._getPathFromRoot())
            throw IllegalArgumentException("unknown openapi type ${this.getType()} for key ${this.getName()}")
        }
    }
}

private fun String.openApiTypeToKATEType(): KATEType? {
    return when (this) {
        "string" -> KATEType.String
        else -> null
    }
}


fun Schema.getMapOfType(): KATEType? {
    return this.getAdditionalPropertiesSchema()?.getType()?.openApiTypeToKATEType()
}

fun Schema.getExtensionsAsProperty(type: KATEType): KATEType.Class.Property? {
    val extensions = this.getExtensions()
    if (extensions.isNotEmpty()) {
        val property = KATEType.Class.Property(type = type, meta = mutableMapOf())
        for (extension in extensions) {
            val extensionKey = extension.key.removePrefix("x-")
            val extensionValue = extension.value.toString()
            (property.meta as MutableMap)[extensionKey] = StringValue(extensionValue)
        }
        return property
    }
    return null
}

class TypedModelObject(objectName: String, val type: KATEType?) : ModelObjectImpl(objectName = objectName) {
    override fun getKnownKATEType(): KATEType = type ?: super.getKnownKATEType()
}

class MergedTypeModelObject(objectName: String, val types: MutableMap<String, KATEType.Class.Property>) :
    ModelObjectImpl(objectName = objectName) {
    override fun getKnownKATEType(): KATEType {
        if (types.isEmpty()) return super.getKnownKATEType()
        val upper = ((super.getKnownKATEType() as KATEType.Object).itemsType as KATEType.Class).members as MutableMap
        for (member in types) upper[member.key] = member.value
        return KATEType.Object(KATEType.Class(upper))
    }
}

fun Schema.toMutableKATEObject(allowNested: Boolean, fallbackName: String = ""): MutableKATEObject {

    require(this.getType() == "object")

    val name = this.getName() ?: fallbackName

    val types = mutableMapOf<String, KATEType.Class.Property>()
    val kateObj = MergedTypeModelObject(objectName = name, types = types)

    val properties = this.getProperties()
    val propertiesOverlay = Overlay.of(properties)
    for (property in properties) {
        if (property.value.getType() == "object" && !allowNested) {
            if (propertiesOverlay.isReference(property.key)) {
                kateObj.insertValue(
                    property.key,
                    TypedModelObject(
                        property.value.getName()!!,
                        property.value.getMapOfType()?.let { KATEType.Object(it) })
                )
                continue
            } else {
                val mapOf = property.value.getMapOfType()
                if (mapOf != null) {
                    kateObj.insertValue(
                        property.key,
                        TypedModelObject(
                            property.value.getName()!!,
                            property.value.getMapOfType()?.let { KATEType.Object(it) })
                    )
                    continue
                }
            }
        }
        var isRequired = false
        for (field in this.getRequiredFields()) {
            if (field == property.key) {
                isRequired = true
            }
        }
        val value = property.value.toKATEValue(allowNested = allowNested)
        property.value.getExtensionsAsProperty(type = value.getKnownKATEType())?.let {
            types[property.key] = it
        }
        if (!isRequired) {
            kateObj.setExplicitType(property.key, KATEType.NullableKateType(value.getKnownKATEType()))
        }
        kateObj.insertValue(property.key, value)
    }

    return kateObj

}