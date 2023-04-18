package com.wakaztahir.openapi.template.schema

import com.reprezen.kaizen.oasparser.model3.Schema

fun Collection<Schema>.generateAsKotlinInterface() {
    generateMultipleFromTemplate(
        outputFile = "kotlin/models/interface/All.kt",
        template = "schema/kotlin/object_as_interface.kate",
        separator = "\n\n",
        allowNested = false
    )
}


fun Collection<Schema>.generateAsKotlinDataClass() {
    generateMultipleFromTemplate(
        outputFile = "kotlin/models/data_class/All.kt",
        template = "schema/kotlin/object_as_data_class.kate",
        separator = "\n\n",
        allowNested = false,
    )
}

fun Collection<Schema>.generateAsSerializableKotlinDataClass() {
    generateMultipleFromTemplate(
        outputFile = "kotlin/models/serializable_data_class/All.kt",
        template = "schema/kotlin/object_as_serializable_data_class.kate",
        separator = "\n\n",
        allowNested = false,
    )
}

fun Collection<Schema>.generateAsOverridableInterface() {
    generateMultipleFromTemplate(
        outputFile = "kotlin/models/overridden/All.kt",
        template = "schema/kotlin/object_as_data_class_overriding_interface.kate",
        separator = "\n\n",
        allowNested = false,
    )
}

fun Collection<Schema>.generateAsOverridableSerializableInterface() {
    generateMultipleFromTemplate(
        outputFile = "kotlin/models/overridden_serializable/All.kt",
        template = "schema/kotlin/object_as_serializable_data_class_overriding_interface.kate",
        separator = "\n\n",
        allowNested = false,
    )
}

fun Collection<Schema>.generateAsGolangStructs() {
    generateMultipleFromTemplate(
        outputFile = "golang/models/struct/All.go",
        template = "schema/golang/object_as_go_struct.kate",
        separator = "\n\n",
        allowNested = false,
    )
}

fun Collection<Schema>.generateAsJson() {
    generateMultipleFromTemplate(
        outputFile = "json/simple/All.json",
        template = "schema/json/object_as_json.kate",
        separator = ",\n",
        allowNested = true,
        prefix = "[",
        suffix = "]"
    )
}

fun Collection<Schema>.generateAsHtml() {
    generateMultipleFromTemplate(
        outputFile = "html/models/All.html",
        template = "schema/html/object_as_html.kate",
        separator = "\n\n",
        allowNested = false
    )
}