package com.wakaztahir.openapi

import com.reprezen.kaizen.oasparser.OpenApi3Parser
import com.reprezen.kaizen.oasparser.model3.Schema
import com.wakaztahir.kate.OutputDestinationStream
import com.wakaztahir.kate.RelativeResourceEmbeddingManager
import com.wakaztahir.kate.model.model.MutableKATEObject
import com.wakaztahir.kate.parser.stream.TextSourceStream
import java.io.File

fun Schema.generateUsingTemplate(outputDir: String, template: String, extension: String, prefix: String?) {
    val name = getName()!!
    val output = File("output/$outputDir/${name}$extension")
    val source = TextSourceStream(
        sourceCode = """@partial_raw @embed_once ./schema/$template${"\n"}@default_no_raw $prefix@var(${name}) @end_default_no_raw @end_partial_raw""",
        model = MutableKATEObject { putValue(name, toKATEValue()) }.also {
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
    generateUsingTemplate("kotlin/interface", "kotlin/object_as_interface.kate", ".kt", "package main\n\n")
}

fun Schema.generateAsKotlinDataClass() {
    generateUsingTemplate("kotlin/data_class", "kotlin/object_as_data_class.kate", ".kt", "package main\n\n")
}

fun Schema.generateAsGolangStructs() {
    generateUsingTemplate("golang/struct", "golang/object_as_go_struct.kate", ".go", "package main\n\n")
}

fun Schema.generateAsJson() {
    generateUsingTemplate("json/simple", "json/object_as_json.kate", ".json", "")
}

fun testCustomTemplate() {
    val input = object {}.javaClass.getResource("/custom/openapi3.json")!!

    val parser = OpenApi3Parser()
    val parsed = parser.parse(input)

    for (schema in parsed.getSchemas()) {
        schema.value.generateAsKotlinInterface()
        schema.value.generateAsKotlinDataClass()
        schema.value.generateAsGolangStructs()
        schema.value.generateAsJson()
    }

}

fun main() {
    testCustomTemplate()
}