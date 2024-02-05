package io.swagger.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import java.util.Objects
import javax.annotation.Generated

/**
 * notifies the MaaS operator of issues with a booking. Asset information can be provided when needed.
 */
@Schema(description = "notifies the MaaS operator of issues with a booking. Asset information can be provided when needed.")
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2021-12-10T11:36:21.130Z[GMT]")
class Notification {
    /**
     * Gets or Sets type
     */
    enum class TypeEnum(private val value: String) {
        VEHICLE_NOT_AVAILABLE("VEHICLE_NOT_AVAILABLE"),
        USER_NO_SHOW("USER_NO_SHOW"),
        ETA("ETA"),
        MESSAGE_TO_DRIVER("MESSAGE_TO_DRIVER"),
        MESSAGE_TO_END_USER("MESSAGE_TO_END_USER"),
        OTHER("OTHER");

        @JsonValue
        override fun toString(): String {
            return value.toString()
        }

        companion object {
            @JsonCreator
            fun fromValue(text: String): TypeEnum? {
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
     * Get type
     * @return type
     */
    @JvmField
    @get:Schema(example = "VEHICLE_NOT_AVAILABLE", required = true, description = "")
    @JsonProperty("type")
    var type: TypeEnum? = null

    /**
     * in case of ETA, the number of minutes until arrival at the pickup location
     * minimum: 0
     * @return minutes
     */
    @get:Schema(description = "in case of ETA, the number of minutes until arrival at the pickup location")
    @JsonProperty("minutes")
    var minutes: Int? = null

    /**
     * Get asset
     * @return asset
     */
    @get:Schema(description = "")
    @JsonProperty("asset")
    var asset: Asset? = null

    /**
     * free text, should match Content-Language
     * @return comment
     */
    @JvmField
    @get:Schema(description = "free text, should match Content-Language")
    @JsonProperty("comment")
    var comment: String? = null
    fun type(type: TypeEnum?): Notification {
        this.type = type
        return this
    }

    fun minutes(minutes: Int?): Notification {
        this.minutes = minutes
        return this
    }

    fun asset(asset: Asset?): Notification {
        this.asset = asset
        return this
    }

    fun comment(comment: String?): Notification {
        this.comment = comment
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val notification = o as Notification
        return type == notification.type && minutes == notification.minutes && asset == notification.asset && comment == notification.comment
    }

    override fun hashCode(): Int {
        return Objects.hash(type, minutes, asset, comment)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class Notification {\n")
        sb.append("    type: ").append(toIndentedString(type)).append("\n")
        sb.append("    minutes: ").append(toIndentedString(minutes)).append("\n")
        sb.append("    asset: ").append(toIndentedString(asset)).append("\n")
        sb.append("    comment: ").append(toIndentedString(comment)).append("\n")
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
