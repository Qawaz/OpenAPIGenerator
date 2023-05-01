package com.wakaztahir.openapi

import com.reprezen.kaizen.oasparser.model3.OpenApi3
import com.wakaztahir.openapi.template.generateMultiFileTemplate
import java.io.File

enum class LanguageGeneration(val value: String, val defaultString: () -> String, val template: String) {
    GolangModels("golang-models", { GolangDefaultConfig }, "schema/golang/gen.kate"),
    GoLangServer("golang-server", { GolangDefaultConfig }, "schema/golang/server/gen.kate"),
    HTML("html", { DefaultConfig }, "schema/html/gen.kate"),
    Kotlin("kotlin", { DefaultConfig }, "schema/kotlin/gen.kate"),
    Json("json", { "" }, "schema/json/gen.kate"),
    Raw("raw", { "" }, "schema/general/raw.gen.kate");
}

fun OpenApi3.generateKotlin() {
    toMutableKATEObject().generateMultiFileTemplate(
        template = LanguageGeneration.Kotlin.template,
        outputDir = File("output/kotlin")
    )
}

fun OpenApi3.generateHtml() {
    toMutableKATEObject().generateMultiFileTemplate(
        template = LanguageGeneration.HTML.template,
        outputDir = File("output/html")
    )
}

fun OpenApi3.generateJson() {
    toMutableKATEObject(allowNested = true).generateMultiFileTemplate(
        template = LanguageGeneration.Json.template,
        outputDir = File("output/json")
    )
}

fun OpenApi3.generateRaw() {
    toMutableKATEObject().generateMultiFileTemplate(
        template =  LanguageGeneration.Raw.template,
        outputDir = File("output/raw")
    )
}

fun OpenApi3.generateGolang() {
    toMutableKATEObject().generateMultiFileTemplate(
        template = LanguageGeneration.GolangModels.template,
        outputDir = File("output/golang")
    )
}

fun OpenApi3.generateGolangServer() {
    toMutableKATEObject().generateMultiFileTemplate(
        template = LanguageGeneration.GoLangServer.template,
        outputDir = File("output/golang/server")
    )
}