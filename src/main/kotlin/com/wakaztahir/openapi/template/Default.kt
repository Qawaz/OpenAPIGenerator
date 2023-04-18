package com.wakaztahir.openapi.template

import com.wakaztahir.kate.OutputDestinationStream
import com.wakaztahir.kate.RelativeResourceEmbeddingManager
import com.wakaztahir.kate.model.model.KATEObject
import com.wakaztahir.kate.model.model.KATEValue
import com.wakaztahir.kate.model.model.MutableKATEObject
import com.wakaztahir.kate.parser.stream.TextSourceStream
import java.io.File

fun KATEValue.generateFromTemplate(
    name: String,
    template: String,
    prefix: String,
    output: File
) {
    val source = TextSourceStream(
        sourceCode = """@partial_raw @embed_once ./$template${"\n"}@default_no_raw $prefix@var(${name}) @end_default_no_raw @end_partial_raw""",
        model = MutableKATEObject { putValue(name, this@generateFromTemplate) }.also {
//            println("Object $name , Template $template , Extension $extension")
//            println(it)
        },
        embeddingManager = RelativeResourceEmbeddingManager(basePath = "/", classLoader = object {}.javaClass)
    )
    val stream = output.outputStream()
    source.generateTo(OutputDestinationStream(stream))
    stream.close()
}

fun List<KATEObject>.generateMultipleFromTemplate(
    output: File,
    template: String,
    separator: String?,
    prefix: String = "",
    suffix: String = ""
) {
    val model = MutableKATEObject {
        for (obj in this@generateMultipleFromTemplate) {
            putValue(obj.objectName, obj)
        }
    }
    var sourceCode = "@partial_raw @embed_once ./$template\n@runtime.print_string(\"$prefix\")"
    var i = 0
    while (i < size) {
        val obj = get(i)
        sourceCode += "@default_no_raw @var(${obj.objectName}) @end_default_no_raw"
        if (separator != null && i != size - 1) {
            sourceCode += "@runtime.print_string(\"$separator\")"
        }
        i++
    }
    sourceCode += "@runtime.print_string(\"$suffix\")@end_partial_raw"
    val source = TextSourceStream(
        sourceCode = sourceCode,
        model = model,
        embeddingManager = RelativeResourceEmbeddingManager(basePath = "/", classLoader = object {}.javaClass)
    )
    val stream = output.outputStream()
    source.generateTo(OutputDestinationStream(stream))
    stream.close()
}
