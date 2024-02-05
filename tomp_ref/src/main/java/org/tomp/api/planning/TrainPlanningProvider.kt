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
import javax.validation.Valid
import javax.validation.constraints.NotNull

@Component
@ConditionalOnProperty(value = ["tomp.providers.planning"], havingValue = "train", matchIfMissing = false)
class TrainPlanningProvider : PlanningProvider {
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
        val booking = Booking()
        val leg = Leg()
        if (bookingIntent) {
            leg.id = "DF(L<#NFSD=SFDKLJ"
        }
        leg.assetType = assetType
        leg.from = toPlace(from)
        leg.to = toPlace(to)
        leg.departureTime = start
        leg.arrivalTime = end
        leg.pricing = fare
        booking.setLegs(Arrays.asList(leg))
        return Arrays.asList(booking)
    }

    private fun toPlace(from2: @NotNull @Valid Coordinates?): Place? {
        // TODO Auto-generated method stub
        return null
    }

    private val fare: Fare
        private get() {
            val fare = Fare()
            val part = FarePart()
            part.type = FarePart.TypeEnum.FIXED
            part.currencyCode = "EUR"
            part.amount = 10.33f
            part.vatRate = 21.0f
            fare.addPartsItem(part)
            return fare
        }
    private val assetType: AssetType
        private get() {
            val typeOfAsset = AssetType()
            typeOfAsset.assetClass = AssetClass.BICYCLE
            typeOfAsset.assetSubClass = "Child, 26 inch"
            val sharedProperties = AssetProperties()
            typeOfAsset.sharedProperties = sharedProperties
            sharedProperties.model = "Batavus"
            sharedProperties.energyLabel = EnergyLabelEnum.A
            return typeOfAsset
        }
}
