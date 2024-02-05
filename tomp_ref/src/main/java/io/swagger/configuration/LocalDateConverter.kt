package io.swagger.configuration

import org.springframework.core.convert.converter.Converter
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

class LocalDateConverter(dateFormat: String?) : Converter<String?, LocalDate?> {
    private val formatter: DateTimeFormatter

    init {
        formatter = DateTimeFormatter.ofPattern(dateFormat)
    }

    override fun convert(source: String?): LocalDate? {
        return if (source == null || source.isEmpty()) {
            null
        } else LocalDate.parse(source, formatter)
    }
}
