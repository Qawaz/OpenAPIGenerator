package com.wakaztahir.openapi.template

import com.wakaztahir.kate.OutputDestinationStream
import com.wakaztahir.kate.RelativeResourceEmbeddingManager
import com.wakaztahir.kate.model.KATEType
import com.wakaztahir.kate.model.ModelReference
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
        this.stream = stream
    }
}

fun MutableKATEObject.putStreamPlaceholderFunction(
    folder: File,
    destination: ChangeableDestinationStream
) {
    insertValue("set_stream", object : KATEFunction(returnedType = KATEType.Unit,KATEType.String) {
        override fun invoke(
            model: KATEObject,
            invokedOn: KATEValue,
            explicitType : KATEType?,
            parameters: List<ReferencedOrDirectValue>
        ): ReferencedOrDirectValue {
            require(parameters.size == 1) {
                "parameters size must be 1 for set_stream"
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
    source.generateTo(destination)
    println(value)
}