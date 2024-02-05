package org.tomp.api.utils

import io.swagger.model.Leg
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.threeten.bp.temporal.ChronoUnit
import javax.validation.Valid

@Component
class LegUtil {
    /*
	 * Duration in seconds
	 */
    fun getDuration(leg: Leg?): Int {
        if (leg!!.departureTime != null && leg.arrivalTime != null) {
            return ChronoUnit.MINUTES.between(leg.departureTime, leg.arrivalTime).toInt()
        }
        // avg 30 km/h => 30000 m/h => 30000/3600 = 8.3 m/s
        val duration = getDistance(leg) / 8.3
        log.info("Duration (seconds): {}", duration)
        return duration.toInt()
    }

    /*
	 * Straight line distance
	 */
    fun getDistance(leg: @Valid Leg?): Double {
        val from = leg!!.from!!.coordinates
        val to = leg.to!!.coordinates
        val distance = GeoUtil.distanceInMeters(
            from!!.lat!!, from.lng!!,
            to!!.lat!!, to.lng!!
        )
        log.info("Distance (meters): {}", distance)
        return distance.toDouble()
    }

    companion object {
        private val log = LoggerFactory.getLogger(LegUtil::class.java)
    }
}
