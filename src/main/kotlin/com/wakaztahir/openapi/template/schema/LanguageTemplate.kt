package com.wakaztahir.openapi.template.schema

import com.reprezen.kaizen.oasparser.model3.Schema

fun Schema.generateAsKotlinInterface() {
    generateUsingTemplate(
        outputDir = "kotlin/models/interface",
        template = "schema/kotlin/object_as_interface.kate",
        extension = ".kt",
        prefix = "package `interface`\n\n"
    )
}

fun Schema.generateAsKotlinDataClass() {
    generateUsingTemplate(
        outputDir = "kotlin/models/data_class",
        template = "schema/kotlin/object_as_data_class.kate",
        extension = ".kt",
        prefix = "package data_class\n\n"
    )
}

fun Schema.generateAsSerializableKotlinDataClass() {
    generateUsingTemplate(
        outputDir = "kotlin/models/serializable_data_class",
        template = "schema/kotlin/object_as_serializable_data_class.kate",
        extension = ".kt",
        prefix = "package data_class\n\n"
    )
}

fun Schema.generateAsOverridableInterface() {
    generateUsingTemplate(
        outputDir = "kotlin/models/overridden",
        template = "schema/kotlin/object_as_data_class_overriding_interface.kate",
        extension = ".kt",
        prefix = "package overridden\n\n"
    )
}

fun Schema.generateAsOverridableSerializableInterface() {
    generateUsingTemplate(
        outputDir = "kotlin/models/overridden_serializable",
        template = "schema/kotlin/object_as_serializable_data_class_overriding_interface.kate",
        extension = ".kt",
        prefix = "package overridden_serializable\n\n"
    )
}

fun Schema.generateAsGolangStructs() {
    generateUsingTemplate(
        outputDir = "golang/models/struct",
        template = "schema/golang/object_as_go_struct.kate",
        extension = ".go",
        prefix = "package main\n\n"
    )
}

fun Schema.generateAsJson() {
    generateUsingTemplate(
        outputDir = "json/simple",
        template = "schema/json/object_as_json.kate",
        extension = ".json",
        prefix = "",
        allowNested = true
    )
}

fun Schema.generateAsHtml() {
    generateUsingTemplate(
        outputDir = "html/models",
        template = "schema/html/object_as_html.kate",
        extension = ".html",
        prefix = ""
    )
}