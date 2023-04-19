package com.wakaztahir.openapi.template.path.operation

import com.reprezen.jsonoverlay.Overlay
import com.reprezen.kaizen.oasparser.model3.Operation
import com.wakaztahir.openapi.template.generateFromTemplate
import com.wakaztahir.openapi.toMutableKATEObject
import java.io.File

fun Operation.generateUsingTemplate(
    outputDir: String,
    template: String,
    extension: String,
    prefix: String,
) {
    val method = Overlay.of(this).pathInParent!!
    toMutableKATEObject(method = method).generateFromTemplate(
        name = "Operation",
        template = template,
        prefix = prefix,
        output = File("output/$outputDir").let {
            it.mkdirs()
            it.resolve("$method$extension")
        }
    )
}

fun Collection<Operation>.generateUsingTemplate(
    path : String,
    outputDir: String,
    template: String,
    prefix: String,
) {
    toMutableKATEObject(path = path).generateFromTemplate(
        name = "MultipleOperations",
        template = template,
        prefix = prefix,
        output = File("output/$outputDir/paths/${path.removePrefix("/").replace('/', '_')}/").let {
            it.mkdirs()
            it.resolve("All.html")
        }
    )
}