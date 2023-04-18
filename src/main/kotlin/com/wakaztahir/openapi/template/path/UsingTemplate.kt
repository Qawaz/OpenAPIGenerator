package com.wakaztahir.openapi.template.path

import com.reprezen.kaizen.oasparser.model3.Path
import com.wakaztahir.openapi.template.generateFromTemplate
import com.wakaztahir.openapi.template.generateMultipleFromTemplate
import com.wakaztahir.openapi.toMutableKATEObject
import java.io.File

fun Path.generateUsingTemplate(
    outputDir: String,
    template: String,
    extension: String,
    prefix: String,
) {
    toMutableKATEObject().generateFromTemplate(
        name = "Path",
        template = template,
        prefix = prefix,
        output = File("output/$outputDir/${getPathString()!!.removePrefix("/").replace('/', '_')}$extension")
    )
}

fun Collection<Path>.generateMultipleFromTemplate(
    outputFile: String,
    template: String,
    separator: String?
) {
    map { it.toMutableKATEObject() }.generateMultipleFromTemplate(
        output = File("output/$outputFile"),
        template = template,
        separator = separator
    )
}