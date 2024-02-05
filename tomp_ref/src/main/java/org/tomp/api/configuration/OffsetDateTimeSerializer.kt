package org.tomp.api.configuration

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneId
import java.io.IOException

class OffsetDateTimeSerializer : JsonSerializer<OffsetDateTime>() {
    @Throws(IOException::class, JsonProcessingException::class)
    override fun serialize(value: OffsetDateTime, gen: JsonGenerator, serializers: SerializerProvider) {

        //value.withOffsetSameLocal(ZoneOffset.of("Amsterdam"));
        var value = value
        val zoneOffset = OffsetDateTime.now(ZoneId.systemDefault()).offset
        value = value.withOffsetSameLocal(zoneOffset)
        val string = value.toString()
        gen.writeString(string)
    }
}
