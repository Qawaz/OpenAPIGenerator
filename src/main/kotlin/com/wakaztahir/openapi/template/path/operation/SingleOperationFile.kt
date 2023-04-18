package com.wakaztahir.openapi.template.path.operation

import com.reprezen.kaizen.oasparser.model3.Operation

fun Operation.generateAsHtml(path: String) {
    generateUsingTemplate(
        outputDir = "html/paths/${path.removePrefix("/").replace('/', '_')}",
        template = "./schema/html/operation_as_html.kate.html",
        extension = ".html",
        prefix = ""
    )
}
