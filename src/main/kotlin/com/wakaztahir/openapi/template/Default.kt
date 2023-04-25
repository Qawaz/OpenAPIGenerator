package com.wakaztahir.openapi.template

import com.reprezen.kaizen.oasparser.model3.OpenApi3
import com.reprezen.kaizen.oasparser.model3.Operation
import com.reprezen.kaizen.oasparser.model3.Path
import com.reprezen.kaizen.oasparser.model3.Schema
import com.wakaztahir.kate.OutputDestinationStream
import com.wakaztahir.kate.RelativeResourceEmbeddingManager
import com.wakaztahir.kate.model.model.KATEObject
import com.wakaztahir.kate.model.model.KATEValue
import com.wakaztahir.kate.model.model.MutableKATEObject
import com.wakaztahir.kate.parser.stream.TextSourceStream
import com.wakaztahir.kate.parser.stream.getErrorInfoAtCurrentPointer
import com.wakaztahir.kate.parser.stream.printErrorLineNumberAndCharacterIndex
import java.io.File

fun KATEValue.generateFromTemplate(
    name: String,
    template: String,
    prefix: String,
    output: File
) {
    val source = TextSourceStream(
        sourceCode = """@partial_raw @embed_once ./$template${"\n"}@default_no_raw $prefix@var(${name}) @end_default_no_raw @end_partial_raw""",
        model = MutableKATEObject { insertValue(name, this@generateFromTemplate) }.also {
//            println("Object $name , Template $template , Extension $extension")
//            println(it)
        },
        embeddingManager = RelativeResourceEmbeddingManager(basePath = "/", classLoader = object {}.javaClass)
    )
    try {
        val stream = output.outputStream()
        source.generateTo(OutputDestinationStream(stream))
        stream.close()
    }catch (e : Exception){
        println("ERROR IN $template OCCURRED AT : ")
        source.printErrorLineNumberAndCharacterIndex()
        e.printStackTrace()
    }
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
            insertValue(obj.objectName, obj)
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

fun OpenApi3.generateForSingleLanguage(
    generateSingleSchema: (Schema) -> Unit,
    generateSchemaColl: (Collection<Schema>) -> Unit,
    generateSingleOperation: (Operation, String) -> Unit,
    generateOperationColl: (Collection<Operation>, String) -> Unit,
    generateSinglePath: (Path) -> Unit,
    generatePathColl: (Collection<Path>) -> Unit,
    generateCompleteSpec: (OpenApi3) -> Unit
) {

    // each schema into its own file for its language
    for (schema in getSchemas()) {
        generateSingleSchema(schema.value)
    }

    // all schemas into a single file
    generateSchemaColl(getSchemas().values)

    for (path in getPaths()) {

        // each operation of path into its own file
        for (operation in path.value.getOperations()) {
            generateSingleOperation(operation.value, path.value.getPathString()!!)
        }

        // all operations of path into a single
        generateOperationColl(path.value.getOperations().values, path.value.getPathString()!!)

        // each path into its own file
        generateSinglePath(path.value)

    }

    // all paths into single file
    generatePathColl(getPaths().values)

    // whole spec into a single file
    generateCompleteSpec(this)

}