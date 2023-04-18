package com.wakaztahir.openapi

import com.reprezen.jsonoverlay.JsonOverlay
import com.reprezen.kaizen.oasparser.model3.*
import com.wakaztahir.kate.model.model.KATEList
import com.wakaztahir.kate.model.model.KATEListImpl
import com.wakaztahir.kate.model.model.MutableKATEObject

fun MediaType.toMutableKATEObject(): MutableKATEObject {
    return MutableKATEObject {
        getSchema()?.toKATEValue(allowNested = false)?.let { putValue("schema", it) }
    }
}

fun Map<String, MediaType>.mediaTypesObject(): MutableKATEObject {
    return MutableKATEObject {
        for (item in this@mediaTypesObject) {
            putValue(item.key, item.value.toMutableKATEObject())
        }
    }
}

fun Response.toMutableKATEObject(statusCode: String): MutableKATEObject {
    return MutableKATEObject {
        putValue("statusCode", statusCode)
        putValue("description", getDescription() ?: "")
        putValue("example", "NO_EXAMPLE_YET")
        putValue("mediaTypes", getContentMediaTypes().mediaTypesObject())
    }
}

fun RequestBody.toMutableKATEObject(): MutableKATEObject {
    return MutableKATEObject {
        getName()?.let { putValue("name", it) }
        getDescription()?.let { putValue("description", it) }
        putValue("mediaTypes", getContentMediaTypes().mediaTypesObject())
        getRequired()?.let { putValue("required", it) }
    }
}

fun Operation.toMutableKATEObject(method: String): MutableKATEObject {
    return MutableKATEObject {
        putValue("method", method)
        getDescription()?.let { putValue("description", it) }
        getSummary()?.let { putValue("summary", it) }
        getOperationId()?.let { putValue("operationId", it) }
        getRequestBody()?.toMutableKATEObject()?.let { putValue("requestBody", it) }
        putValue("responses", KATEListImpl(getResponses().map { it.value.toMutableKATEObject(statusCode = it.key) }))
    }
}

fun Collection<Operation>.toMutableKATEObject(path: String): MutableKATEObject {
    return MutableKATEObject {
        putValue("path", path)
        putValue("operations", KATEListImpl(map { op ->
            op.toMutableKATEObject(
                method = (op as JsonOverlay<*>)._getPathInParent()
            )
        }))
    }
}

fun Example.toMutableKATEObject(key: String): MutableKATEObject {
    return MutableKATEObject {
        putValue("key", key)
        getName()?.let { putValue("name", it) }
        getDescription()?.let { putValue("description", it) }
        getSummary()?.let { putValue("summary", it) }
        getExternalValue()?.let { putValue("externalValue", it) }
    }
}

fun Map<String, Example>.toKATEList(): KATEListImpl<MutableKATEObject> {
    return KATEListImpl(map { it.value.toMutableKATEObject(key = it.key) })
}

fun Parameter.toMutableKATEObject(): MutableKATEObject {
    return MutableKATEObject {
        getName()?.let { putValue("name", it) }
        getDescription()?.let { putValue("description", it) }
        getRequired()?.let { putValue("required", it) }
        getIn()?.let { putValue("in", it) }
        getAllowEmptyValue()?.let { putValue("allowEmptyValue", it) }
        getAllowReserved()?.let { putValue("allowReserved", it) }
        getDeprecated()?.let { putValue("deprecated", it) }
        putValue("mediaTypes", getContentMediaTypes().mediaTypesObject())
        getExplode()?.let { putValue("explode", it) }
        getKey()?.let { putValue("key", it) }
        getStyle()?.let { putValue("style", it) }
        getSchema()?.let { putValue("schema", it.toKATEValue(allowNested = true)) }
        putValue("examples", getExamples().toKATEList())
    }
}

fun Path.toMutableKATEObject(): MutableKATEObject {
    this.getParameters()
    return MutableKATEObject {
        putValue("path", getPathString()!!)
        getDescription()?.let { putValue("description", it) }
        getSummary()?.let { putValue("summary", it) }
        putValue("parameters", KATEListImpl(getParameters().map { it.toMutableKATEObject() }))
        putValue("operations", getOperations().values.toMutableKATEObject(path = getPathString()!!))
    }
}