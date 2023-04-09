package com.wakaztahir.openapi

import com.reprezen.kaizen.oasparser.model3.Operation
import com.reprezen.kaizen.oasparser.model3.Path
import com.wakaztahir.kate.model.model.MutableKATEObject

fun Operation.toMutableKATEObject(method: String, path: String): MutableKATEObject {
    return MutableKATEObject {
        putValue("method", method)
        putValue("path", path)
    }
}

fun Path.toRouteList(): List<MutableKATEObject> {
    return getOperations().map { op ->
        op.value.toMutableKATEObject(method = op.key, path = getPathString()!!)
    }
}