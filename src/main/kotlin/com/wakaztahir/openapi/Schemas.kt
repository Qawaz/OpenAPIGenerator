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

fun MutableKATEObject.addNoNested(): MutableKATEObject {
    insertValue("__no_nested__", true)
    return this
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

fun Schema.toMutableKATEObject(allowNested: Boolean, fallbackName: String = ""): MutableKATEObject {

    require(this.getType() == "object")

    val name = this.getName() ?: fallbackName

    val kateObj = MutableKATEObject(name = name) { }

    val properties = this.getProperties()
    val propertiesOverlay = Overlay.of(properties)
    for (property in properties) {
        if (property.value.getType() == "object" && !allowNested) {
            if (propertiesOverlay.isReference(property.key)) {
                kateObj.insertValue(
                    property.key,
                    ModelObjectImpl(
                        property.value.getName()!!,
                        itemType = property.value.getMapOfType() ?: KATEType.Any
                    ).addNoNested()
                )
                continue
            } else {
                val mapOf = property.value.getMapOfType()
                if (mapOf != null) {
                    kateObj.insertValue(
                        property.key,
                        ModelObjectImpl(property.value.getName()!!, itemType = mapOf).addNoNested()
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
        if (!isRequired) {
            kateObj.setExplicitType(property.key, KATEType.NullableKateType(value.getKnownKATEType()))
        }
        kateObj.insertValue(property.key, value)
    }

    return kateObj

}