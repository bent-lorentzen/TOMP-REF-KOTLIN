package org.tomp.api.planning

import io.swagger.model.AssetType
import io.swagger.model.Booking
import io.swagger.model.BookingState
import io.swagger.model.Fare
import io.swagger.model.Leg
import io.swagger.model.OneOflegConditionsItems
import io.swagger.model.Place
import io.swagger.model.Planning
import io.swagger.model.PlanningRequest
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.threeten.bp.OffsetDateTime
import org.tomp.api.configuration.ExternalConfiguration
import org.tomp.api.providers.conditions.ConditionProvider
import org.tomp.api.repository.DefaultRepository
import org.tomp.api.utils.LegUtil
import java.util.Arrays
import java.util.UUID
import javax.validation.Valid

abstract class BasePlanningProvider : PlanningProvider {
    protected var from: Place? = null
    protected var to: Place? = null
    protected var startTime: @Valid OffsetDateTime? = null
    protected var endTime: @Valid OffsetDateTime? = null

    @Autowired
    protected var repository: DefaultRepository? = null

    @Autowired
    protected var configuration: ExternalConfiguration? = null

    @Autowired
    protected var conditionProvider: ConditionProvider? = null

    @Autowired
    var legUtil: LegUtil? = null
    override fun getOptions(body: @Valid PlanningRequest?, acceptLanguage: String?, bookingIntent: Boolean): Planning? {
        log.info("Request for options")
        val options = Planning()
        from = body!!.from
        to = body.to
        startTime = body.departureTime
        endTime = body.arrivalTime
        options.setOptions(getResults(body, acceptLanguage, bookingIntent))
        if (bookingIntent) {
            repository!!.saveBookingOption(options)
        } else {
            log.info("Forget this one")
        }
        return options
    }

    protected open fun getResults(body: @Valid PlanningRequest?, acceptLanguage: String?, bookingIntent: Boolean): List<Booking>? {
        val booking = Booking()
        booking.state = BookingState.NEW
        val leg = Leg()
        leg.assetType = assetType
        leg.from = body!!.from
        leg.to = body.to
        leg.departureTime = startTime
        leg.arrivalTime = endTime
        if (endTime == null) {
            val duration = legUtil!!.getDuration(leg).toDouble()
            leg.arrivalTime = startTime!!.plusSeconds(duration.toLong())
        }
        leg.pricing = fare
        leg.setConditions(getConditionsForLeg(leg, acceptLanguage))
        if (bookingIntent) {
            val uuid = UUID.randomUUID().toString()
            leg.id = uuid
            log.info("Generated uuid: {}", uuid)
            booking.id = uuid
        }
        booking.setLegs(Arrays.asList(leg))
        booking.from = body.from
        booking.to = body.to
        //		booking.setDepartureTime(leg.getDepartureTime());
//		booking.setArrivalTime(leg.getArrivalTime());
//		booking.setNrOfTravelers(body.getNrOfTravelers());
        booking.pricing = leg.pricing
        //		booking.setRadius(body.getRadius());
        return Arrays.asList(booking)
    }

    protected open fun getConditionsForLeg(result: Leg?, acceptLanguage: String?): List<OneOflegConditionsItems?>? {
        return conditionProvider!!.getApplyingConditions(acceptLanguage, result)
    }

    protected abstract val fare: Fare?
    protected abstract val assetType: AssetType?

    companion object {
        private val log = LoggerFactory.getLogger(BasePlanningProvider::class.java)
    }
}
