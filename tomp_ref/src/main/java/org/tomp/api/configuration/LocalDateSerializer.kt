package org.tomp.api.configuration

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import org.threeten.bp.LocalDate
import java.io.IOException

class LocalDateSerializer : JsonSerializer<LocalDate>() {
    @Throws(IOException::class, JsonProcessingException::class)
    override fun serialize(value: LocalDate, gen: JsonGenerator, serializers: SerializerProvider) {
        val string = value.toString()
        gen.writeString(string)
    }
}
