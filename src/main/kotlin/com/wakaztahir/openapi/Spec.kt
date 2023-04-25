package com.wakaztahir.openapi

import com.reprezen.kaizen.oasparser.model3.Contact
import com.reprezen.kaizen.oasparser.model3.Info
import com.reprezen.kaizen.oasparser.model3.License
import com.reprezen.kaizen.oasparser.model3.OpenApi3
import com.wakaztahir.kate.model.KATEType
import com.wakaztahir.kate.model.model.KATEListImpl
import com.wakaztahir.kate.model.model.MutableKATEObject

fun Contact.toMutableKATEObject(): MutableKATEObject {
    return MutableKATEObject {
        getName()?.let { insertValue("name", it) }
        getUrl()?.let { insertValue("url", it) }
        getEmail()?.let { insertValue("email", it) }
    }
}

fun License.toMutableKATEObject(): MutableKATEObject {
    return MutableKATEObject {
        getName()?.let { insertValue("name", it) }
        getUrl()?.let { insertValue("url", it) }
    }
}

fun Info.toMutableKATEObject(): MutableKATEObject {
    return MutableKATEObject {
        getTitle()?.let { insertValue("title", it) }
        getDescription()?.let { insertValue("description", it) }
        getVersion()?.let { insertValue("version", it) }
        getTermsOfService()?.let { insertValue("termsOfService", it) }
        getContact()?.let { insertValue("contact", it.toMutableKATEObject()) }
        getLicense()?.let { insertValue("license", it.toMutableKATEObject()) }
    }
}

fun OpenApi3.toMutableKATEObject(allowNested : Boolean = false): MutableKATEObject {
    return MutableKATEObject {
        getInfo()?.let { insertValue("info", it.toMutableKATEObject()) }
        insertValue("schemas", KATEListImpl(getSchemas().values.map { it.toKATEValue(allowNested = allowNested) },itemType = KATEType.Any))
        insertValue("paths", KATEListImpl(getPaths().values.map { it.toMutableKATEObject() },itemType = KATEType.Any))
    }
}