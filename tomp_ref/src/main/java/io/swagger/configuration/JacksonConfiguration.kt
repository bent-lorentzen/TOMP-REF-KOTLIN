package io.swagger.configuration

import com.fasterxml.jackson.datatype.threetenbp.ThreeTenModule
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.threeten.bp.Instant
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZonedDateTime

@Configuration
class JacksonConfiguration {
    @Bean
    @ConditionalOnMissingBean(ThreeTenModule::class)
    fun threeTenModule(): ThreeTenModule {
        val module = ThreeTenModule()
        module.addDeserializer<Instant>(Instant::class.java, CustomInstantDeserializer.Companion.INSTANT)
        module.addDeserializer<OffsetDateTime>(OffsetDateTime::class.java, CustomInstantDeserializer.Companion.OFFSET_DATE_TIME)
        module.addDeserializer<ZonedDateTime>(ZonedDateTime::class.java, CustomInstantDeserializer.Companion.ZONED_DATE_TIME)
        return module
    }
}
