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
            if(this.getEnums().isNotEmpty()){
                KATEEnum(this.getEnums().map { it as String })
            } else {
                StringValue("")
            }
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

fun Schema.addExtensionsAsMetadata(type: KATEType): KATEType? {
    if (this.getExtensions().isNotEmpty() || this.getFormat() != null) {
        val metaMap = mutableMapOf<String, KATEValue>()
        this.getFormat()?.let { metaMap["format"] = StringValue(it) }
        for (extension in this.getExtensions()) {
            val extensionKey = extension.key.removePrefix("x-")
            val extensionValue = extension.value as? String
            if (extensionValue != null) {
                metaMap[extensionKey] = StringValue(extensionValue)
            }
        }
        return KATEType.TypeWithMetadata(actual = type, metaMap)
    }
    return null
}

class TypedModelObject(objectName: String, val type: KATEType?) : ModelObjectImpl(objectName = objectName) {
    override fun getKnownKATEType(): KATEType = type ?: super.getKnownKATEType()
}

fun Schema.toMutableKATEObject(allowNested: Boolean, fallbackName: String = ""): MutableKATEObject {

    require(this.getType() == "object")

    val name = this.getName() ?: fallbackName

    val kateObj = ModelObjectImpl(objectName = name)

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

        var typeName: KATEType? = null
        if (!isRequired) {
            typeName = KATEType.NullableKateType(value.getKnownKATEType())
        }
        typeName = property.value.addExtensionsAsMetadata(typeName ?: value.getKnownKATEType()) ?: typeName
        if (typeName != null) {
            kateObj.setExplicitType(property.key, typeName)
        }
        kateObj.insertValue(property.key, value)
    }

    return kateObj

}