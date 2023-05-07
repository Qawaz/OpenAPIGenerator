package com.wakaztahir.openapi

import com.reprezen.kaizen.oasparser.OpenApiParser
import com.reprezen.kaizen.oasparser.validate
import com.wakaztahir.openapi.template.generateMultiFileTemplate
import java.io.File

fun main(strArr: Array<String>) {
    if (strArr.size < 3) {
        throw IllegalArgumentException("there must be at least 3 cmd parameters language-gen-type schema_path output_dir_path , example golang-server ./ output/")
    } else {
        strArr.joinToString(",") { it }
    }
    val languageGenType: String = strArr[0]
    val schemaPath: String = strArr[1]
    val outputDirPath: String = strArr[2]
    val templatesDirPath: String = strArr.getOrNull(3) ?: "./templates"
    val verifySchema: Boolean = strArr.getOrNull(4)?.let { it.equals("true", ignoreCase = true) } ?: true
    val isForcedDefault: Boolean = strArr.getOrNull(5)?.let { it.equals("true", ignoreCase = true) } ?: false
    val isForcedDefaultWrite: Boolean = strArr.getOrNull(6)?.let { it.equals("true", ignoreCase = true) } ?: false

    // KATE Configuration file

    val input = File(schemaPath)
    val parser = OpenApiParser()
    val parsed = parser.parse(input)
    if (verifySchema) {
        val results = parsed.validate().getItems()
        if (results.isNotEmpty()) {
            for (result in results) println(result)
            throw IllegalStateException("input schema isn't valid")
        }
    }
    val gens = if (languageGenType == "all") LanguageGeneration.values().toList() else listOf(
        LanguageGeneration.values().find { it.value == languageGenType }
            ?: throw IllegalStateException("generation type $languageGenType not found")
    )
    for (gen in gens) {
        val outputDir = File(if (languageGenType == "all") gen.outputFolder else outputDirPath)
        val configFile = outputDir.resolve("gen.config.kate")
        val config = loadConfigurationObject(
            configFile = configFile,
            forceDefault = isForcedDefault,
            forceWriteDefault = isForcedDefaultWrite,
            configString = gen.defaultString
        )
        parsed.toMutableKATEObject(allowNested = gen.allowNested).apply {
            insertValue("config", config)
        }.generateMultiFileTemplate(
            templatesDir = templatesDirPath,
            template = gen.template,
            outputDir = outputDir
        )
    }
}