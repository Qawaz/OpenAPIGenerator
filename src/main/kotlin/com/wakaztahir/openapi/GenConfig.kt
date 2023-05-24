package com.wakaztahir.openapi

import com.wakaztahir.kate.model.model.MutableKATEObject
import com.wakaztahir.kate.parser.stream.TextDestinationStream
import com.wakaztahir.kate.parser.stream.TextSourceStream
import java.io.File

internal const val DefaultConfig = """
    @var ignore : list<string> = @list()
"""

internal const val GolangDefaultConfig = """
    @define_object(golang)
        @var pkgName = "server"
    @end_define_object
"""

fun loadConfigurationObject(
    configFile: File,
    forceDefault: Boolean,
    forceWriteDefault: Boolean,
    configString: () -> String
): MutableKATEObject {
    val input = if (configFile.exists()) configFile.inputStream() else null
    val configuration = if (configFile.exists() && !forceDefault) {
        configFile.inputStream().use { it.reader().use { r -> r.readText() } }
    } else {
        configString()
    }
    if (!configFile.exists() || forceWriteDefault) {
        configFile.outputStream().use { it.writer().use { w -> w.write(configString()) } }
    }
    val stream = TextSourceStream(sourceCode = configuration)
    stream.block.parse().generateTo(TextDestinationStream())
    input?.close()
    return stream.model
}