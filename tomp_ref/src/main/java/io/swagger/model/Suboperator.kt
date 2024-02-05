package io.swagger.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import java.util.Objects
import javax.annotation.Generated

/**
 * The operator of a leg or asset, in case this is not the TO itself but should be shown to the user
 */
@Schema(description = "The operator of a leg or asset, in case this is not the TO itself but should be shown to the user")
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2020-12-02T11:35:19.171Z[GMT]")
class Suboperator {
    /**
     * Name of the operator, could match Content-Language
     * @return name
     */
    @JvmField
    @get:Schema(required = true, description = "Name of the operator, could match Content-Language")
    @JsonProperty("name")
    var name: String? = null

    /**
     * the maasId from the operator
     * @return maasId
     */
    @JvmField
    @get:Schema(description = "the maasId from the operator")
    @JsonProperty("maasId")
    var maasId: String? = null

    /**
     * short description of the operator, should match Content-Language
     * @return description
     */
    @get:Schema(description = "short description of the operator, should match Content-Language")
    @JsonProperty("description")
    var description: String? = null

    /**
     * contact information, should match Content-Language
     * @return contact
     */
    @get:Schema(description = "contact information, should match Content-Language")
    @JsonProperty("contact")
    var contact: String? = null
    fun name(name: String?): Suboperator {
        this.name = name
        return this
    }

    fun maasId(maasId: String?): Suboperator {
        this.maasId = maasId
        return this
    }

    fun description(description: String?): Suboperator {
        this.description = description
        return this
    }

    fun contact(contact: String?): Suboperator {
        this.contact = contact
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val suboperator = o as Suboperator
        return name == suboperator.name && maasId == suboperator.maasId && description == suboperator.description && contact == suboperator.contact
    }

    override fun hashCode(): Int {
        return Objects.hash(name, maasId, description, contact)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class Suboperator {\n")
        sb.append("    name: ").append(toIndentedString(name)).append("\n")
        sb.append("    maasId: ").append(toIndentedString(maasId)).append("\n")
        sb.append("    description: ").append(toIndentedString(description)).append("\n")
        sb.append("    contact: ").append(toIndentedString(contact)).append("\n")
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
