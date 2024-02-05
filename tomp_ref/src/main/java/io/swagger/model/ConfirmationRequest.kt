package io.swagger.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import java.util.Objects
import javax.annotation.Generated

/**
 * the TO can ask permission to do something to the MP, as the MP is financially responsible.
 */
@Schema(description = "the TO can ask permission to do something to the MP, as the MP is financially responsible.")
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2021-06-08T10:25:25.468Z[GMT]")
class ConfirmationRequest {
    /**
     * Gets or Sets type
     */
    enum class TypeEnum(private val value: String) {
        REPLACE_ASSET("REPLACE_ASSET"),
        START_LEG("START_LEG");

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

    /**
     * reference to the assetType in /operator/available-assets, this property can be set when replacing an asset (for another type). In case of a succesfull replacement, the /legs/{id}/events - ASSIGN_ASSET should be send to the MP to inform a change of asset has been made.
     * @return assetTypeId
     */
    @get:Schema(description = "reference to the assetType in /operator/available-assets, this property can be set when replacing an asset (for another type). In case of a succesfull replacement, the /legs/{id}/events - ASSIGN_ASSET should be send to the MP to inform a change of asset has been made.")
    @JsonProperty("assetTypeId")
    var assetTypeId: String? = null
    fun type(type: TypeEnum?): ConfirmationRequest {
        this.type = type
        return this
    }

    fun assetTypeId(assetTypeId: String?): ConfirmationRequest {
        this.assetTypeId = assetTypeId
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val confirmationRequest = o as ConfirmationRequest
        return type == confirmationRequest.type && assetTypeId == confirmationRequest.assetTypeId
    }

    override fun hashCode(): Int {
        return Objects.hash(type, assetTypeId)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class ConfirmationRequest {\n")
        sb.append("    type: ").append(toIndentedString(type)).append("\n")
        sb.append("    assetTypeId: ").append(toIndentedString(assetTypeId)).append("\n")
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
