package com.wakaztahir.openapi

import com.reprezen.jsonoverlay.JsonOverlay
import com.reprezen.kaizen.oasparser.model3.*
import com.wakaztahir.kate.model.KATEType
import com.wakaztahir.kate.model.StringValue
import com.wakaztahir.kate.model.model.KATEListImpl
import com.wakaztahir.kate.model.model.MutableKATEObject

fun MediaType.toMutableKATEObject(): MutableKATEObject {
    return MutableKATEObject {
        getSchema()?.toKATEValue(allowNested = false)?.let { setValue("schema", it) }
    }
}

fun Map<String, MediaType>.mediaTypesObject(): MutableKATEObject {
    return MutableKATEObject {
        for (item in this@mediaTypesObject) {
            setValue(item.key, item.value.toMutableKATEObject())
        }
    }
}

fun Response.toMutableKATEObject(statusCode: String): MutableKATEObject {
    return MutableKATEObject {
        setValue("statusCode", statusCode)
        setValue("description", getDescription() ?: "")
        setValue("example", "NO_EXAMPLE_YET")
        setValue("mediaTypes", getContentMediaTypes().mediaTypesObject())
    }
}

fun RequestBody.toMutableKATEObject(): MutableKATEObject {
    return MutableKATEObject {
        getName()?.let { setValue("name", it) }
        getDescription()?.let { setValue("description", it) }
        setValue("mediaTypes", getContentMediaTypes().mediaTypesObject())
        getRequired()?.let { setValue("required", it) }
    }
}

fun SecurityParameter.toKATEList(): KATEListImpl<StringValue> {
    return KATEListImpl(this.getParameters().map { StringValue(it) }, itemType = KATEType.String)
}

fun SecurityRequirement.toMutableKATEObject(): MutableKATEObject {
    return MutableKATEObject {
        setValue("requirements", MutableKATEObject {
            for (requirement in getRequirements()) {
                setValue(requirement.key, requirement.value.toKATEList())
            }
        })
    }
}

fun Operation.toMutableKATEObject(method: String): MutableKATEObject {
    return MutableKATEObject {
        setValue("method", method)
        getDescription()?.let { setValue("description", it) }
        getSummary()?.let { setValue("summary", it) }
        getOperationId()?.let { setValue("operationId", it) }
        getRequestBody()?.toMutableKATEObject()?.let { setValue("requestBody", it) }
        setValue("responses", KATEListImpl(getResponses().map { it.value.toMutableKATEObject(statusCode = it.key) },itemType = KATEType.Object(KATEType.Any)))
        setValue("security", KATEListImpl(getSecurityRequirements().map { it.toMutableKATEObject() },itemType = KATEType.Object(KATEType.Any)))
    }
}

fun Collection<Operation>.toKATEList(): KATEListImpl<*> {
    return KATEListImpl(map { op -> op.toMutableKATEObject(method = (op as JsonOverlay<*>)._getPathInParent()) },itemType = KATEType.Object(KATEType.Any))
}

fun Collection<Operation>.toMutableKATEObject(path: String): MutableKATEObject {
    return MutableKATEObject {
        setValue("path", path)
        setValue("parameters", KATEListImpl(emptyList(),itemType = KATEType.Any))
        setValue("operations", toKATEList())
    }
}

fun Example.toMutableKATEObject(key: String): MutableKATEObject {
    return MutableKATEObject {
        setValue("key", key)
        getName()?.let { setValue("name", it) }
        getDescription()?.let { setValue("description", it) }
        getSummary()?.let { setValue("summary", it) }
        getExternalValue()?.let { setValue("externalValue", it) }
    }
}

fun Map<String, Example>.toKATEList(): KATEListImpl<MutableKATEObject> {
    return KATEListImpl(map { it.value.toMutableKATEObject(key = it.key) },itemType = KATEType.Object(KATEType.Any))
}

fun Parameter.toMutableKATEObject(): MutableKATEObject {
    return MutableKATEObject {
        getName()?.let { setValue("name", it) }
        getDescription()?.let { setValue("description", it) }
        getRequired()?.let { setValue("required", it) }
        getIn()?.let { setValue("in", it) }
        getAllowEmptyValue()?.let { setValue("allowEmptyValue", it) }
        getAllowReserved()?.let { setValue("allowReserved", it) }
        getDeprecated()?.let { setValue("deprecated", it) }
        setValue("mediaTypes", getContentMediaTypes().mediaTypesObject())
        getExplode()?.let { setValue("explode", it) }
        getKey()?.let { setValue("key", it) }
        getStyle()?.let { setValue("style", it) }
        getSchema()?.let { setValue("schema", it.toKATEValue(allowNested = true)) }
        setValue("examples", getExamples().toKATEList())
    }
}

fun Path.toMutableKATEObject(): MutableKATEObject {
    return MutableKATEObject {
        setValue("path", getPathString()!!)
        getDescription()?.let { setValue("description", it) }
        getSummary()?.let { setValue("summary", it) }
        setValue("parameters", KATEListImpl(getParameters().map { it.toMutableKATEObject() },itemType = KATEType.Object(KATEType.Any)))
        setValue("operations", getOperations().values.toKATEList())
    }
}