package org.tomp.api.planning

import io.swagger.client.ApiException
import io.swagger.model.AssetType
import io.swagger.model.Booking
import io.swagger.model.Coordinates
import io.swagger.model.Leg
import io.swagger.model.Place
import io.swagger.model.Planning
import io.swagger.model.PlanningRequest
import io.swagger.model.Requirements
import io.swagger.model.Suboperator
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.temporal.ChronoUnit
import org.tomp.api.model.Segment
import org.tomp.api.model.TransportOperator
import org.tomp.api.model.Trip
import org.tomp.api.mp.TOProvider
import org.tomp.api.repository.MPRepository
import org.tomp.api.utils.ClientUtil
import java.util.Random
import javax.validation.Valid

@Component
@ConditionalOnProperty(value = ["tomp.providers.planning"], havingValue = "maasprovider", matchIfMissing = false)
class MaaSPlanningProvider : PlanningProvider {
    @Autowired
    var toProvider: TOProvider? = null

    @Autowired
    var repository: MPRepository? = null

    @Autowired
    var clientUtil: ClientUtil? = null
    override fun getOptions(body: @Valid PlanningRequest?, acceptLanguage: String?, bookingIntent: Boolean): Planning? {
        log.info("Request for planning options")
        applyPersonalStuff(body)
        var trips = constructPossibleTrips(body)
        log.info("Number of trips constructed {}", trips.size)
        findTransportOperatorsPerLeg(trips)
        log.info("Looking for transport operators")
        try {
            getTransportOperatorInformation(trips, body)
            log.info("Fetched planning options from TOs")
            trips = constructBestTrips(trips)
            log.info("Constructed best trips {}", trips.size)
            log.info("Request ids")
            provideIds(trips, body)
            log.info("Done")
        } catch (e: ApiException) {
            log.error(e.message)
            log.error(e.responseBody)
        }
        return createPlanningOption(trips)
    }

    private fun createPlanningOption(trips: List<Trip>): Planning {
        val option = Planning()
        // option.setConditions(gatherConditions(trips));
        // option.setValidUntil(getMinimalValidUntil(trips));
        option.setOptions(gatherResults(trips))
        return option
    }

    private fun gatherResults(trips: List<Trip>): List<Booking> {
        val results: MutableList<Booking> = ArrayList()
        for (trip in trips) {
            val booking = Booking()
            val constructOperatorLegs = constructOperatorLegs(trip)
            booking.setLegs(constructOperatorLegs)
            results.add(booking)
            repository!!.saveBooking(booking, trip)
        }
        return results
    }

    private fun constructOperatorLegs(trip: Trip): List<Leg> {
        val legs: MutableList<Leg> = ArrayList()
        for (segment in trip.segments) {
            // take first operator
            val operator = segment.operators.iterator().next()
            val options = segment!!.getResult(operator)
            val leg = options!!.getOptions()!![0].getLegs()!![0]
            legs.add(toOperatorLeg(leg, operator))
        }
        return legs
    }

    private fun toOperatorLeg(leg: Leg, operator: TransportOperator?): Leg {
        val suboperator = Suboperator()
        suboperator.maasId = operator.getId()
        suboperator.name = operator.getName()
        leg.suboperator = suboperator
        return leg
    }

    @Throws(ApiException::class)
    private fun provideIds(trips: List<Trip>, body: PlanningRequest?) {
        for (trip in trips) {
            for (segment in trip.segments) {
                for (operator in segment.operators) {
                    val planningCheck = createPlanningCheck(segment, body)
                    // planningCheck.provideIds(true);
                    val options = clientUtil!!.post(
                        operator, "/plannings/&bookingIntent=true", planningCheck,
                        Planning::class.java
                    )
                    segment!!.addResult(operator, options)
                }
            }
        }
    }

    private fun applyPersonalStuff(body: PlanningRequest?) {
        var first = true
        for (user in body!!.getTravelers()!!) {
            // you can apply your knowledge of the end user in the body. It will be passed
            // to the TOs
            user.setIsValidated(true)
            if (first) {
                val requirements = Requirements()
                requirements["CROW-RK"] = "HR-02"
                user.setRequirements(requirements)
                first = false
            }
        }
    }

    private fun constructPossibleTrips(body: PlanningRequest?): List<Trip> {
        val numberOfTrips = Random().nextInt(3) + 1
        val trips: MutableList<Trip> = ArrayList()
        for (i in 0 until numberOfTrips) {
            trips.add(generateTrip(body))
        }
        return trips
    }

    private fun generateTrip(body: PlanningRequest?): Trip {
        val numberOfLegs = Random().nextInt(3) + 1
        val segments: MutableList<Segment> = ArrayList()
        var from = Coordinates()
        from.lat = body!!.from!!.coordinates!!.lat
        from.lng = body.from!!.coordinates!!.lng
        var to = body.to!!.coordinates
        var deltaX = to!!.lat!! - from.lat!!
        deltaX = deltaX / numberOfLegs
        var deltaY = to.lng!! - from.lng!!
        deltaY = deltaY / numberOfLegs
        // int deltaT = (body.getEndTime().subtract(body.getStartTime())).intValue();
        var deltaT = ChronoUnit.SECONDS.between(body.arrivalTime, body.departureTime)
        deltaT = deltaT / numberOfLegs
        to = Coordinates()
        to.lat = body.from!!.coordinates!!.lat
        to.lng = body.from!!.coordinates!!.lng
        to = applyDelta(to, 1, deltaX, deltaY)
        for (i in 0 until numberOfLegs) {
            val segment = Segment()
            val typeOfAsset = AssetType()
            segment.from = toPlace(from)
            segment.to = toPlace(to)
            from = applyDelta(from, i + 1, deltaX, deltaY)
            to = applyDelta(to, i + 1, deltaX, deltaY)
            val transportOperators = toProvider!!.getTransportOperators(segment)
            val index = Random().nextInt(transportOperators!!.size)
            val assetClass = transportOperators[index].assetClasses[0]
            typeOfAsset.assetClass = assetClass
            segment.assetType = typeOfAsset
            segment.departureTime = applyDelta(body.departureTime, i, deltaT)
            segment.arrivalTime = applyDelta(body.arrivalTime, i + 1, deltaT)
            segments.add(segment)
        }
        val trip = Trip()
        trip.segments = segments
        return trip
    }

    private fun toPlace(coord: Coordinates?): Place {
        val place = Place()
        place.coordinates = coord
        return place
    }

    private fun findTransportOperatorsPerLeg(trips: List<Trip>) {
        for (trip in trips) {
            for (segment in trip.segments) {
                for (to in toProvider!!.getTransportOperators(segment)) {
                    if (to!!.providesAssetClass(segment!!.assetType!!.assetClass)) {
                        segment.addResult(to, null)
                    }
                }
            }
        }
    }

    @Throws(ApiException::class)
    private fun getTransportOperatorInformation(trips: List<Trip>, body: PlanningRequest?) {
        for (trip in trips) {
            for (segment in trip.segments) {
                for (operator in segment.operators) {
                    val options = clientUtil!!.post(
                        operator, "/plannings/", createPlanningCheck(segment, body),
                        Planning::class.java
                    )
                    segment!!.addResult(operator, options)
                }
            }
        }
    }

    private fun createPlanningCheck(segment: Segment?, body: PlanningRequest?): PlanningRequest {
        val check = PlanningRequest()
        check.from(segment!!.from)
        check.to(segment.to)
        check.radius = body!!.radius
        check.departureTime(segment.departureTime)
        check.arrivalTime(segment.arrivalTime)
        check.setTravelers(body.getTravelers())
        return check
    }

    private fun constructBestTrips(allTrips: List<Trip>): List<Trip> {
        return allTrips
    }

    private fun applyDelta(offsetDateTime: @Valid OffsetDateTime?, i: Int, deltaT: Long): OffsetDateTime? {
        return ChronoUnit.SECONDS.addTo(offsetDateTime, i * deltaT)
    }

    private fun applyDelta(coord: Coordinates?, i: Int, deltaX: Float, deltaY: Float): Coordinates {
        val result = Coordinates()
        result.lat = coord!!.lat!! + deltaX * i
        result.lng = coord.lng!! + deltaY * i
        return result
    }

    companion object {
        private val log = LoggerFactory.getLogger(MaaSPlanningProvider::class.java)
    }
}
