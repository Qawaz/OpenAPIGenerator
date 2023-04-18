package com.wakaztahir.openapi.template.path

import com.reprezen.kaizen.oasparser.model3.Path

fun Path.generateAsHtml() {
    generateUsingTemplate(
        outputDir = "html/paths",
        template = "schema/html/path_as_html.kate.html",
        extension = ".html",
        prefix = ""
    )
}