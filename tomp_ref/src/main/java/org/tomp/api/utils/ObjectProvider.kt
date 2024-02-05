package org.tomp.api.utils

interface ObjectProvider<T> {
    fun getObject(acceptLanguage: String?, c: Class<T>?, fromFile: String?): T
}
