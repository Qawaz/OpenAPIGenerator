package com.wakaztahir.openapi

import com.fasterxml.jackson.databind.JsonNode
import com.reprezen.jsonoverlay.PropertiesOverlay
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
            IntValue(0)
        }

        "boolean" -> {
            BooleanValue(false)
        }

        "array" -> {
            KATEListImpl(listOf(this.getItemsSchema()!!.toKATEValue(allowNested = allowNested)))
        }

        "object" -> {
            val references = mutableListOf<String>()
            getNode()?.getReferencesInto(references)
            toMutableKATEObject(avoidObjects = references, allowNested = allowNested)
        }

        else -> {
            throw IllegalArgumentException("unknown openapi type ${this.getType()} for key ${this.getName()}")
        }
    }
}

fun MutableKATEObject.addNoNested(): MutableKATEObject {
    putValue("__no_nested__", true)
    return this
}

fun MutableKATEObject.addMapOf(value: String): MutableKATEObject {
    putValue("__map_of__", value)
    return this
}

fun Schema.getMapOf(): String? {
    if (this.getAdditionalPropertiesSchema()?.getType() == "string") {
        return "string"
    }
    return null
}

private fun JsonNode.getReferencesInto(list: MutableList<String>) {
    fields().forEach {
        if (it.key == "\$ref") {
            list.add(it.value.asText().let { t -> t.substring(t.lastIndexOf('/') + 1) })
        } else {
            it.value.getReferencesInto(list)
        }
    }
}

private fun Schema.getNode() = (this as? PropertiesOverlay<*>)?.json

fun Schema.toMutableKATEObject(avoidObjects: List<String>, allowNested: Boolean): MutableKATEObject {

    require(this.getType() == "object")

    val name = this.getName() ?: "UNKNOWN"

    val kateObj = MutableKATEObject(name = name) { }

    val properties = this.getProperties()
    for (property in properties) {
        if (property.value.getType() == "object" && !allowNested) {
            if (avoidObjects.contains(property.value.getName())) {
                kateObj.putValue(property.key, ModelObjectImpl(property.value.getName()!!).addNoNested().also { obj ->
                    property.value.getMapOf()?.let { obj.addMapOf(it) }
                })
                continue
            } else {
                val mapOf = property.value.getMapOf()
                if (mapOf != null) {
                    kateObj.putValue(
                        property.key,
                        ModelObjectImpl(property.value.getName()!!).addNoNested().also { obj ->
                            property.value.getMapOf()?.let { obj.addMapOf(it) }
                        })
                    continue
                }
            }
        }
        kateObj.putValue(property.key, property.value.toKATEValue(allowNested = allowNested))
    }

    return kateObj

}