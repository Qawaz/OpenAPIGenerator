package com.wakaztahir.openapi

import com.reprezen.kaizen.oasparser.OpenApi3Parser
import com.reprezen.kaizen.oasparser.model3.Schema
import com.wakaztahir.kate.OutputDestinationStream
import com.wakaztahir.kate.RelativeResourceEmbeddingManager
import com.wakaztahir.kate.model.model.MutableKATEObject
import com.wakaztahir.kate.parser.stream.TextSourceStream
import java.io.File

fun Schema.generateUsingTemplate(outputDir: String, template: String, extension: String, prefix: String?,allowNested : Boolean = false) {
    val name = getName()!!
    val output = File("output/$outputDir/${name}$extension")
    val source = TextSourceStream(
        sourceCode = """@partial_raw @embed_once ./schema/$template${"\n"}@default_no_raw $prefix@var(${name}) @end_default_no_raw @end_partial_raw""",
        model = MutableKATEObject { putValue(name, toKATEValue(allowNested = allowNested)) }.also {
//            println("Object $name , Template $template , Extension $extension")
//            println(it)
        },
        embeddingManager = RelativeResourceEmbeddingManager(basePath = "/", classLoader = object {}.javaClass)
    )
    val stream = output.outputStream()
    source.generateTo(OutputDestinationStream(stream))
    stream.close()
}

fun Schema.generateAsKotlinInterface() {
    generateUsingTemplate("kotlin/models/interface", "kotlin/object_as_interface.kate", ".kt", "package `interface`\n\n")
}

fun Schema.generateAsKotlinDataClass() {
    generateUsingTemplate("kotlin/models/data_class", "kotlin/object_as_data_class.kate", ".kt", "package data_class\n\n")
}

fun Schema.generateAsSerializableKotlinDataClass() {
    generateUsingTemplate("kotlin/models/serializable_data_class", "kotlin/object_as_serializable_data_class.kate", ".kt", "package data_class\n\n")
}

fun Schema.generateAsOverridableInterface() {
    generateUsingTemplate("kotlin/models/overridden", "kotlin/object_as_data_class_overriding_interface.kate", ".kt", "package overridden\n\n")
}

fun Schema.generateAsOverridableSerializableInterface() {
    generateUsingTemplate("kotlin/models/overridden_serializable", "kotlin/object_as_serializable_data_class_overriding_interface.kate", ".kt", "package overridden_serializable\n\n")
}

fun Schema.generateAsGolangStructs() {
    generateUsingTemplate("golang/models/struct", "golang/object_as_go_struct.kate", ".go", "package main\n\n")
}

fun Schema.generateAsJson() {
    generateUsingTemplate("json/simple", "json/object_as_json.kate", ".json", "", allowNested = true)
}

fun testCustomTemplate() {
    val input = object {}.javaClass.getResource("/custom/openapi3.json")!!

    val parser = OpenApi3Parser()
    val parsed = parser.parse(input)

    for (schema in parsed.getSchemas()) {
        schema.value.generateAsKotlinInterface()
        schema.value.generateAsKotlinDataClass()
        schema.value.generateAsSerializableKotlinDataClass()
        schema.value.generateAsOverridableInterface()
        schema.value.generateAsOverridableSerializableInterface()
        schema.value.generateAsGolangStructs()
        schema.value.generateAsJson()
    }

}

fun main() {
    testCustomTemplate()
}