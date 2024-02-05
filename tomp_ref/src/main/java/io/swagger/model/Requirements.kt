package io.swagger.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import java.util.Objects
import javax.annotation.Generated
import javax.validation.Valid

/**
 * Requirements from the end user side.
 */
@Schema(description = "Requirements from the end user side.")
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2021-04-26T08:47:05.979Z[GMT]")
class Requirements : HashMap<String?, Any?>() {
    @JsonProperty("abilities")
    private var abilities: @Valid MutableList<Requirement>? = null

    @JsonProperty("bringAlong")
    private var bringAlong: @Valid MutableList<Requirement>? = null
    fun abilities(abilities: List<Requirement>?): Requirements {
        this.abilities = abilities
        return this
    }

    fun addAbilitiesItem(abilitiesItem: Requirement): Requirements {
        if (abilities == null) {
            abilities = ArrayList()
        }
        abilities!!.add(abilitiesItem)
        return this
    }

    /**
     * Get abilities
     * @return abilities
     */
    @Schema(description = "")
    fun getAbilities(): @Valid MutableList<Requirement>? {
        return abilities
    }

    fun setAbilities(abilities: List<Requirement>?) {
        this.abilities = abilities
    }

    fun bringAlong(bringAlong: List<Requirement>?): Requirements {
        this.bringAlong = bringAlong
        return this
    }

    fun addBringAlongItem(bringAlongItem: Requirement): Requirements {
        if (bringAlong == null) {
            bringAlong = ArrayList()
        }
        bringAlong!!.add(bringAlongItem)
        return this
    }

    /**
     * Get bringAlong
     * @return bringAlong
     */
    @Schema(description = "")
    fun getBringAlong(): @Valid MutableList<Requirement>? {
        return bringAlong
    }

    fun setBringAlong(bringAlong: List<Requirement>?) {
        this.bringAlong = bringAlong
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val requirements = o as Requirements
        return abilities == requirements.abilities && bringAlong == requirements.bringAlong &&
                super.equals(o)
    }

    override fun hashCode(): Int {
        return Objects.hash(abilities, bringAlong, super.hashCode())
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class Requirements {\n")
        sb.append("    ").append(toIndentedString(super.toString())).append("\n")
        sb.append("    abilities: ").append(toIndentedString(abilities)).append("\n")
        sb.append("    bringAlong: ").append(toIndentedString(bringAlong)).append("\n")
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
