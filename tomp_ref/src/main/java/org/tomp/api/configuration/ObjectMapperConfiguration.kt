package org.tomp.api.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.threeten.bp.LocalDate
import org.threeten.bp.OffsetDateTime
import javax.annotation.PostConstruct

@Component
class ObjectMapperConfiguration {
    @Autowired
    var mapper: ObjectMapper? = null
    @PostConstruct
    fun postConstruct() {
        val module = SimpleModule()
        module.addDeserializer(LocalDate::class.java, LocalDateDeserializer())
        module.addSerializer(LocalDate::class.java, LocalDateSerializer())
        module.addDeserializer(OffsetDateTime::class.java, OffsetDateTimeDeserializer())
        module.addSerializer(OffsetDateTime::class.java, OffsetDateTimeSerializer())
        mapper!!.registerModule(module)
    }
}
