package com.wakaztahir.openapi

import com.fasterxml.jackson.databind.JsonNode
import com.reprezen.jsonoverlay.PropertiesOverlay
import com.reprezen.kaizen.oasparser.model3.Schema
import com.wakaztahir.kate.dsl.ModelObjectImpl
import com.wakaztahir.kate.model.*
import com.wakaztahir.kate.model.model.*

fun Schema.toKATEValue(): KATEValue {
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
            KATEListImpl(listOf(this.getItemsSchema()!!.toKATEValue()))
        }

        "object" -> {
            val references = mutableListOf<String>()
            getNode()?.getReferencesInto(references)
            toMutableKATEObject(avoidObjects = references)
        }

        else -> {
            throw IllegalArgumentException("unknown openapi type ${this.getType()}")
        }
    }
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

fun Schema.toMutableKATEObject(avoidObjects: List<String>): MutableKATEObject {

    require(this.getType() == "object")

    val name = this.getName() ?: "UNKNOWN"

    val kateObj = MutableKATEObject(name = name) { }

    val properties = this.getProperties()
    for (property in properties) {
        if (property.value.getType() == "object" && avoidObjects.contains(property.value.getName())) {
            continue
        }
        kateObj.putValue(property.key, property.value.toKATEValue())
    }

    return kateObj

}