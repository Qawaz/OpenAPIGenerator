package com.wakaztahir.openapi

import com.reprezen.kaizen.oasparser.OpenApiParser
import com.reprezen.kaizen.oasparser.validate
import com.wakaztahir.openapi.template.generateMultiFileTemplate
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

    parsed.toMutableKATEObject().generateMultiFileTemplate(
        template = "schema/kotlin/gen.kate",
        outputDir = File("output/kotlin")
    )

    parsed.toMutableKATEObject().generateMultiFileTemplate(
        template = "schema/html/gen.kate",
        outputDir = File("output/html")
    )

    parsed.toMutableKATEObject(allowNested = true).generateMultiFileTemplate(
        template = "schema/json/gen.kate",
        outputDir = File("output/json")
    )

    parsed.toMutableKATEObject().generateMultiFileTemplate(
        template = "schema/general/raw.gen.kate",
        outputDir = File("output/raw")
    )

    parsed.toMutableKATEObject().generateMultiFileTemplate(
        template = "schema/golang/gen.kate",
        outputDir = File("output/golang")
    )

}

fun main() {
    testCustomTemplate()
}