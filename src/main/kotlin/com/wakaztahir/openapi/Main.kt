package com.wakaztahir.openapi

import com.reprezen.kaizen.oasparser.OpenApiParser
import com.reprezen.kaizen.oasparser.model3.Operation
import com.reprezen.kaizen.oasparser.validate
import com.wakaztahir.openapi.template.generateFromTemplate
import com.wakaztahir.openapi.template.schema.*
import java.io.File

fun Map<String, Operation>.generateMultipleOperations(path: String, outputFile: String) {
    toMutableKATEObject(path = path).generateFromTemplate(
        output = File("output/$outputFile").also { it.parentFile.mkdirs() },
        template = "./schema/html/multiple_operations.kate.html",
        name = "routesList",
        prefix = ""
    )
}


fun Operation.generateAsHtml(method: String, path: String) {
    toMutableKATEObject(method = method).generateFromTemplate(
        name = method,
        template = "./schema/html/operation_as_html.kate.html",
        prefix = "",
        output = File("output/html/paths/${path.removePrefix("/").replace('/', '_')}/${method}.html")
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
        path.value.getOperations().generateMultipleOperations(
            path = path.value.getPathString()!!,
            outputFile = "html/paths/${
                path.value.getPathString()!!.removePrefix("/").replace('/', '_')
            }/operations.html"
        )

    }


}

fun main() {
    testCustomTemplate()
}