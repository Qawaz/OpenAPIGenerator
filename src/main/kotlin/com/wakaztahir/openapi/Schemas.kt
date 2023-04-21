package com.wakaztahir.openapi

import com.reprezen.jsonoverlay.JsonOverlay
import com.reprezen.jsonoverlay.Overlay
import com.reprezen.kaizen.oasparser.model3.Schema
import com.wakaztahir.kate.dsl.ModelObjectImpl
import com.wakaztahir.kate.model.*
import com.wakaztahir.kate.model.model.KATEListImpl
import com.wakaztahir.kate.model.model.KATEValue
import com.wakaztahir.kate.model.model.MutableKATEObject

fun Schema.toKATEValue(allowNested: Boolean): KATEValue {
    return when (this.getType()) {
        "string" -> {
            StringValue("")
        }

        "number" -> {
            DoubleValue(0.0)
        }

        "integer" -> {
            IntValue(0)
        }

        "boolean" -> {
            BooleanValue(false)
        }

        "array" -> {
            KATEListImpl(listOf(this.getItemsSchema()!!.toKATEValue(allowNested = allowNested)),itemType = KATEType.Any)
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
    setValue("__no_nested__", true)
    return this
}

fun MutableKATEObject.addMapOf(value: String): MutableKATEObject {
    setValue("__map_of__", value)
    return this
}

fun Schema.getMapOf(): String? {
    if (this.getAdditionalPropertiesSchema()?.getType() == "string") {
        return "string"
    }
    return null
}

fun Schema.toMutableKATEObject(allowNested: Boolean): MutableKATEObject {

    require(this.getType() == "object")

    val name = this.getName() ?: ""

    val kateObj = MutableKATEObject(name = name) { }

    val properties = this.getProperties()
    val propertiesOverlay = Overlay.of(properties)
    for (property in properties) {
        if (property.value.getType() == "object" && !allowNested) {
            if (propertiesOverlay.isReference(property.key)) {
                kateObj.setValue(property.key, ModelObjectImpl(property.value.getName()!!,itemType = KATEType.Any).addNoNested().also { obj ->
                    property.value.getMapOf()?.let { obj.addMapOf(it) }
                })
                continue
            } else {
                val mapOf = property.value.getMapOf()
                if (mapOf != null) {
                    kateObj.setValue(
                        property.key,
                        ModelObjectImpl(property.value.getName()!!,itemType = KATEType.Any).addNoNested().also { obj ->
                            property.value.getMapOf()?.let { obj.addMapOf(it) }
                        })
                    continue
                }
            }
        }
        kateObj.setValue(property.key, property.value.toKATEValue(allowNested = allowNested))
    }

    return kateObj

}