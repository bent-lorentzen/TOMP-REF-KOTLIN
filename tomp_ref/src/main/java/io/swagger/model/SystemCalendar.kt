package io.swagger.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.validation.annotation.Validated
import java.util.Objects
import javax.annotation.Generated

/**
 * SystemCalendar
 */
@Validated
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2020-12-02T11:35:19.171Z[GMT]")
class SystemCalendar {
    /**
     * If this parameter is present, it means that start and end prameters correspond to the opening and closing days of the station. (GET /operator/stations)
     * @return stationId
     */
    @get:Schema(description = "If this parameter is present, it means that start and end prameters correspond to the opening and closing days of the station. (GET /operator/stations)")
    @JsonProperty("stationId")
    var stationId: String? = null

    /**
     * If this parameter is present, it means that start and end prameters correspond to the opening and closing days for the region. (GET /operator/regions)
     * @return regionId
     */
    @get:Schema(description = "If this parameter is present, it means that start and end prameters correspond to the opening and closing days for the region. (GET /operator/regions)")
    @JsonProperty("regionId")
    var regionId: String? = null

    /**
     * Starting month for the system operations (1-12)
     * minimum: 1
     * maximum: 12
     * @return startMonth
     */
    @JvmField
    @get:Schema(example = "1", required = true, description = "Starting month for the system operations (1-12)")
    @JsonProperty("startMonth")
    var startMonth: Int? = null

    /**
     * Starting day for the system operations (1-31)
     * minimum: 1
     * maximum: 31
     * @return startDay
     */
    @JvmField
    @get:Schema(example = "1", required = true, description = "Starting day for the system operations (1-31)")
    @JsonProperty("startDay")
    var startDay: Int? = null

    /**
     * Starting year for the system operations
     * @return startYear
     */
    @JvmField
    @get:Schema(example = "2019", description = "Starting year for the system operations")
    @JsonProperty("startYear")
    var startYear: Int? = null

    /**
     * Ending month for the system operations (1-12)
     * minimum: 1
     * maximum: 12
     * @return endMonth
     */
    @JvmField
    @get:Schema(example = "12", required = true, description = "Ending month for the system operations (1-12)")
    @JsonProperty("endMonth")
    var endMonth: Int? = null

    /**
     * Ending day for the system operations (1-31)
     * minimum: 1
     * maximum: 31
     * @return endDay
     */
    @JvmField
    @get:Schema(example = "31", required = true, description = "Ending day for the system operations (1-31)")
    @JsonProperty("endDay")
    var endDay: Int? = null

    /**
     * Ending year for the system operations
     * @return endYear
     */
    @JvmField
    @get:Schema(example = "2099", description = "Ending year for the system operations")
    @JsonProperty("endYear")
    var endYear: Int? = null
    fun stationId(stationId: String?): SystemCalendar {
        this.stationId = stationId
        return this
    }

    fun regionId(regionId: String?): SystemCalendar {
        this.regionId = regionId
        return this
    }

    fun startMonth(startMonth: Int?): SystemCalendar {
        this.startMonth = startMonth
        return this
    }

    fun startDay(startDay: Int?): SystemCalendar {
        this.startDay = startDay
        return this
    }

    fun startYear(startYear: Int?): SystemCalendar {
        this.startYear = startYear
        return this
    }

    fun endMonth(endMonth: Int?): SystemCalendar {
        this.endMonth = endMonth
        return this
    }

    fun endDay(endDay: Int?): SystemCalendar {
        this.endDay = endDay
        return this
    }

    fun endYear(endYear: Int?): SystemCalendar {
        this.endYear = endYear
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val systemCalendar = o as SystemCalendar
        return stationId == systemCalendar.stationId && regionId == systemCalendar.regionId && startMonth == systemCalendar.startMonth && startDay == systemCalendar.startDay && startYear == systemCalendar.startYear && endMonth == systemCalendar.endMonth && endDay == systemCalendar.endDay && endYear == systemCalendar.endYear
    }

    override fun hashCode(): Int {
        return Objects.hash(stationId, regionId, startMonth, startDay, startYear, endMonth, endDay, endYear)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class SystemCalendar {\n")
        sb.append("    stationId: ").append(toIndentedString(stationId)).append("\n")
        sb.append("    regionId: ").append(toIndentedString(regionId)).append("\n")
        sb.append("    startMonth: ").append(toIndentedString(startMonth)).append("\n")
        sb.append("    startDay: ").append(toIndentedString(startDay)).append("\n")
        sb.append("    startYear: ").append(toIndentedString(startYear)).append("\n")
        sb.append("    endMonth: ").append(toIndentedString(endMonth)).append("\n")
        sb.append("    endDay: ").append(toIndentedString(endDay)).append("\n")
        sb.append("    endYear: ").append(toIndentedString(endYear)).append("\n")
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
