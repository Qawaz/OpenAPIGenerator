package com.wakaztahir.openapi

import com.reprezen.kaizen.oasparser.OpenApiParser
import com.reprezen.kaizen.oasparser.model3.OpenApi3
import com.reprezen.kaizen.oasparser.model3.Operation
import com.reprezen.kaizen.oasparser.model3.Path
import com.reprezen.kaizen.oasparser.model3.Schema
import com.reprezen.kaizen.oasparser.validate
import com.wakaztahir.openapi.template.generateFromTemplate
import com.wakaztahir.openapi.template.path.generateAsHtml
import com.wakaztahir.openapi.template.path.generateMultipleFromTemplate
import com.wakaztahir.openapi.template.path.operation.generateAsHtml
import com.wakaztahir.openapi.template.schema.*
import java.io.File

fun OpenApi3.generateForSingleLanguage(
    generateSingleSchema: (Schema) -> Unit,
    generateSchemaColl: (Collection<Schema>) -> Unit,
    generateSingleOperation: (Operation, String) -> Unit,
    generateOperationColl: (Collection<Operation>, String) -> Unit,
    generateSinglePath: (Path) -> Unit,
    generatePathColl: (Collection<Path>) -> Unit,
    generateCompleteSpec: (OpenApi3) -> Unit
) {

    // each schema into its own file for its language
    for (schema in getSchemas()) {
        generateSingleSchema(schema.value)
    }

    // all schemas into a single file
    generateSchemaColl(getSchemas().values)

    for (path in getPaths()) {

        // each operation of path into its own file
        for (operation in path.value.getOperations()) {
            generateSingleOperation(operation.value, path.value.getPathString()!!)
        }

        // all operations of path into a single
        generateOperationColl(path.value.getOperations().values, path.value.getPathString()!!)

        // each path into its own file
        generateSinglePath(path.value)

    }

    // all paths into single file
    generatePathColl(getPaths().values)

    // whole spec into a single file
    generateCompleteSpec(this)

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

    // each schema into its own file for its language
    for (schema in parsed.getSchemas()) {
        schema.value.generateAsKotlinInterface()
        schema.value.generateAsKotlinDataClass()
        schema.value.generateAsSerializableKotlinDataClass()
        schema.value.generateAsOverridableInterface()
        schema.value.generateAsOverridableSerializableInterface()
        schema.value.generateAsGolangStructs()
        schema.value.generateAsJson()
    }

    // all schemas into a single file
    parsed.getSchemas().values.generateAsKotlinInterface()
    parsed.getSchemas().values.generateAsKotlinDataClass()
    parsed.getSchemas().values.generateAsSerializableKotlinDataClass()
    parsed.getSchemas().values.generateAsOverridableInterface()
    parsed.getSchemas().values.generateAsOverridableSerializableInterface()
    parsed.getSchemas().values.generateAsGolangStructs()
    parsed.getSchemas().values.generateAsJson()

    parsed.generateForSingleLanguage(
        generateSingleSchema = { it.generateAsHtml() },
        generateSchemaColl = { it.generateAsHtml() },
        generateSingleOperation = { op, path -> op.generateAsHtml(path = path) },
        generateOperationColl = { coll, path -> coll.generateAsHtml(path = path) },
        generateSinglePath = { it.generateAsHtml() },
        generatePathColl = { it.generateAsHtml() },
        generateCompleteSpec = {
            it.toMutableKATEObject().generateFromTemplate(
                name = "OpenApiObj",
                template = "./schema/html/spec_as_html.kate.html",
                prefix = "",
                output = File("output/html/spec.html")
            )
        }
    )

}

fun main() {
    testCustomTemplate()
}