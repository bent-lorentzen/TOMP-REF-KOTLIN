package io.swagger.configuration

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonTokenId
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.datatype.threetenbp.DecimalUtils
import com.fasterxml.jackson.datatype.threetenbp.deser.ThreeTenDateTimeDeserializerBase
import com.fasterxml.jackson.datatype.threetenbp.function.BiFunction
import com.fasterxml.jackson.datatype.threetenbp.function.Function
import org.threeten.bp.DateTimeException
import org.threeten.bp.DateTimeUtils
import org.threeten.bp.Instant
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.temporal.Temporal
import org.threeten.bp.temporal.TemporalAccessor
import java.io.IOException

/**
 * Deserializer for ThreeTen temporal [Instant]s, [OffsetDateTime], and [ZonedDateTime]s.
 * Adapted from the jackson threetenbp InstantDeserializer to add support for deserializing rfc822 format.
 *
 * @author Nick Williams
 */
class CustomInstantDeserializer<T : Temporal?> : ThreeTenDateTimeDeserializerBase<T?> {
    protected val fromMilliseconds: Function<FromIntegerArguments, T?>
    protected val fromNanoseconds: Function<FromDecimalArguments, T?>
    protected val parsedToValue: Function<TemporalAccessor, T?>
    protected val adjust: BiFunction<T?, ZoneId?, T?>

    protected constructor(
        supportedType: Class<T>?,
        parser: DateTimeFormatter?,
        parsedToValue: Function<TemporalAccessor, T>,
        fromMilliseconds: Function<FromIntegerArguments, T>,
        fromNanoseconds: Function<FromDecimalArguments, T>,
        adjust: BiFunction<T, ZoneId?, T>?
    ) : super(supportedType, parser) {
        this.parsedToValue = parsedToValue
        this.fromMilliseconds = fromMilliseconds
        this.fromNanoseconds = fromNanoseconds
        this.adjust = adjust
            ?: BiFunction { t, zoneId -> t }
    }

    protected constructor(base: CustomInstantDeserializer<T?>, f: DateTimeFormatter?) : super(base.handledType() as Class<T>, f) {
        parsedToValue = base.parsedToValue
        fromMilliseconds = base.fromMilliseconds
        fromNanoseconds = base.fromNanoseconds
        adjust = base.adjust
    }

    override fun withDateFormat(dtf: DateTimeFormatter): JsonDeserializer<T?> {
        return if (dtf == _formatter) {
            this
        } else CustomInstantDeserializer<T?>(this, dtf)
    }

    @Throws(IOException::class)
    override fun deserialize(parser: JsonParser, context: DeserializationContext): T? {
        //NOTE: Timestamps contain no timezone info, and are always in configured TZ. Only
        //string values have to be adjusted to the configured TZ.
        when (parser.currentTokenId) {
            JsonTokenId.ID_NUMBER_FLOAT -> {
                val value = parser.decimalValue
                val seconds = value.toLong()
                val nanoseconds = DecimalUtils.extractNanosecondDecimal(value, seconds)
                return fromNanoseconds.apply(
                    FromDecimalArguments(
                        seconds, nanoseconds, getZone(context)
                    )
                )
            }

            JsonTokenId.ID_NUMBER_INT -> {
                val timestamp = parser.longValue
                return if (context.isEnabled(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS)) {
                    fromNanoseconds.apply(
                        FromDecimalArguments(
                            timestamp, 0, getZone(context)
                        )
                    )
                } else fromMilliseconds.apply(
                    FromIntegerArguments(
                        timestamp, getZone(context)
                    )
                )
            }

            JsonTokenId.ID_STRING -> {
                var string = parser.text.trim { it <= ' ' }
                if (string.length == 0) {
                    return null
                }
                if (string.endsWith("+0000")) {
                    string = string.substring(0, string.length - 5) + "Z"
                }
                val value: T?
                try {
                    val acc = _formatter.parse(string)
                    value = parsedToValue.apply(acc)
                    if (context.isEnabled(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE)) {
                        return adjust.apply(value, getZone(context))
                    }
                } catch (e: DateTimeException) {
                    throw _peelDTE(e)
                }
                return value
            }
        }
        throw context.mappingException("Expected type float, integer, or string.")
    }

    private fun getZone(context: DeserializationContext): ZoneId? {
        // Instants are always in UTC, so don't waste compute cycles
        return if (_valueClass == Instant::class.java) null else DateTimeUtils.toZoneId(context.timeZone)
    }

    private class FromIntegerArguments(val value: Long, val zoneId: ZoneId?)
    private class FromDecimalArguments(val integer: Long, val fraction: Int, val zoneId: ZoneId?)
    companion object {
        private const val serialVersionUID = 1L
        val INSTANT = CustomInstantDeserializer(
            Instant::class.java, DateTimeFormatter.ISO_INSTANT,
            Function { temporalAccessor -> Instant.from(temporalAccessor) },
            Function { a -> Instant.ofEpochMilli(a.value) },
            Function { a -> Instant.ofEpochSecond(a.integer, a.fraction.toLong()) },
            null
        )
        val OFFSET_DATE_TIME = CustomInstantDeserializer(
            OffsetDateTime::class.java, DateTimeFormatter.ISO_OFFSET_DATE_TIME,
            Function { temporalAccessor -> OffsetDateTime.from(temporalAccessor) },
            Function { a -> OffsetDateTime.ofInstant(Instant.ofEpochMilli(a.value), a.zoneId) },
            Function { a -> OffsetDateTime.ofInstant(Instant.ofEpochSecond(a.integer, a.fraction.toLong()), a.zoneId) },
            BiFunction { d, z -> d.withOffsetSameInstant(z.rules.getOffset(d.toLocalDateTime())) }
        )
        val ZONED_DATE_TIME = CustomInstantDeserializer(
            ZonedDateTime::class.java, DateTimeFormatter.ISO_ZONED_DATE_TIME,
            Function { temporalAccessor -> ZonedDateTime.from(temporalAccessor) },
            Function { a -> ZonedDateTime.ofInstant(Instant.ofEpochMilli(a.value), a.zoneId) },
            Function { a -> ZonedDateTime.ofInstant(Instant.ofEpochSecond(a.integer, a.fraction.toLong()), a.zoneId) },
            BiFunction { zonedDateTime, zoneId -> zonedDateTime.withZoneSameInstant(zoneId) }
        )
    }
}
