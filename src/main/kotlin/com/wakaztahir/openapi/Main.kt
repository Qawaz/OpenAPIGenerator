package com.wakaztahir.openapi

import com.reprezen.kaizen.oasparser.OpenApiParser
import com.reprezen.kaizen.oasparser.model3.OpenApi3
import com.reprezen.kaizen.oasparser.model3.Operation
import com.reprezen.kaizen.oasparser.model3.Path
import com.reprezen.kaizen.oasparser.model3.Schema
import com.reprezen.kaizen.oasparser.validate
import com.wakaztahir.kate.OutputDestinationStream
import com.wakaztahir.kate.RelativeResourceEmbeddingManager
import com.wakaztahir.kate.model.LazyBlock
import com.wakaztahir.kate.model.StringValue
import com.wakaztahir.kate.model.model.*
import com.wakaztahir.kate.parser.stream.SourceStream
import com.wakaztahir.kate.parser.stream.TextDestinationStream
import com.wakaztahir.kate.parser.stream.TextSourceStream
import com.wakaztahir.openapi.template.generateForSingleLanguage
import com.wakaztahir.openapi.template.generateFromTemplate
import com.wakaztahir.openapi.template.generateMultiFileTemplate
import com.wakaztahir.openapi.template.path.generateAsHtml
import com.wakaztahir.openapi.template.path.generateMultipleFromTemplate
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
    }

    // all schemas into a single file
    parsed.getSchemas().values.generateAsKotlinInterface()
    parsed.getSchemas().values.generateAsKotlinDataClass()
    parsed.getSchemas().values.generateAsSerializableKotlinDataClass()
    parsed.getSchemas().values.generateAsOverridableInterface()
    parsed.getSchemas().values.generateAsOverridableSerializableInterface()
    parsed.getSchemas().values.generateAsGolangStructs()
    parsed.getSchemas().values.generateAsJson()

    parsed.toMutableKATEObject().generateMultiFileTemplate(
        template = "schema/general/raw.gen.kate",
        outputDir = File("output/raw")
    )

    parsed.toMutableKATEObject().generateMultiFileTemplate(
        template = "schema/html/gen.kate",
        outputDir = File("output/html")
    )

}

fun main() {
    testCustomTemplate()
}