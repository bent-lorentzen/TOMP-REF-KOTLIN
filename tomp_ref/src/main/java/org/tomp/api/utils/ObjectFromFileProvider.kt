package org.tomp.api.utils

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream

class ObjectFromFileProvider<T> : ObjectProvider<T?> {
    var mapper = ObjectMapper()
    override fun getObject(acceptLanguage: String?, c: Class<T?>?, fromFile: String?): T? {
        if (fromFile == null) {
            throw RuntimeException()
        }
        var file = File(fromFile)
        var resourceAsStream: InputStream? = null
        if (!file.exists()) {
            log.info("file: {} does not exist", fromFile)
            log.info("file: {} does not exist", file.absolutePath)
            resourceAsStream = ClassLoader.getSystemResourceAsStream(fromFile)
            if (resourceAsStream == null) {
                val classLoader = ClassLoader.getSystemClassLoader()
                file = File(classLoader.getResource(fromFile).file)
                if (!file.exists()) {
                    log.error(file.absolutePath)
                }
            }
        }
        try {
            resourceAsStream = FileInputStream(file)
        } catch (e1: FileNotFoundException) {
            log.error(e1.message)
        }
        try {
            return mapper.readValue(resourceAsStream, c)
        } catch (e: IOException) {
            log.error("parse exception", e)
        }
        return null
    }

    companion object {
        private val log = LoggerFactory.getLogger(ObjectFromFileProvider::class.java)
    }
}
