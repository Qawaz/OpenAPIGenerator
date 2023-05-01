package com.wakaztahir.openapi

import com.reprezen.kaizen.oasparser.OpenApiParser
import com.reprezen.kaizen.oasparser.validate
import com.wakaztahir.openapi.template.generateMultiFileTemplate
import java.io.File
import java.net.URL

fun main(strArr: Array<String>) {
    if (strArr.size != 3) {
        throw IllegalArgumentException("there must be at least 3 cmd parameters language-gen-type schema_path output_dir_path , example golang-server ./ output/")
    } else {
        strArr.joinToString(","){ it }
    }
    val languageGenType: String = strArr[0]
    val schemaPath: String = strArr[1]
    val outputDirPath: String = strArr[2]

    // Getting Language Generation
    val gen: LanguageGeneration = LanguageGeneration.values().find { it.value == languageGenType }
        ?: throw IllegalStateException("generation type $languageGenType not found")

    // KATE Configuration file
    val configFile = File(outputDirPath).resolve("gen.config.kate")
    val config = loadConfigurationObject(configFile = configFile, langGen = gen)

    val input = File(schemaPath)
    val parser = OpenApiParser()
    val parsed = parser.parse(input)
    val results = parsed.validate().getItems()
    if (results.isNotEmpty()) {
        for (result in results) println(result)
        throw IllegalStateException("input schema isn't valid")
    }
    parsed.toMutableKATEObject().apply {
        insertValue("config", config)
    }.generateMultiFileTemplate(
        template = gen.template,
        outputDir = File(outputDirPath)
    )
}