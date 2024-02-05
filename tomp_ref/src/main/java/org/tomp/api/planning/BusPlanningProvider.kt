package org.tomp.api.planning

import io.swagger.model.AssetClass
import io.swagger.model.AssetProperties
import io.swagger.model.AssetProperties.EnergyLabelEnum
import io.swagger.model.AssetType
import io.swagger.model.Booking
import io.swagger.model.Coordinates
import io.swagger.model.Fare
import io.swagger.model.FarePart
import io.swagger.model.Leg
import io.swagger.model.Place
import io.swagger.model.Planning
import io.swagger.model.PlanningRequest
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component
import org.threeten.bp.OffsetDateTime
import java.util.Arrays
import java.util.UUID
import javax.validation.Valid
import javax.validation.constraints.NotNull

@Component
@ConditionalOnProperty(value = ["tomp.providers.planning"], havingValue = "bus", matchIfMissing = false)
class BusPlanningProvider : PlanningProvider {
    private var from: @NotNull @Valid Coordinates? = null
    private var to: @Valid Coordinates? = null
    private var start: @Valid OffsetDateTime? = null
    private var end: @Valid OffsetDateTime? = null
    override fun getOptions(body: @Valid PlanningRequest?, acceptLanguage: String?, bookingIntent: Boolean): Planning? {
        val options = Planning()
        from = body!!.from!!.coordinates
        to = body.to!!.coordinates
        start = body.departureTime
        end = body.arrivalTime
        options.setOptions(getResults(body, bookingIntent))
        return options
    }

    private fun getResults(body: @Valid PlanningRequest?, bookingIntent: Boolean): List<Booking> {
        val leg = Leg()
        if (bookingIntent) {
            leg.id = UUID.randomUUID().toString()
        }
        leg.assetType = assetType
        leg.from = toPlace(from)
        leg.to = toPlace(to)
        leg.departureTime = start
        leg.arrivalTime = end
        leg.pricing = fare
        val booking = Booking()
        booking.setLegs(Arrays.asList(leg))
        return Arrays.asList(booking)
    }

    private fun toPlace(coord: @NotNull @Valid Coordinates?): Place {
        val place = Place()
        place.coordinates = coord
        return place
    }

    private val fare: Fare
        private get() {
            val fare = Fare()
            val part = FarePart()
            part.type = FarePart.TypeEnum.FIXED
            part.currencyCode = "EUR"
            part.amount = 10.33.toFloat()
            part.vatRate = 21.0.toFloat()
            fare.addPartsItem(part)
            return fare
        }
    private val assetType: AssetType
        private get() {
            val assetType = AssetType()
            assetType.assetClass = AssetClass.BICYCLE
            assetType.assetSubClass = "Child, 26 inch"
            val sharedProperties = AssetProperties()
            sharedProperties.model = "Batavus"
            sharedProperties.energyLabel = EnergyLabelEnum.A
            assetType.sharedProperties = sharedProperties
            return assetType
        }
}
