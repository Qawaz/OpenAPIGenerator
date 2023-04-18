package com.wakaztahir.openapi

import com.reprezen.kaizen.oasparser.OpenApiParser
import com.reprezen.kaizen.oasparser.model3.Operation
import com.reprezen.kaizen.oasparser.validate
import com.wakaztahir.openapi.template.generateFromTemplate
import com.wakaztahir.openapi.template.path.operation.generateAsHtml
import com.wakaztahir.openapi.template.schema.*
import java.io.File

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
        schema.value.generateAsHtml()
    }

    // all schemas into a single file
    parsed.getSchemas().values.generateAsKotlinInterface()
    parsed.getSchemas().values.generateAsKotlinDataClass()
    parsed.getSchemas().values.generateAsSerializableKotlinDataClass()
    parsed.getSchemas().values.generateAsOverridableInterface()
    parsed.getSchemas().values.generateAsOverridableSerializableInterface()
    parsed.getSchemas().values.generateAsGolangStructs()
    parsed.getSchemas().values.generateAsJson()
    parsed.getSchemas().values.generateAsHtml()

    for (path in parsed.getPaths()) {

        // each operation of path into its own file
        for (operation in path.value.getOperations()) {
            operation.value.generateAsHtml(path = path.value.getPathString()!!)
        }

        path.value.getOperations().values.generateAsHtml(path = path.value.getPathString()!!)

    }


}

fun main() {
    testCustomTemplate()
}