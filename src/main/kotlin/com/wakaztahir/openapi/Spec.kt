package com.wakaztahir.openapi

import com.reprezen.kaizen.oasparser.model3.Contact
import com.reprezen.kaizen.oasparser.model3.Info
import com.reprezen.kaizen.oasparser.model3.License
import com.reprezen.kaizen.oasparser.model3.OpenApi3
import com.wakaztahir.kate.model.model.KATEListImpl
import com.wakaztahir.kate.model.model.MutableKATEObject

fun Contact.toMutableKATEObject(): MutableKATEObject {
    return MutableKATEObject {
        getName()?.let { putValue("name", it) }
        getUrl()?.let { putValue("url", it) }
        getEmail()?.let { putValue("email", it) }
    }
}

fun License.toMutableKATEObject(): MutableKATEObject {
    return MutableKATEObject {
        getName()?.let { putValue("name", it) }
        getUrl()?.let { putValue("url", it) }
    }
}

fun Info.toMutableKATEObject(): MutableKATEObject {
    return MutableKATEObject {
        getTitle()?.let { putValue("title", it) }
        getDescription()?.let { putValue("description", it) }
        getVersion()?.let { putValue("version", it) }
        getTermsOfService()?.let { putValue("termsOfService", it) }
        getContact()?.let { putValue("contact", it.toMutableKATEObject()) }
        getLicense()?.let { putValue("license", it.toMutableKATEObject()) }
    }
}

fun OpenApi3.toMutableKATEObject(): MutableKATEObject {
    return MutableKATEObject {
        getInfo()?.let { putValue("info", it.toMutableKATEObject()) }
        putValue("schemas", KATEListImpl(getSchemas().values.map { it.toKATEValue(allowNested = false) }))
        putValue("paths", KATEListImpl(getPaths().values.map { it.toMutableKATEObject() }))
    }
}