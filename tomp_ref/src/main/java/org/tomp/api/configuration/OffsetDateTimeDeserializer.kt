package org.tomp.api.configuration

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import org.threeten.bp.OffsetDateTime
import java.io.IOException

class OffsetDateTimeDeserializer protected constructor(vc: Class<*>?) : StdDeserializer<OffsetDateTime>(vc) {
    constructor() : this(null)

    @Throws(IOException::class, JsonProcessingException::class)
    override fun deserialize(jp: JsonParser, ctxt: DeserializationContext): OffsetDateTime {
        val node = jp.codec.readTree<JsonNode>(jp)
        return OffsetDateTime.parse(node.textValue())
    }

    companion object {
        private const val serialVersionUID = 8908258441798461284L
    }
}
