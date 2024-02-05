package io.swagger.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import org.threeten.bp.OffsetDateTime
import java.util.Objects
import javax.annotation.Generated
import javax.validation.Valid

/**
 * event for the execution
 */
@Schema(description = "event for the execution")
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2021-04-26T08:47:05.979Z[GMT]")
class LegEvent {
    /**
     * Get time
     * @return time
     */
    @JvmField
    @get:Schema(required = true, description = "")
    @JsonProperty("time")
    var time: OffsetDateTime? = null

    /**
     * Gets or Sets event
     */
    enum class EventEnum(private val value: String) {
        PREPARE("PREPARE"),
        ASSIGN_ASSET("ASSIGN_ASSET"),
        SET_IN_USE("SET_IN_USE"),
        PAUSE("PAUSE"),
        START_FINISHING("START_FINISHING"),
        FINISH("FINISH"),
        TIME_EXTEND("TIME_EXTEND"),
        TIME_POSTPONE("TIME_POSTPONE"),
        CANCEL("CANCEL");

        @JsonValue
        override fun toString(): String {
            return value.toString()
        }

        companion object {
            @JsonCreator
            fun fromValue(text: String): EventEnum? {
                for (b in entries) {
                    if (b.value.toString() == text) {
                        return b
                    }
                }
                return null
            }
        }
    }

    /**
     * Get event
     * @return event
     */
    @JvmField
    @get:Schema(required = true, description = "")
    @JsonProperty("event")
    var event: EventEnum? = null

    /**
     * free text, should match Content-Language
     * @return comment
     */
    @get:Schema(description = "free text, should match Content-Language")
    @JsonProperty("comment")
    var comment: String? = null

    @JsonProperty("url")
    private var url: @Valid MutableList<String>? = null

    /**
     * Get asset
     * @return asset
     */
    @JvmField
    @get:Schema(description = "")
    @JsonProperty("asset")
    var asset: Asset? = null
    fun time(time: OffsetDateTime?): LegEvent {
        this.time = time
        return this
    }

    fun event(event: EventEnum?): LegEvent {
        this.event = event
        return this
    }

    fun comment(comment: String?): LegEvent {
        this.comment = comment
        return this
    }

    fun url(url: List<String>?): LegEvent {
        this.url = url
        return this
    }

    fun addUrlItem(urlItem: String): LegEvent {
        if (url == null) {
            url = ArrayList()
        }
        url!!.add(urlItem)
        return this
    }

    /**
     * urls to support the event e.g. pictures justifying the exit conditions
     * @return url
     */
    @Schema(description = "urls to support the event e.g. pictures justifying the exit conditions")
    fun getUrl(): List<String>? {
        return url
    }

    fun setUrl(url: List<String>?) {
        this.url = url
    }

    fun asset(asset: Asset?): LegEvent {
        this.asset = asset
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val legEvent = o as LegEvent
        return time == legEvent.time && event == legEvent.event && comment == legEvent.comment && url == legEvent.url && asset == legEvent.asset
    }

    override fun hashCode(): Int {
        return Objects.hash(time, event, comment, url, asset)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class LegEvent {\n")
        sb.append("    time: ").append(toIndentedString(time)).append("\n")
        sb.append("    event: ").append(toIndentedString(event)).append("\n")
        sb.append("    comment: ").append(toIndentedString(comment)).append("\n")
        sb.append("    url: ").append(toIndentedString(url)).append("\n")
        sb.append("    asset: ").append(toIndentedString(asset)).append("\n")
        sb.append("}")
        return sb.toString()
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private fun toIndentedString(o: Any?): String {
        return o?.toString()?.replace("\n", "\n    ") ?: "null"
    }
}
