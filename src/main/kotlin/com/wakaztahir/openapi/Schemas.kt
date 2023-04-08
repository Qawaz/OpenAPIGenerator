package com.wakaztahir.openapi

import com.fasterxml.jackson.databind.JsonNode
import com.reprezen.jsonoverlay.PropertiesOverlay
import com.reprezen.kaizen.oasparser.model3.Schema
import com.wakaztahir.kate.dsl.ModelObjectImpl
import com.wakaztahir.kate.model.*
import com.wakaztahir.kate.model.model.*

fun MutableKATEObject.putOpenApiType(type: String, name: String) {
    when (type) {
        "string" -> {
            putValue(name, "")
        }

        "number" -> {
            putValue(name, 0.0)
        }

        "integer" -> {
            putValue(name, 0)
        }

        "boolean" -> {
            putValue(name, false)
        }

        else -> {
            throw IllegalArgumentException("unknown openapi type $type with name $name")
        }
    }
}

fun Schema.toKATEValue(skipReferences: Boolean): KATEValue {

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
            KATEListImpl(listOf(this.getItemsSchema()!!.toKATEValue(skipReferences = skipReferences)))
        }

        "object" -> {
            if(this.checkIsReference()) {
                StringValue("SKIP_${getName()}")
            } else {
                toMutableKATEObject()
            }
        }

        else -> {
            throw IllegalArgumentException("unknown openapi type ${this.getType()}")
        }
    }
}

private fun JsonNode.isReference(): Boolean {
    val refNode = this.get("\$ref")
    return refNode != null && !refNode.isNull
}

private fun Schema.checkIsReference(): Boolean {
    val jsonNode = (this as? PropertiesOverlay<*>)?.json
    return jsonNode != null && jsonNode.isReference()
}

fun Schema.toMutableKATEObject(): MutableKATEObject {

    val name = this.getName() ?: "UNKNOWN"

    val kateObj = MutableKATEObject(name = name) { }

    when (this.getType()) {

        "object" -> {
            val properties = this.getProperties()
            for (property in properties) {
                kateObj.putValue(property.key, property.value.toKATEValue(skipReferences = true))
            }
        }

        else -> {
            kateObj.putOpenApiType(this.getType()!!, name)
        }
    }

    return kateObj

}