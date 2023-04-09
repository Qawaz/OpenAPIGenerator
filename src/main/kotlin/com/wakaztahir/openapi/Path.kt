package com.wakaztahir.openapi

import com.reprezen.kaizen.oasparser.model3.*
import com.wakaztahir.kate.model.model.KATEListImpl
import com.wakaztahir.kate.model.model.MutableKATEObject

fun MediaType.toMutableKATEObject(): MutableKATEObject {
    return MutableKATEObject {
        getSchema()?.toKATEValue(allowNested = false)?.let { putValue("schema", it) }
    }
}

fun Map<String, MediaType>.toMutableKATEObject(): MutableKATEObject {
    return MutableKATEObject {
        for (item in this@toMutableKATEObject) {
            putValue(item.key, item.value.toMutableKATEObject())
        }
    }
}

fun Response.toMutableKATEObject(statusCode: String): MutableKATEObject {
    return MutableKATEObject {
        putValue("statusCode", statusCode)
        putValue("description", getDescription() ?: "")
        putValue("example", "NO_EXAMPLE_YET")
        putValue("mediaTypes", getContentMediaTypes().toMutableKATEObject())
    }
}

fun Operation.toMutableKATEObject(method: String, path: String): MutableKATEObject {
    return MutableKATEObject {
        putValue("method", method)
        putValue("path", path)
        putValue("description", getDescription() ?: "")
        putValue("summary", getSummary() ?: "")
        putValue("operationId", getOperationId() ?: "")
        putValue("responses", KATEListImpl(getResponses().map { it.value.toMutableKATEObject(statusCode = it.key) }))
    }
}

fun Path.toRouteList(): List<MutableKATEObject> {
    return getOperations().map { op ->
        op.value.toMutableKATEObject(method = op.key, path = getPathString()!!)
    }
}