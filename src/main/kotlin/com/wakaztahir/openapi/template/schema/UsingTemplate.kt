package com.wakaztahir.openapi.template.schema

import com.reprezen.kaizen.oasparser.model3.Schema
import com.wakaztahir.kate.model.model.MutableKATEObject
import com.wakaztahir.openapi.template.generateFromTemplate
import com.wakaztahir.openapi.template.generateMultipleFromTemplate
import com.wakaztahir.openapi.toKATEValue
import java.io.File

fun Schema.generateUsingTemplate(
    outputDir: String,
    template: String,
    extension: String,
    prefix: String,
    allowNested: Boolean
) {
    val name = getName()!!
    toKATEValue(allowNested = allowNested).generateFromTemplate(
        name = name,
        template = template,
        prefix = prefix,
        output = File("output/$outputDir/${name}$extension")
    )
}

fun Collection<Schema>.generateMultipleFromTemplate(
    outputFile: String,
    template: String,
    separator : String?,
    allowNested: Boolean,
    prefix : String = "",
    suffix : String = ""
) {
    map { it.toKATEValue(allowNested = allowNested) as MutableKATEObject }.generateMultipleFromTemplate(
        output = File("output/$outputFile"),
        template = template,
        separator = separator,
        prefix = prefix,
        suffix = suffix,
    )
}