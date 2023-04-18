package com.wakaztahir.openapi.template.path.operation

import com.reprezen.kaizen.oasparser.model3.Operation

fun Collection<Operation>.generateAsHtml(path: String) {
    generateUsingTemplate(
        path = path,
        outputDir = "html",
        template = "./schema/html/path_as_html.kate.html",
        prefix = ""
    )
}