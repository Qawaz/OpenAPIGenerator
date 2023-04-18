package com.wakaztahir.openapi

import com.reprezen.kaizen.oasparser.OpenApiParser
import com.reprezen.kaizen.oasparser.model3.Operation
import com.reprezen.kaizen.oasparser.model3.Path
import com.reprezen.kaizen.oasparser.model3.Schema
import com.reprezen.kaizen.oasparser.validate
import com.wakaztahir.kate.OutputDestinationStream
import com.wakaztahir.kate.RelativeResourceEmbeddingManager
import com.wakaztahir.kate.model.model.KATEObject
import com.wakaztahir.kate.model.model.KATEValue
import com.wakaztahir.kate.model.model.MutableKATEObject
import com.wakaztahir.kate.parser.stream.TextSourceStream
import java.io.File

fun KATEValue.generateFromTemplate(name: String, template: String, prefix: String, output: File) {
    val source = TextSourceStream(
        sourceCode = """@partial_raw @embed_once ./$template${"\n"}@default_no_raw $prefix@var(${name}) @end_default_no_raw @end_partial_raw""",
        model = MutableKATEObject { putValue(name, this@generateFromTemplate) }.also {
//            println("Object $name , Template $template , Extension $extension")
//            println(it)
        },
        embeddingManager = RelativeResourceEmbeddingManager(basePath = "/", classLoader = object {}.javaClass)
    )
    val stream = output.outputStream()
    source.generateTo(OutputDestinationStream(stream))
    stream.close()
}

fun List<KATEObject>.generateMultipleFromTemplate(output: File, template: String) {
    val model = MutableKATEObject {
        for (obj in this@generateMultipleFromTemplate) {
            putValue(obj.objectName, obj)
        }
    }
    var sourceCode = "@partial_raw @embed_once ./$template\n"
    for (obj in this) {
        sourceCode += "@default_no_raw @var(${obj.objectName}) @end_default_no_raw"
    }
    sourceCode += "@end_partial_raw"
    val source = TextSourceStream(
        sourceCode = sourceCode,
        model = model,
        embeddingManager = RelativeResourceEmbeddingManager(basePath = "/", classLoader = object {}.javaClass)
    )
    val stream = output.outputStream()
    source.generateTo(OutputDestinationStream(stream))
    stream.close()
}

fun Collection<Schema>.generateMultipleFromTemplate(outputFile: String, template: String, allowNested: Boolean = false) {
    map { it.toKATEValue(allowNested = allowNested) as MutableKATEObject }.generateMultipleFromTemplate(
        output = File("output/$outputFile"),
        template = template
    )
}

fun Map<String, Operation>.generateMultipleRoutes(outputFile: String) {
    toMutableKATEObject().generateFromTemplate(
        output = File("output/$outputFile").also { it.parentFile.mkdirs() },
        template = "./schema/html/multiple_operations.kate.html",
        name = "routesList",
        prefix = ""
    )
}

//fun Map<String, Path>.generateIntoSingleTemplate(
//    outputFile: String,
//    template: String,
//) {
//    map { path ->
//        path.value.getOperations().toListMutableKATEObject(path = path.value.getPathString()!!)
//    }.generateMultipleFromTemplate(
//        output = File("output/$outputFile").also { it.parentFile.mkdirs() },
//        template = template
//    )
//}

fun Schema.generateUsingTemplate(
    outputDir: String,
    template: String,
    extension: String,
    prefix: String,
    allowNested: Boolean = false
) {
    val name = getName()!!
    toKATEValue(allowNested = allowNested).generateFromTemplate(
        name = name,
        template = template,
        prefix = prefix,
        output = File("output/$outputDir/${name}$extension")
    )
}

fun Schema.generateAsKotlinInterface() {
    generateUsingTemplate(
        "kotlin/models/interface",
        "schema/kotlin/object_as_interface.kate",
        ".kt",
        "package `interface`\n\n"
    )
}

fun Schema.generateAsKotlinDataClass() {
    generateUsingTemplate(
        "kotlin/models/data_class",
        "schema/kotlin/object_as_data_class.kate",
        ".kt",
        "package data_class\n\n"
    )
}

fun Schema.generateAsSerializableKotlinDataClass() {
    generateUsingTemplate(
        "kotlin/models/serializable_data_class",
        "schema/kotlin/object_as_serializable_data_class.kate",
        ".kt",
        "package data_class\n\n"
    )
}

fun Schema.generateAsOverridableInterface() {
    generateUsingTemplate(
        "kotlin/models/overridden",
        "schema/kotlin/object_as_data_class_overriding_interface.kate",
        ".kt",
        "package overridden\n\n"
    )
}

fun Schema.generateAsOverridableSerializableInterface() {
    generateUsingTemplate(
        "kotlin/models/overridden_serializable",
        "schema/kotlin/object_as_serializable_data_class_overriding_interface.kate",
        ".kt",
        "package overridden_serializable\n\n"
    )
}

fun Schema.generateAsGolangStructs() {
    generateUsingTemplate("golang/models/struct", "schema/golang/object_as_go_struct.kate", ".go", "package main\n\n")
}

fun Schema.generateAsJson() {
    generateUsingTemplate("json/simple", "schema/json/object_as_json.kate", ".json", "", allowNested = true)
}

fun Schema.generateAsHtml() {
    generateUsingTemplate("html/models", "schema/html/object_as_html.kate", ".html", "")
}

fun Operation.generateAsHtml(method: String, path: String) {
    toMutableKATEObject(method = method).generateFromTemplate(
        name = method,
        template = "./schema/html/operation_as_html.kate.html",
        prefix = "",
        output = File("output/html/routes/${path.removePrefix("/").replace('/', '_')}/${method}.html")
    )
}

fun testCustomTemplate() {
    val input = object {}.javaClass.getResource("/custom/openapi3.json")!!

    val parser = OpenApiParser()
    val parsed = parser.parse(input)

    val results = parsed.validate().getItems()
    if (results.isNotEmpty()) {
        for (result in results) println(result)
        throw IllegalStateException("Schema isn't valid")
    }

    parsed.getPaths().values.first().getPathString()

    // each schema into its own file for its language
    for (schema in parsed.getSchemas()) {
        schema.value.generateAsKotlinInterface()
        schema.value.generateAsKotlinDataClass()
        schema.value.generateAsSerializableKotlinDataClass()
        schema.value.generateAsOverridableInterface()
        schema.value.generateAsOverridableSerializableInterface()
        schema.value.generateAsGolangStructs()
        schema.value.generateAsJson()
        schema.value.generateAsHtml()
    }

    // all schemas into a single file
    parsed.getSchemas().values.generateMultipleFromTemplate(
        outputFile = "html/models.html",
        template = "./schema/html/object_as_html.kate"
    )

    for (path in parsed.getPaths()) {

        // each operation of path into its own file
        for (operation in path.value.getOperations()) {
            operation.value.generateAsHtml(method = operation.key, path = path.value.getPathString()!!)
        }

        // all operations of path into a single file
        path.value.getOperations().generateMultipleRoutes(
            outputFile = "html/routes/${path.value.getPathString()!!.removePrefix("/").replace('/', '_')}/operations.html"
        )

    }


}

fun main() {
    testCustomTemplate()
}