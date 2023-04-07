package com.wakaztahir.openapi

import com.reprezen.kaizen.oasparser.OpenApi3Parser

fun testCustomTemplate(){
    val input = object { }.javaClass.getResource("custom/openapi3.json")!!
    val parser = OpenApi3Parser()
    val parsed = parser.parse(input)
    for(schema in parsed.getSchemas()){
        println("SCHEMA FOUND ${schema.key}")
        schema.value.toMutableKATEObject()
    }
}

fun main() {
    testCustomTemplate()
}