package com.wakaztahir.openapi.template

import com.wakaztahir.kate.OutputDestinationStream
import com.wakaztahir.kate.RelativeResourceEmbeddingManager
import com.wakaztahir.kate.model.PlaceholderInvocation
import com.wakaztahir.kate.model.StringValue
import com.wakaztahir.kate.model.model.*
import com.wakaztahir.kate.parser.stream.*
import com.wakaztahir.kate.runtime.StringImplementation
import java.io.File
import java.io.OutputStream

class ChangeableDestinationStream(stream: WritableStream) : DestinationStream {

    override var stream: WritableStream = stream
        private set

    fun updateStream(stream: WritableStream) {
        if (stream is OutputStream) stream.close()
        this.stream = stream
    }
}

fun MutableKATEObject.putStreamPlaceholderFunction(
    folder: File,
    destination: ChangeableDestinationStream
) {
    putValue("set_stream", object : KATEFunction() {
        override fun invoke(model: KATEObject, invokedOn: KATEValue, parameters: List<ReferencedValue>): KATEValue {
            require(parameters.size == 1) {
                "parameters size must be 1 for stream_placeholder"
            }
            val path = parameters.getOrNull(0)?.asNullablePrimitive(model)?.let { it as? StringValue }
            require(path != null) { "${toString()} stream path cannot be null" }
            val file = folder.resolve(path.value)
            val parentFolder = file.parentFile
            if (parentFolder != folder) parentFolder.mkdirs()
            val outputStream = file.outputStream()
            destination.updateStream(object : WritableStream {
                override fun write(char: Char) {
                    outputStream.write(char.code)
                }

                override fun write(str: String) {
                    for (char in str) outputStream.write(char.code)
                }
            })
            return KATEUnit
        }

        override fun toString(): String = "set_stream(path : string)"
    })
}

fun MutableKATEObject.generateMultiFileTemplate(template: String, outputDir: File) {
    val source = TextSourceStream(
        sourceCode = """@partial_raw @embed_once ./$template${'\n'} @end_partial_raw""",
        model = this,
        embeddingManager = RelativeResourceEmbeddingManager(basePath = "/", classLoader = object {}.javaClass)
    )
    var value = ""
    val destination = ChangeableDestinationStream(object : WritableStream {
        override fun write(char: Char) {
            value += char
        }

        override fun write(str: String) {
            value += str
        }
    })
    putStreamPlaceholderFunction(folder = outputDir, destination = destination)
    StringImplementation.propertyMap["removePrefix"] = object : KATEFunction() {
        override fun invoke(model: KATEObject, invokedOn: KATEValue, parameters: List<ReferencedValue>): KATEValue {
            val param = parameters.getOrNull(0)?.asNullablePrimitive(model)?.value as? String
            require(param != null) { "removePrefix expects a valid string value as parameter" }
            return StringValue((invokedOn as StringValue).value.removePrefix(param))
        }

        override fun toString(): String = "removePrefix(value : string) : string"
    }
    source.generateTo(destination)
    println(value)
}