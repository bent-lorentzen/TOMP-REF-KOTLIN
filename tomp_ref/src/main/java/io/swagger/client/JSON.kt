/*
 * Transport Operator MaaS Provider API
 * An API between MaaS providers and transport operators for booking trips and corresponding assets. <p>The documentation (examples, process flows and sequence diagrams) can be found at <a href=\"https://github.com/TOMP-WG/TOMP-API/\">github</a>.
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */
package io.swagger.client

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParseException
import com.google.gson.TypeAdapter
import com.google.gson.internal.bind.util.ISO8601Utils
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import io.gsonfire.GsonFireBuilder
import io.gsonfire.PostProcessor
import io.swagger.model.AmountOfMoney
import io.swagger.model.Booking
import io.swagger.model.BookingRequest
import io.swagger.model.Card
import io.swagger.model.CardType
import io.swagger.model.Condition
import io.swagger.model.ConditionDeposit
import io.swagger.model.ConditionPayWhenFinished
import io.swagger.model.ConditionPostponedCommit
import io.swagger.model.ConditionRequireBookingData
import io.swagger.model.ConditionReturnArea
import io.swagger.model.ConditionUpfrontPayment
import io.swagger.model.Customer
import io.swagger.model.ExtraCosts
import io.swagger.model.FarePart
import io.swagger.model.JournalEntry
import io.swagger.model.License
import io.swagger.model.LicenseType
import io.swagger.model.SupportRequest
import io.swagger.model.SupportStatus
import io.swagger.model.Traveler
import org.threeten.bp.LocalDate
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.io.IOException
import java.io.StringReader
import java.lang.reflect.Type
import java.sql.Date
import java.text.DateFormat
import java.text.ParseException
import java.text.ParsePosition
import java.util.Locale

class JSON {
    /**
     * Get Gson.
     *
     * @return Gson
     */
    var gson: Gson
        private set
    private var isLenientOnJson = false
    private val dateTypeAdapter = DateTypeAdapter()
    private val sqlDateTypeAdapter = SqlDateTypeAdapter()
    private val offsetDateTimeTypeAdapter = OffsetDateTimeTypeAdapter()
    private val localDateTypeAdapter = LocalDateTypeAdapter()

    init {
        gson = createGson()
            .registerTypeAdapter(java.util.Date::class.java, dateTypeAdapter)
            .registerTypeAdapter(Date::class.java, sqlDateTypeAdapter)
            .registerTypeAdapter(OffsetDateTime::class.java, offsetDateTimeTypeAdapter)
            .registerTypeAdapter(LocalDate::class.java, localDateTypeAdapter)
            .create()
    }

    /**
     * Set Gson.
     *
     * @param gson Gson
     * @return JSON
     */
    fun setGson(gson: Gson): JSON {
        this.gson = gson
        return this
    }

    fun setLenientOnJson(lenientOnJson: Boolean): JSON {
        isLenientOnJson = lenientOnJson
        return this
    }

    /**
     * Serialize the given Java object into JSON string.
     *
     * @param obj Object
     * @return String representation of the JSON
     */
    fun serialize(obj: Any?): String {
        return gson.toJson(obj)
    }

    /**
     * Deserialize the given JSON string to Java object.
     *
     * @param <T>        Type
     * @param body       The JSON string
     * @param returnType The type to deserialize into
     * @return The deserialized Java object
    </T> */
    fun <T> deserialize(body: String, returnType: Type): T {
        return try {
            if (isLenientOnJson) {
                val jsonReader = JsonReader(StringReader(body))
                // see https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/stream/JsonReader.html#setLenient(boolean)
                jsonReader.isLenient = true
                gson.fromJson(jsonReader, returnType)
            } else {
                gson.fromJson(body, returnType)
            }
        } catch (e: JsonParseException) {
            // Fallback processing when failed to parse JSON form response body:
            // return the response body string directly for the String return type;
            if (returnType == String::class.java) body as T else throw e
        }
    }

    /**
     * Gson TypeAdapter for JSR310 OffsetDateTime type
     */
    class OffsetDateTimeTypeAdapter @JvmOverloads constructor(private var formatter: DateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME) :
        TypeAdapter<OffsetDateTime?>() {
        fun setFormat(dateFormat: DateTimeFormatter) {
            formatter = dateFormat
        }

        @Throws(IOException::class)
        override fun write(out: JsonWriter, date: OffsetDateTime?) {
            if (date == null) {
                out.nullValue()
            } else {
                out.value(formatter.format(date))
            }
        }

        @Throws(IOException::class)
        override fun read(`in`: JsonReader): OffsetDateTime? {
            return when (`in`.peek()) {
                JsonToken.NULL -> {
                    `in`.nextNull()
                    null
                }

                else -> {
                    var date = `in`.nextString()
                    if (date.endsWith("+0000")) {
                        date = date.substring(0, date.length - 5) + "Z"
                    }
                    OffsetDateTime.parse(date, formatter)
                }
            }
        }
    }

    /**
     * Gson TypeAdapter for JSR310 LocalDate type
     */
    inner class LocalDateTypeAdapter @JvmOverloads constructor(private var formatter: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE) :
        TypeAdapter<LocalDate?>() {
        fun setFormat(dateFormat: DateTimeFormatter) {
            formatter = dateFormat
        }

        @Throws(IOException::class)
        override fun write(out: JsonWriter, date: LocalDate?) {
            if (date == null) {
                out.nullValue()
            } else {
                out.value(formatter.format(date))
            }
        }

        @Throws(IOException::class)
        override fun read(`in`: JsonReader): LocalDate? {
            return when (`in`.peek()) {
                JsonToken.NULL -> {
                    `in`.nextNull()
                    null
                }

                else -> {
                    val date = `in`.nextString()
                    LocalDate.parse(date, formatter)
                }
            }
        }
    }

    fun setOffsetDateTimeFormat(dateFormat: DateTimeFormatter): JSON {
        offsetDateTimeTypeAdapter.setFormat(dateFormat)
        return this
    }

    fun setLocalDateFormat(dateFormat: DateTimeFormatter): JSON {
        localDateTypeAdapter.setFormat(dateFormat)
        return this
    }

    /**
     * Gson TypeAdapter for java.sql.Date type
     * If the dateFormat is null, a simple "yyyy-MM-dd" format will be used
     * (more efficient than SimpleDateFormat).
     */
    class SqlDateTypeAdapter : TypeAdapter<Date?> {
        private var dateFormat: DateFormat? = null

        constructor()
        constructor(dateFormat: DateFormat?) {
            this.dateFormat = dateFormat
        }

        fun setFormat(dateFormat: DateFormat?) {
            this.dateFormat = dateFormat
        }

        @Throws(IOException::class)
        override fun write(out: JsonWriter, date: Date?) {
            if (date == null) {
                out.nullValue()
            } else {
                val value: String
                value = if (dateFormat != null) {
                    dateFormat!!.format(date)
                } else {
                    date.toString()
                }
                out.value(value)
            }
        }

        @Throws(IOException::class)
        override fun read(`in`: JsonReader): Date? {
            return when (`in`.peek()) {
                JsonToken.NULL -> {
                    `in`.nextNull()
                    null
                }

                else -> {
                    val date = `in`.nextString()
                    try {
                        if (dateFormat != null) {
                            Date(dateFormat!!.parse(date).time)
                        } else Date(ISO8601Utils.parse(date, ParsePosition(0)).time)
                    } catch (e: ParseException) {
                        throw JsonParseException(e)
                    }
                }
            }
        }
    }

    /**
     * Gson TypeAdapter for java.util.Date type
     * If the dateFormat is null, ISO8601Utils will be used.
     */
    class DateTypeAdapter : TypeAdapter<java.util.Date?> {
        private var dateFormat: DateFormat? = null

        constructor()
        constructor(dateFormat: DateFormat?) {
            this.dateFormat = dateFormat
        }

        fun setFormat(dateFormat: DateFormat?) {
            this.dateFormat = dateFormat
        }

        @Throws(IOException::class)
        override fun write(out: JsonWriter, date: java.util.Date?) {
            if (date == null) {
                out.nullValue()
            } else {
                val value: String
                value = if (dateFormat != null) {
                    dateFormat!!.format(date)
                } else {
                    ISO8601Utils.format(date, true)
                }
                out.value(value)
            }
        }

        @Throws(IOException::class)
        override fun read(`in`: JsonReader): java.util.Date? {
            return try {
                when (`in`.peek()) {
                    JsonToken.NULL -> {
                        `in`.nextNull()
                        null
                    }

                    else -> {
                        val date = `in`.nextString()
                        try {
                            if (dateFormat != null) {
                                dateFormat!!.parse(date)
                            } else ISO8601Utils.parse(date, ParsePosition(0))
                        } catch (e: ParseException) {
                            throw JsonParseException(e)
                        }
                    }
                }
            } catch (e: IllegalArgumentException) {
                throw JsonParseException(e)
            }
        }
    }

    fun setDateFormat(dateFormat: DateFormat?): JSON {
        dateTypeAdapter.setFormat(dateFormat)
        return this
    }

    fun setSqlDateFormat(dateFormat: DateFormat?): JSON {
        sqlDateTypeAdapter.setFormat(dateFormat)
        return this
    }

    companion object {
        fun createGson(): GsonBuilder {
            val fireBuilder = GsonFireBuilder()
                .registerTypeSelector(BookingRequest::class.java) { readElement ->
                    val classByDiscriminatorValue: MutableMap<String, Class<out BookingRequest>> = HashMap()
                    classByDiscriminatorValue["booking".uppercase(Locale.getDefault())] = Booking::class.java
                    classByDiscriminatorValue["BookingRequest".uppercase(Locale.getDefault())] = BookingRequest::class.java
                    getClassByDiscriminator(
                        classByDiscriminatorValue,
                        getDiscriminatorValue(readElement, "")
                    )
                }
                .registerPostProcessor(BookingRequest::class.java, object : PostProcessor<BookingRequest> {
                    override fun postDeserialize(result: BookingRequest, src: JsonElement, gson: Gson) {}
                    override fun postSerialize(result: JsonElement, src: BookingRequest, gson: Gson) {
                        val discriminatorValueByClass: MutableMap<Class<out BookingRequest>, String> = HashMap()
                        discriminatorValueByClass[Booking::class.java] = "booking"
                        discriminatorValueByClass[BookingRequest::class.java] = "BookingRequest"
                        if (result is JsonObject) {
                            if (!result.has("")) {
                                result.addProperty("", discriminatorValueByClass[src.javaClass])
                            }
                        }
                    }
                })
                .registerTypeSelector(CardType::class.java) { readElement ->
                    val classByDiscriminatorValue: MutableMap<String, Class<out CardType>> = HashMap()
                    classByDiscriminatorValue["card".uppercase(Locale.getDefault())] = Card::class.java
                    classByDiscriminatorValue["CardType".uppercase(Locale.getDefault())] = CardType::class.java
                    getClassByDiscriminator(
                        classByDiscriminatorValue,
                        getDiscriminatorValue(readElement, "")
                    )
                }
                .registerPostProcessor(CardType::class.java, object : PostProcessor<CardType> {
                    override fun postDeserialize(result: CardType, src: JsonElement, gson: Gson) {}
                    override fun postSerialize(result: JsonElement, src: CardType, gson: Gson) {
                        val discriminatorValueByClass: MutableMap<Class<out CardType>, String> = HashMap()
                        discriminatorValueByClass[Card::class.java] = "card"
                        discriminatorValueByClass[CardType::class.java] = "CardType"
                        if (result is JsonObject) {
                            if (!result.has("")) {
                                result.addProperty("", discriminatorValueByClass[src.javaClass])
                            }
                        }
                    }
                })
                .registerTypeSelector(Condition::class.java) { readElement ->
                    val classByDiscriminatorValue: MutableMap<String, Class<out Condition>> = HashMap()
                    classByDiscriminatorValue["conditionDeposit".uppercase(Locale.getDefault())] = ConditionDeposit::class.java
                    classByDiscriminatorValue["conditionPayWhenFinished".uppercase(Locale.getDefault())] = ConditionPayWhenFinished::class.java
                    classByDiscriminatorValue["conditionPostponedCommit".uppercase(Locale.getDefault())] = ConditionPostponedCommit::class.java
                    classByDiscriminatorValue["conditionRequireBookingData".uppercase(Locale.getDefault())] = ConditionRequireBookingData::class.java
                    classByDiscriminatorValue["conditionReturnArea".uppercase(Locale.getDefault())] = ConditionReturnArea::class.java
                    classByDiscriminatorValue["conditionUpfrontPayment".uppercase(Locale.getDefault())] = ConditionUpfrontPayment::class.java
                    classByDiscriminatorValue["Condition".uppercase(Locale.getDefault())] = Condition::class.java
                    getClassByDiscriminator(
                        classByDiscriminatorValue,
                        getDiscriminatorValue(readElement, "conditionType")
                    )
                }
                .registerPostProcessor(Condition::class.java, object : PostProcessor<Condition> {
                    override fun postDeserialize(result: Condition, src: JsonElement, gson: Gson) {}
                    override fun postSerialize(result: JsonElement, src: Condition, gson: Gson) {
                        val discriminatorValueByClass: MutableMap<Class<out Condition>, String> = HashMap()
                        discriminatorValueByClass[ConditionDeposit::class.java] = "conditionDeposit"
                        discriminatorValueByClass[ConditionPayWhenFinished::class.java] = "conditionPayWhenFinished"
                        discriminatorValueByClass[ConditionPostponedCommit::class.java] = "conditionPostponedCommit"
                        discriminatorValueByClass[ConditionRequireBookingData::class.java] = "conditionRequireBookingData"
                        discriminatorValueByClass[ConditionReturnArea::class.java] = "conditionReturnArea"
                        discriminatorValueByClass[ConditionUpfrontPayment::class.java] = "conditionUpfrontPayment"
                        discriminatorValueByClass[Condition::class.java] = "Condition"
                        if (result is JsonObject) {
                            if (!result.has("conditionType")) {
                                result.addProperty("conditionType", discriminatorValueByClass[src.javaClass])
                            }
                        }
                    }
                })
                .registerTypeSelector(Traveler::class.java) { readElement ->
                    val classByDiscriminatorValue: MutableMap<String, Class<out Traveler>> = HashMap()
                    classByDiscriminatorValue["customer".uppercase(Locale.getDefault())] = Customer::class.java
                    classByDiscriminatorValue["Traveler".uppercase(Locale.getDefault())] = Traveler::class.java
                    getClassByDiscriminator(
                        classByDiscriminatorValue,
                        getDiscriminatorValue(readElement, "")
                    )
                }
                .registerPostProcessor(Traveler::class.java, object : PostProcessor<Traveler> {
                    override fun postDeserialize(result: Traveler, src: JsonElement, gson: Gson) {}
                    override fun postSerialize(result: JsonElement, src: Traveler, gson: Gson) {
                        val discriminatorValueByClass: MutableMap<Class<out Traveler>, String> = HashMap()
                        discriminatorValueByClass[Customer::class.java] = "customer"
                        discriminatorValueByClass[Traveler::class.java] = "Traveler"
                        if (result is JsonObject) {
                            if (!result.has("")) {
                                result.addProperty("", discriminatorValueByClass[src.javaClass])
                            }
                        }
                    }
                })
                .registerTypeSelector(AmountOfMoney::class.java) { readElement ->
                    val classByDiscriminatorValue: MutableMap<String, Class<out AmountOfMoney>> = HashMap()
                    classByDiscriminatorValue["extraCosts".uppercase(Locale.getDefault())] = ExtraCosts::class.java
                    classByDiscriminatorValue["farePart".uppercase(Locale.getDefault())] = FarePart::class.java
                    classByDiscriminatorValue["journalEntry".uppercase(Locale.getDefault())] = JournalEntry::class.java
                    classByDiscriminatorValue["AmountOfMoney".uppercase(Locale.getDefault())] = AmountOfMoney::class.java
                    getClassByDiscriminator(
                        classByDiscriminatorValue,
                        getDiscriminatorValue(readElement, "")
                    )
                }
                .registerPostProcessor(AmountOfMoney::class.java, object : PostProcessor<AmountOfMoney> {
                    override fun postDeserialize(result: AmountOfMoney, src: JsonElement, gson: Gson) {}
                    override fun postSerialize(result: JsonElement, src: AmountOfMoney, gson: Gson) {
                        val discriminatorValueByClass: MutableMap<Class<out AmountOfMoney>, String> = HashMap()
                        discriminatorValueByClass[ExtraCosts::class.java] = "extraCosts"
                        discriminatorValueByClass[FarePart::class.java] = "farePart"
                        discriminatorValueByClass[JournalEntry::class.java] = "journalEntry"
                        discriminatorValueByClass[AmountOfMoney::class.java] = "AmountOfMoney"
                        if (result is JsonObject) {
                            if (!result.has("")) {
                                result.addProperty("", discriminatorValueByClass[src.javaClass])
                            }
                        }
                    }
                })
                .registerTypeSelector(LicenseType::class.java) { readElement ->
                    val classByDiscriminatorValue: MutableMap<String, Class<out LicenseType>> = HashMap()
                    classByDiscriminatorValue["license".uppercase(Locale.getDefault())] = License::class.java
                    classByDiscriminatorValue["LicenseType".uppercase(Locale.getDefault())] = LicenseType::class.java
                    getClassByDiscriminator(
                        classByDiscriminatorValue,
                        getDiscriminatorValue(readElement, "")
                    )
                }
                .registerPostProcessor(LicenseType::class.java, object : PostProcessor<LicenseType> {
                    override fun postDeserialize(result: LicenseType, src: JsonElement, gson: Gson) {}
                    override fun postSerialize(result: JsonElement, src: LicenseType, gson: Gson) {
                        val discriminatorValueByClass: MutableMap<Class<out LicenseType>, String> = HashMap()
                        discriminatorValueByClass[License::class.java] = "license"
                        discriminatorValueByClass[LicenseType::class.java] = "LicenseType"
                        if (result is JsonObject) {
                            if (!result.has("")) {
                                result.addProperty("", discriminatorValueByClass[src.javaClass])
                            }
                        }
                    }
                })
                .registerTypeSelector(SupportRequest::class.java) { readElement ->
                    val classByDiscriminatorValue: MutableMap<String, Class<out SupportRequest>> = HashMap()
                    classByDiscriminatorValue["supportStatus".uppercase(Locale.getDefault())] = SupportStatus::class.java
                    classByDiscriminatorValue["SupportRequest".uppercase(Locale.getDefault())] = SupportRequest::class.java
                    getClassByDiscriminator(
                        classByDiscriminatorValue,
                        getDiscriminatorValue(readElement, "")
                    )
                }
                .registerPostProcessor(SupportRequest::class.java, object : PostProcessor<SupportRequest> {
                    override fun postDeserialize(result: SupportRequest, src: JsonElement, gson: Gson) {}
                    override fun postSerialize(result: JsonElement, src: SupportRequest, gson: Gson) {
                        val discriminatorValueByClass: MutableMap<Class<out SupportRequest>, String> = HashMap()
                        discriminatorValueByClass[SupportStatus::class.java] = "supportStatus"
                        discriminatorValueByClass[SupportRequest::class.java] = "SupportRequest"
                        if (result is JsonObject) {
                            if (!result.has("")) {
                                result.addProperty("", discriminatorValueByClass[src.javaClass])
                            }
                        }
                    }
                })
            return fireBuilder.createGsonBuilder()
        }

        private fun getDiscriminatorValue(readElement: JsonElement, discriminatorField: String): String {
            val element = readElement.asJsonObject[discriminatorField]
                ?: throw IllegalArgumentException("missing discriminator field: <$discriminatorField>")
            return element.asString
        }

        private fun <T> getClassByDiscriminator(
            classByDiscriminatorValue: Map<String, Class<out T?>>,
            discriminatorValue: String
        ): Class<out T?> {
            return classByDiscriminatorValue[discriminatorValue.uppercase(Locale.getDefault())]
                ?: throw IllegalArgumentException("cannot determine model class of name: <$discriminatorValue>")
        }
    }
}
