package com.wakaztahir.openapi

import com.reprezen.jsonoverlay.JsonOverlay
import com.reprezen.jsonoverlay.Overlay
import com.reprezen.jsonoverlay.PropertiesOverlay
import com.reprezen.kaizen.oasparser.model3.*
import com.reprezen.kaizen.oasparser.ovl3.MediaTypeImpl
import com.wakaztahir.kate.model.KATEType
import com.wakaztahir.kate.model.StringValue
import com.wakaztahir.kate.model.model.*

fun MediaType.toMutableKATEObject(): MutableKATEObject {
    return MutableKATEObject {
        getSchema()?.toKATEValue(allowNested = false)?.let { insertValue("schema", it) }
    }
}

fun Map<String, MediaType>.mediaTypesObject(): MutableKATEObject {
    return MutableKATEObject {
        for (item in this@mediaTypesObject) {
            insertValue(item.key, item.value.toMutableKATEObject())
        }
    }
}

fun Response.toMutableKATEObject(statusCode: String): MutableKATEObject {
    return MutableKATEObject {
        insertValue("statusCode", statusCode)
        insertValue("description", getDescription() ?: "")
        insertValue("example", "NO_EXAMPLE_YET")
        insertValue("mediaTypes", getContentMediaTypes().mediaTypesObject())
        insertValue("getSchemaRefName", object : KATEFunction(KATEType.Boolean, KATEType.String) {
            override fun invoke(
                model: KATEObject,
                invokedOn: KATEValue,
                explicitType: KATEType?,
                parameters: List<ReferencedOrDirectValue>
            ): ReferencedOrDirectValue {
                val key = (parameters[0].getKATEValue(model) as StringValue).value
                val mediaType = getContentMediaType(key)!! as PropertiesOverlay<*>
                return StringValue(
                    mediaType._getValueOverlayByPath("schema")!!._getReference()?.pointer?.segments?.lastOrNull() ?: ""
                )
            }
        })
    }
}

fun RequestBody.toMutableKATEObject(): MutableKATEObject {
    return MutableKATEObject {
        getName()?.let { insertValue("name", it) }
        getDescription()?.let { insertValue("description", it) }
        insertValue("mediaTypes", getContentMediaTypes().mediaTypesObject())
        getRequired()?.let { insertValue("required", it) }
        insertValue("getSchemaRefName", object : KATEFunction(KATEType.Boolean, KATEType.String) {
            override fun invoke(
                model: KATEObject,
                invokedOn: KATEValue,
                explicitType: KATEType?,
                parameters: List<ReferencedOrDirectValue>
            ): ReferencedOrDirectValue {
                val key = (parameters[0].getKATEValue(model) as StringValue).value
                val mediaType = getContentMediaType(key)!! as PropertiesOverlay<*>
                return StringValue(
                    mediaType._getValueOverlayByPath("schema")!!._getReference()?.pointer?.segments?.lastOrNull() ?: ""
                )
            }
        })
    }
}

fun SecurityParameter.toKATEList(): KATEListImpl<StringValue> {
    return KATEListImpl(this.getParameters().map { StringValue(it) }, itemType = KATEType.String)
}

fun SecurityRequirement.toMutableKATEObject(): MutableKATEObject {
    return MutableKATEObject {
        insertValue("requirements", MutableKATEObject {
            for (requirement in getRequirements()) {
                insertValue(requirement.key, requirement.value.toKATEList())
            }
        })
    }
}

fun Operation.toMutableKATEObject(method: String): MutableKATEObject {
    return MutableKATEObject {
        insertValue("method", method)
        getDescription()?.let { insertValue("description", it) }
        getSummary()?.let { insertValue("summary", it) }
        getOperationId()?.let { insertValue("operationId", it) }
        getRequestBody()?.toMutableKATEObject()?.let { insertValue("requestBody", it) }
        insertValue(
            "responses",
            KATEListImpl(
                getResponses().map { it.value.toMutableKATEObject(statusCode = it.key) },
                itemType = KATEType.Object(KATEType.Any)
            )
        )
        insertValue(
            "security",
            KATEListImpl(
                getSecurityRequirements().map { it.toMutableKATEObject() },
                itemType = KATEType.Object(KATEType.Any)
            )
        )
    }
}

fun Collection<Operation>.toKATEList(): KATEListImpl<*> {
    return KATEListImpl(
        map { op -> op.toMutableKATEObject(method = (op as JsonOverlay<*>)._getPathInParent()) },
        itemType = KATEType.Object(KATEType.Any)
    )
}

fun Collection<Operation>.toMutableKATEObject(path: String): MutableKATEObject {
    return MutableKATEObject {
        insertValue("path", path)
        insertValue("parameters", KATEListImpl(emptyList(), itemType = KATEType.Any))
        insertValue("operations", toKATEList())
    }
}

fun Example.toMutableKATEObject(key: String): MutableKATEObject {
    return MutableKATEObject {
        insertValue("key", key)
        getName()?.let { insertValue("name", it) }
        getDescription()?.let { insertValue("description", it) }
        getSummary()?.let { insertValue("summary", it) }
        getExternalValue()?.let { insertValue("externalValue", it) }
    }
}

fun Map<String, Example>.toKATEList(): KATEListImpl<MutableKATEObject> {
    return KATEListImpl(map { it.value.toMutableKATEObject(key = it.key) }, itemType = KATEType.Object(KATEType.Any))
}

fun Parameter.toMutableKATEObject(): MutableKATEObject {
    return MutableKATEObject {
        getName()?.let { insertValue("name", it) }
        getDescription()?.let { insertValue("description", it) }
        getRequired()?.let { insertValue("required", it) }
        getIn()?.let { insertValue("in", it) }
        getAllowEmptyValue()?.let { insertValue("allowEmptyValue", it) }
        getAllowReserved()?.let { insertValue("allowReserved", it) }
        getDeprecated()?.let { insertValue("deprecated", it) }
        insertValue("mediaTypes", getContentMediaTypes().mediaTypesObject())
        getExplode()?.let { insertValue("explode", it) }
        getKey()?.let { insertValue("key", it) }
        getStyle()?.let { insertValue("style", it) }
        getSchema()?.let { insertValue("schema", it.toKATEValue(allowNested = true)) }
        insertValue("examples", getExamples().toKATEList())
    }
}

fun Path.toMutableKATEObject(): MutableKATEObject {
    return MutableKATEObject {
        insertValue("path", getPathString()!!)
        getDescription()?.let { insertValue("description", it) }
        getSummary()?.let { insertValue("summary", it) }
        insertValue(
            "parameters",
            KATEListImpl(getParameters().map { it.toMutableKATEObject() }, itemType = KATEType.Object(KATEType.Any))
        )
        insertValue("operations", getOperations().values.toKATEList())
    }
}