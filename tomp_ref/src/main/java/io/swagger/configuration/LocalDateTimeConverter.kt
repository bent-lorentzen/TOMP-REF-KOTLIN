package io.swagger.configuration

import org.springframework.core.convert.converter.Converter
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

class LocalDateTimeConverter(dateFormat: String?) : Converter<String?, LocalDateTime?> {
    private val formatter: DateTimeFormatter

    init {
        formatter = DateTimeFormatter.ofPattern(dateFormat)
    }

    override fun convert(source: String?): LocalDateTime? {
        return if (source == null || source.isEmpty()) {
            null
        } else LocalDateTime.parse(source, formatter)
    }
}
