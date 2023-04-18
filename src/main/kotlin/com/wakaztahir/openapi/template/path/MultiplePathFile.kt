package com.wakaztahir.openapi.template.path

import com.reprezen.kaizen.oasparser.model3.Path

fun Collection<Path>.generateAsHtml() {
    generateMultipleFromTemplate(
        outputFile = "html/paths/All.html",
        template = "schema/html/path_as_html.kate.html",
        separator = null
    )
}