package org.tomp.api.configuration

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import org.threeten.bp.LocalDate
import java.io.IOException

class LocalDateDeserializer @JvmOverloads constructor(vc: Class<*>? = null) : StdDeserializer<LocalDate>(vc) {
    @Throws(IOException::class)
    override fun deserialize(jp: JsonParser, ctxt: DeserializationContext): LocalDate {
        val node = jp.codec.readTree<JsonNode>(jp)
        return LocalDate.parse(node.textValue())
    }

    companion object {
        /**
         *
         */
        private const val serialVersionUID = -1586983811903817030L
    }
}