package org.tomp.api.planning

import io.swagger.model.AssetClass
import io.swagger.model.AssetType
import io.swagger.model.Booking
import io.swagger.model.Fare
import io.swagger.model.Leg
import io.swagger.model.Place
import io.swagger.model.PlanningRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component
import org.tomp.api.providers.assets.AssetProvider
import org.tomp.api.providers.fares.FareProvider
import org.tomp.api.repository.GbfsRepository
import javax.validation.Valid

@Component
@ConditionalOnProperty(value = ["tomp.providers.planning"], havingValue = "gbfs", matchIfMissing = false)
class GbfsPlanningProvider : BasePlanningProvider() {
    @Autowired
    var fareProvider: FareProvider? = null

    @Autowired
    var assetProvider: AssetProvider? = null

    @Autowired
    var gbfsRepository: GbfsRepository? = null
    override fun getResults(body: @Valid PlanningRequest?, acceptLanguage: String?, bookingIntent: Boolean): ArrayList<Booking>? {
        val assets = gbfsRepository!!.getNearestAssets(body!!.from, body.radius)
        val bookingList = ArrayList<Booking>()
        for (assetType in assets!!) {
            val booking = Booking()
            val leg = Leg()
            leg.assetType = assetType
            leg.from = from
            leg.to = to
            leg.departureTime = startTime
            leg.arrivalTime = endTime
            leg.pricing = fare
            leg.setConditions(getConditionsForLeg(leg, acceptLanguage))
            var assetLocation = getAssetLocation(assetType)
            if (assetLocation == null) {
                assetLocation = body.from
            }
            booking.addLegsItem(leg)
            val byFoot = Leg()
            val asset = AssetType()
            asset.assetClass = AssetClass.FOOT
            byFoot.assetType = asset
            byFoot.from = from
            byFoot.to = assetLocation
            byFoot.departureTime = body.departureTime
            byFoot.arrivalTime = body.arrivalTime!!.plusMinutes(5)
            booking.addLegsItem(byFoot)
            val byBike = Leg()
            byBike.assetType = assetType
            byBike.from = assetLocation
            byBike.to = to
            byBike.departureTime = body.departureTime!!.plusMinutes(5)
            byBike.arrivalTime = body.arrivalTime
            booking.addLegsItem(byBike)
            bookingList.add(booking)
        }
        return bookingList
    }

    private fun getAssetLocation(assetType: AssetType?): Place? {
        return if (assetType != null && !assetType.getAssets()!!.isEmpty() && assetType.getAssets()!![0].overriddenProperties!!.location != null) {
            assetType.getAssets()!![0].overriddenProperties!!.location
        } else null
    }

    protected override val fare: Fare?
        protected get() = fareProvider.getFare()
    protected override val assetType: AssetType?
        protected get() = assetProvider.getTypeOfAsset()
}
