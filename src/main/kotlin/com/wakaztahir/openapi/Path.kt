package com.wakaztahir.openapi

import com.reprezen.kaizen.oasparser.model3.*
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

fun Operation.toMutableKATEObject(method: String): MutableKATEObject {
    return MutableKATEObject {
        putValue("method", method)
        putValue("description", getDescription() ?: "")
        putValue("summary", getSummary() ?: "")
        putValue("operationId", getOperationId() ?: "")
        putValue("responses", KATEListImpl(getResponses().map { it.value.toMutableKATEObject(statusCode = it.key) }))
    }
}

fun Map<String, Operation>.toMutableKATEObject(): MutableKATEObject {
    return MutableKATEObject {
        putValue("operations", KATEListImpl(map { op ->
            op.value.toMutableKATEObject(
                method = op.key
            )
        }))
    }
}