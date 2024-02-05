package io.swagger.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import java.util.Objects
import javax.annotation.Generated

/**
 * reference to a stop (can be nation specific). This can help to specific pinpoint a (bus) stop. Extra information about the stop is not supplied; you should find it elsewhere.
 */
@Schema(description = "reference to a stop (can be nation specific). This can help to specific pinpoint a (bus) stop. Extra information about the stop is not supplied; you should find it elsewhere.")
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2020-12-02T11:35:19.171Z[GMT]")
class StopReference {
    /**
     * type of external reference (GTFS, CHB).
     */
    enum class TypeEnum(private val value: String) {
        GTFS_STOP_ID("GTFS_STOP_ID"),
        GTFS_STOP_CODE("GTFS_STOP_CODE"),
        GTFS_AREA_ID("GTFS_AREA_ID"),
        CHB_STOP_PLACE_CODE("CHB_STOP_PLACE_CODE"),
        CHB_QUAY_CODE("CHB_QUAY_CODE"),
        NS_CODE("NS_CODE");

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
     * type of external reference (GTFS, CHB).
     * @return type
     */
    @get:Schema(required = true, description = "type of external reference (GTFS, CHB).")
    @JsonProperty("type")
    var type: TypeEnum? = null

    /**
     * this field should contain the complete ID. E.g. NL:S:13121110 or BE:S:79640040
     * @return id
     */
    @get:Schema(
        required = true,
        description = "this field should contain the complete ID. E.g. NL:S:13121110 or BE:S:79640040"
    )
    @JsonProperty("id")
    var id: String? = null

    /**
     * two-letter country codes according to ISO 3166-1
     * @return country
     */
    @get:Schema(
        example = "NL",
        required = true,
        description = "two-letter country codes according to ISO 3166-1"
    )
    @JsonProperty("country")
    var country: String? = null
    fun type(type: TypeEnum?): StopReference {
        this.type = type
        return this
    }

    fun id(id: String?): StopReference {
        this.id = id
        return this
    }

    fun country(country: String?): StopReference {
        this.country = country
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val stopReference = o as StopReference
        return type == stopReference.type && id == stopReference.id && country == stopReference.country
    }

    override fun hashCode(): Int {
        return Objects.hash(type, id, country)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class StopReference {\n")
        sb.append("    type: ").append(toIndentedString(type)).append("\n")
        sb.append("    id: ").append(toIndentedString(id)).append("\n")
        sb.append("    country: ").append(toIndentedString(country)).append("\n")
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
