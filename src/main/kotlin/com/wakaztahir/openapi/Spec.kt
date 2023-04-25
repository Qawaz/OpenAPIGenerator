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
        getName()?.let { setValue("name", it) }
        getUrl()?.let { setValue("url", it) }
        getEmail()?.let { setValue("email", it) }
    }
}

fun License.toMutableKATEObject(): MutableKATEObject {
    return MutableKATEObject {
        getName()?.let { setValue("name", it) }
        getUrl()?.let { setValue("url", it) }
    }
}

fun Info.toMutableKATEObject(): MutableKATEObject {
    return MutableKATEObject {
        getTitle()?.let { setValue("title", it) }
        getDescription()?.let { setValue("description", it) }
        getVersion()?.let { setValue("version", it) }
        getTermsOfService()?.let { setValue("termsOfService", it) }
        getContact()?.let { setValue("contact", it.toMutableKATEObject()) }
        getLicense()?.let { setValue("license", it.toMutableKATEObject()) }
    }
}

fun OpenApi3.toMutableKATEObject(allowNested : Boolean = false): MutableKATEObject {
    return MutableKATEObject {
        getInfo()?.let { insertValue("info", it.toMutableKATEObject()) }
        insertValue("schemas", KATEListImpl(getSchemas().values.map { it.toKATEValue(allowNested = allowNested) },itemType = KATEType.Any))
        insertValue("paths", KATEListImpl(getPaths().values.map { it.toMutableKATEObject() },itemType = KATEType.Any))
    }
}