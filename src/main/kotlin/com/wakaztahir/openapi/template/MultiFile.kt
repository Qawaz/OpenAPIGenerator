package com.wakaztahir.openapi.template

import com.wakaztahir.kate.RelativeFileEmbeddingManager
import com.wakaztahir.kate.model.KATEType
import com.wakaztahir.kate.model.StringValue
import com.wakaztahir.kate.model.model.*
import com.wakaztahir.kate.parser.stream.DestinationStream
import com.wakaztahir.kate.parser.stream.TextSourceStream
import com.wakaztahir.kate.parser.stream.WritableStream
import java.io.File

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
            val path = parameters.getOrNull(0)?.asNullablePrimitive()?.let { it as? StringValue }
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

fun MutableKATEObject.generateMultiFileTemplate(templatesDir : String,template: String, outputDir: File) {
    val source = TextSourceStream(
        sourceCode = """@partial_raw @embed_once ./$template${'\n'} @end_partial_raw""",
        model = this,
        embeddingManager = RelativeFileEmbeddingManager(File(templatesDir))
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
    source.block.parse().generateTo(destination)
    println(value)
}