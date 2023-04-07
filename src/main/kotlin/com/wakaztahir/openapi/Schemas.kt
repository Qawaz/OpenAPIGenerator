package com.wakaztahir.openapi

import com.reprezen.kaizen.oasparser.model3.Schema
import com.wakaztahir.kate.model.LazyReferencedValue
import com.wakaztahir.kate.model.model.KATEObject
import com.wakaztahir.kate.model.model.MutableKATEObject
import com.wakaztahir.kate.model.model.MutableKTEObject

fun Schema.toMutableKATEObject(): MutableKATEObject {

    val kateObj = MutableKTEObject { }

    when (this.getType()) {

        "object" -> {
            val properties = this.getProperties()
            for (property in properties) {
                property.value.toMutableKATEObject()
            }
        }

        "string" -> {
            println("${this.getType()} : ${this.getName()}")
        }

        "number" -> {
            println("${this.getType()} : ${this.getName()}")
        }

        "integer" -> {
            println("${this.getType()} : ${this.getName()}")
        }

        "boolean" -> {
            println("${this.getType()} : ${this.getName()}")
        }

        "array" -> {
            println("${this.getType()} : ${this.getName()}")
        }

        else -> {
            throw IllegalArgumentException("unknown type ${this@toMutableKATEObject.getType()}")
        }
    }

    return kateObj

}