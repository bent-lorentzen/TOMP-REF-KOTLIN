package io.swagger.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import java.util.Objects
import javax.annotation.Generated
import javax.validation.Valid

/**
 * describes an (dis)ability or ancillary.
 */
@Schema(description = "describes an (dis)ability or ancillary.")
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2021-12-10T11:36:21.130Z[GMT]")
class Requirement {
    /**
     * if obsolete, it is referencing the travelers' dictionary (https://github.com/TOMP-WG/TOMP-API/blob/master/documents/CROW%20passenger%20characteristics.xlsx)
     * @return source
     */
    @get:Schema(description = "if obsolete, it is referencing the travelers' dictionary (https://github.com/TOMP-WG/TOMP-API/blob/master/documents/CROW%20passenger%20characteristics.xlsx)")
    @JsonProperty("source")
    var source: String? = null

    /**
     * references to the first column of the specification initial values [ HR, AV, HV, AB, AER, K, ZR, RR ]
     * @return category
     */
    @get:Schema(
        required = true,
        description = "references to the first column of the specification initial values [ HR, AV, HV, AB, AER, K, ZR, RR ]"
    )
    @JsonProperty("category")
    var category: String? = null

    /**
     * references to the second column of the specification
     * @return number
     */
    @get:Schema(required = true, description = "references to the second column of the specification")
    @JsonProperty("number")
    var number: String? = null

    /**
     * conditionally extra information, referencing to the 3th column
     * @return type
     */
    @get:Schema(description = "conditionally extra information, referencing to the 3th column")
    @JsonProperty("type")
    var type: String? = null

    /**
     * extra field for detailed information, not standardized
     * @return memo
     */
    @get:Schema(description = "extra field for detailed information, not standardized")
    @JsonProperty("memo")
    var memo: String? = null

    /**
     * in some requirements there is references to '[variable number]' e.g. of meters (like ZR06)
     * minimum: 0
     * @return variableNumber
     */
    @get:Schema(description = "in some requirements there is references to '[variable number]' e.g. of meters (like ZR06)")
    @JsonProperty("variable-number")
    var variableNumber: Int? = null

    /**
     * Gets or Sets applicableDays
     */
    enum class ApplicableDaysEnum(private val value: String) {
        MO("MO"),
        TU("TU"),
        WE("WE"),
        TH("TH"),
        FR("FR"),
        SA("SA"),
        SU("SU");

        @JsonValue
        override fun toString(): String {
            return value.toString()
        }

        companion object {
            @JsonCreator
            fun fromValue(text: String): ApplicableDaysEnum? {
                for (b in entries) {
                    if (b.value.toString() == text) {
                        return b
                    }
                }
                return null
            }
        }
    }

    @JsonProperty("applicable-days")
    private var applicableDays: @Valid MutableList<ApplicableDaysEnum>? = null
    fun source(source: String?): Requirement {
        this.source = source
        return this
    }

    fun category(category: String?): Requirement {
        this.category = category
        return this
    }

    fun number(number: String?): Requirement {
        this.number = number
        return this
    }

    fun type(type: String?): Requirement {
        this.type = type
        return this
    }

    fun memo(memo: String?): Requirement {
        this.memo = memo
        return this
    }

    fun variableNumber(variableNumber: Int?): Requirement {
        this.variableNumber = variableNumber
        return this
    }

    fun applicableDays(applicableDays: List<ApplicableDaysEnum>?): Requirement {
        this.applicableDays = applicableDays
        return this
    }

    fun addApplicableDaysItem(applicableDaysItem: ApplicableDaysEnum): Requirement {
        if (applicableDays == null) {
            applicableDays = ArrayList()
        }
        applicableDays!!.add(applicableDaysItem)
        return this
    }

    /**
     * days of week that are applicable
     * @return applicableDays
     */
    @Schema(description = "days of week that are applicable")
    fun getApplicableDays(): List<ApplicableDaysEnum>? {
        return applicableDays
    }

    fun setApplicableDays(applicableDays: List<ApplicableDaysEnum>?) {
        this.applicableDays = applicableDays
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val requirement = o as Requirement
        return source == requirement.source && this.category == requirement.category && number == requirement.number && type == requirement.type && memo == requirement.memo && variableNumber == requirement.variableNumber && applicableDays == requirement.applicableDays
    }

    override fun hashCode(): Int {
        return Objects.hash(source, category, number, type, memo, variableNumber, applicableDays)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class Requirement {\n")
        sb.append("    source: ").append(toIndentedString(source)).append("\n")
        sb.append("    category: ").append(toIndentedString(category)).append("\n")
        sb.append("    number: ").append(toIndentedString(number)).append("\n")
        sb.append("    type: ").append(toIndentedString(type)).append("\n")
        sb.append("    memo: ").append(toIndentedString(memo)).append("\n")
        sb.append("    variableNumber: ").append(toIndentedString(variableNumber)).append("\n")
        sb.append("    applicableDays: ").append(toIndentedString(applicableDays)).append("\n")
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
