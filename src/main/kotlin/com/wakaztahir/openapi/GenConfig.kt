package com.wakaztahir.openapi

import com.wakaztahir.kate.InputSourceStream
import com.wakaztahir.kate.model.model.MutableKATEObject
import com.wakaztahir.kate.parser.stream.TextDestinationStream
import com.wakaztahir.kate.parser.stream.TextSourceStream
import java.io.File

internal const val DefaultConfig = """
    @var generationType = "golang-server"
    @var outputDir = "/"
"""

internal const val GolangDefaultConfig = """
    $DefaultConfig
    @define_object(golang)
        @var pkgName = "server"
    @end_define_object
"""

fun loadConfigurationObject(configFile: File, langGen: LanguageGeneration): MutableKATEObject {
    val input = if (configFile.exists()) configFile.inputStream() else null
    val configuration = if (configFile.exists()) {
        configFile.inputStream().use { it.reader().use { r -> r.readText() } }
    } else {
        langGen.defaultString()
    }
    if (!configFile.exists()) {
        configFile.outputStream().use { it.writer().use { w -> w.write(langGen.defaultString()) } }
    }
    val stream = TextSourceStream(sourceCode = configuration)
    stream.block.generateTo(TextDestinationStream())
    input?.close()
    return stream.model
}