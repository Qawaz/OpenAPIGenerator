package com.wakaztahir.openapi

enum class LanguageGeneration(
    val value: String,
    val defaultString: () -> String,
    val template: String,
    val outputFolder: String,
    val allowNested: Boolean = false,
) {
    GolangModels(
        value = "golang-models",
        defaultString = { "$DefaultConfig$GolangDefaultConfig" },
        template = "schema/golang/gen.kate",
        outputFolder = "output/golang/models"
    ),
    GoLangServer(
        value = "golang-server",
        defaultString = { "$DefaultConfig$GolangDefaultConfig" },
        template = "schema/golang/server/gen.kate",
        outputFolder = "output/golang/server"
    ),
    HTML(
        value = "html",
        defaultString = { DefaultConfig },
        template = "schema/html/gen.kate",
        outputFolder = "output/html"
    ),
    Kotlin(
        value = "kotlin",
        defaultString = { DefaultConfig },
        template = "schema/kotlin/gen.kate",
        outputFolder = "output/kotlin"
    ),
    Json(
        value = "json",
        defaultString = { DefaultConfig },
        template = "schema/json/gen.kate",
        outputFolder = "output/json",
        allowNested = true
    ),
    Raw(
        value = "raw",
        defaultString = { DefaultConfig },
        template = "schema/general/raw.gen.kate",
        outputFolder = "output/raw"
    );
}