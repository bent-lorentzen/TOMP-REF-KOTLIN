package io.swagger.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import java.util.Objects
import javax.annotation.Generated

/**
 * Phone
 */
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2020-12-02T11:35:19.171Z[GMT]")
class Phone {
    /**
     * only one phone in this array can have a true in this property
     * @return preferred
     */
    @get:Schema(description = "only one phone in this array can have a true in this property")
    @JsonProperty("preferred")
    var isPreferred: Boolean? = null

    /**
     * phone number. In case of international usage, always provide the country code.
     * @return number
     */
    @get:Schema(
        example = "+31-48934758 or +(0075)-834923384 or 020 1234 1234",
        description = "phone number. In case of international usage, always provide the country code."
    )
    @JsonProperty("number")
    var number: String? = null

    /**
     * Gets or Sets kind
     */
    enum class KindEnum(private val value: String) {
        LANDLINE("LANDLINE"),
        MOBILE("MOBILE");

        @JsonValue
        override fun toString(): String {
            return value.toString()
        }

        companion object {
            @JsonCreator
            fun fromValue(text: String): KindEnum? {
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
     * Get kind
     * @return kind
     */
    @get:Schema(description = "")
    @JsonProperty("kind")
    var kind: KindEnum? = null

    /**
     * Gets or Sets type
     */
    enum class TypeEnum(private val value: String) {
        PRIVATE("PRIVATE"),
        BUSINESS("BUSINESS"),
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
    @get:Schema(description = "")
    @JsonProperty("type")
    var type: TypeEnum? = null
    fun preferred(preferred: Boolean?): Phone {
        isPreferred = preferred
        return this
    }

    fun number(number: String?): Phone {
        this.number = number
        return this
    }

    fun kind(kind: KindEnum?): Phone {
        this.kind = kind
        return this
    }

    fun type(type: TypeEnum?): Phone {
        this.type = type
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val phone = o as Phone
        return isPreferred == phone.isPreferred && number == phone.number && kind == phone.kind && type == phone.type
    }

    override fun hashCode(): Int {
        return Objects.hash(isPreferred, number, kind, type)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class Phone {\n")
        sb.append("    preferred: ").append(toIndentedString(isPreferred)).append("\n")
        sb.append("    number: ").append(toIndentedString(number)).append("\n")
        sb.append("    kind: ").append(toIndentedString(kind)).append("\n")
        sb.append("    type: ").append(toIndentedString(type)).append("\n")
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
