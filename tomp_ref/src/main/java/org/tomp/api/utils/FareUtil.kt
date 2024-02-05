package org.tomp.api.utils

import io.swagger.model.Fare
import io.swagger.model.FarePart
import io.swagger.model.FarePart.ScaleTypeEnum
import io.swagger.model.FarePart.UnitTypeEnum
import io.swagger.model.Leg
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException
import javax.validation.constraints.NotNull
import kotlin.math.ceil

@Component
class FareUtil {
    @Autowired
    var legUtil: LegUtil? = null
    fun calculateFare(leg: Leg?): Double {
        val fare = leg!!.pricing
        val minutes = legUtil!!.getDuration(leg).toDouble()
        val distanceInMeters = legUtil!!.getDistance(leg)
        return calculateFare(fare, minutes, distanceInMeters)
    }

    fun calculateFare(fare: Fare?, minutes: Double, distanceInMeters: Double): Double {
        var amount = 0.0
        var max: Float? = null
        for (part in fare!!.getParts()!!) {
            when (part.type) {
                FarePart.TypeEnum.FIXED -> amount += part.amount!!.toDouble()
                FarePart.TypeEnum.FLEX -> amount += calculateFlexPart(part, minutes, distanceInMeters)
                FarePart.TypeEnum.MAX -> max = part.amount
                else -> {}
            }
        }
        if (max != null && max.toDouble() > amount) {
            amount = max.toDouble()
        }
        return Math.round(amount * 100.0) / 100.0
    }

    private fun calculateFlexPart(part: FarePart, minutes: Double, distanceInMeters: Double): Double {
        log.info("calc fare {} {} {}", part, minutes, distanceInMeters)
        if (part.scaleType == null) {
            var amount = part.amount!!.toDouble()
            if (part.unitType === UnitTypeEnum.HOUR || part.unitType === UnitTypeEnum.MINUTE || part.unitType === UnitTypeEnum.SECOND) {
                var minutesPerUnit = getMinutesPerUnitType(part.unitType)
                minutesPerUnit = minutesPerUnit * part.units!!.toDouble()
                log.info("minutesPerUnit {}", minutesPerUnit)
                amount = amount * ceil(minutes / minutesPerUnit)
            } else if (part.unitType === UnitTypeEnum.KM) {
                val meterPerUnit = getMetersPerUnitType(part.unitType)
                log.info("meterPerUnit {}", meterPerUnit)
                amount = amount * ceil(distanceInMeters / meterPerUnit)
            }
            return amount
        }
        when (part.scaleType) {
            ScaleTypeEnum.HOUR -> {
                val startMinutes = part.scaleFrom!!.toDouble()
                val endMinutes = part.scaleTo!!.toDouble()
                if (minutes > startMinutes && minutes < endMinutes) {
                    return calculatePrice(part, minutes, startMinutes)
                }
            }

            ScaleTypeEnum.KM -> {}
            ScaleTypeEnum.MILE -> {}
            ScaleTypeEnum.MINUTE -> {}
            else -> throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Unknown ScaleType: " + part.scaleType)
        }
        return 0
    }

    private fun getMetersPerUnitType(unitType: @NotNull UnitTypeEnum?): Double {
        return when (unitType) {
            UnitTypeEnum.KM -> 1000
            UnitTypeEnum.MILE -> 1609.344
            else -> throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Unknown UnitType for getMeters: $unitType")
        }
    }

    private fun getMinutesPerUnitType(unitType: @NotNull UnitTypeEnum?): Double {
        return when (unitType) {
            UnitTypeEnum.HOUR -> 60
            UnitTypeEnum.MINUTE -> 1
            UnitTypeEnum.SECOND -> 1.0 / 60
            else -> throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Unknown UnitType for getMinutesPerUnitType: $unitType"
            )
        }
    }

    private fun calculatePrice(part: FarePart, minutes: Double, startMinutes: Double): Double {
        val fareMinutes = minutes - startMinutes
        return when (part.unitType) {
            UnitTypeEnum.HOUR -> {
                val fareHours = Math.round(fareMinutes / 60.0) + 1
                part.amount!!.toDouble() * fareHours
            }

            UnitTypeEnum.MINUTE -> part.amount!!.toDouble() * fareMinutes
            UnitTypeEnum.SECOND -> {
                val wholeFareMinutes = Math.round(minutes)
                val fareSeconds = Math.round((minutes - wholeFareMinutes) * 60.0)
                part.amount!!.toDouble() * fareSeconds
            }

            else -> throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Illegal fare part configuration: $part")
        }
    }

    companion object {
        private val log = LoggerFactory.getLogger(FareUtil::class.java)
    }
}
