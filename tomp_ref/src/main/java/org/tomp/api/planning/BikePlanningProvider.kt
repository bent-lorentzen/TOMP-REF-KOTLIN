package org.tomp.api.planning

import io.swagger.model.AssetClass
import io.swagger.model.AssetProperties
import io.swagger.model.AssetProperties.EnergyLabelEnum
import io.swagger.model.AssetType
import io.swagger.model.ConditionReturnArea
import io.swagger.model.Day
import io.swagger.model.Fare
import io.swagger.model.FarePart
import io.swagger.model.GeojsonPolygon
import io.swagger.model.Leg
import io.swagger.model.OneOflegConditionsItems
import io.swagger.model.SystemHours
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component
import org.tomp.api.utils.GeoUtil
import java.util.Arrays

@Component
@ConditionalOnProperty(value = ["tomp.providers.planning"], havingValue = "bike", matchIfMissing = false)
class BikePlanningProvider : BasePlanningProvider() {
    protected override val fare: Fare?
        protected get() {
            val fare = Fare()
            val part = FarePart()
            part.type = FarePart.TypeEnum.FIXED
            part.currencyCode = "EUR"
            part.amount = 10.33.toFloat()
            part.vatRate = 21.0.toFloat()
            fare.addPartsItem(part)
            return fare
        }
    protected override val assetType: AssetType?
        protected get() {
            val assetType = AssetType()
            assetType.assetClass = AssetClass.BICYCLE
            assetType.assetSubClass = "Child, 26 inch"
            val sharedProperties = AssetProperties()
            sharedProperties.model = "Batavus"
            sharedProperties.energyLabel = EnergyLabelEnum.A
            assetType.sharedProperties = sharedProperties
            return assetType
        }

    override fun getConditionsForLeg(result: Leg?, acceptLanguage: String?): List<OneOflegConditionsItems?>? {
        val condition = ConditionReturnArea()
        condition.conditionType = "conditionReturnArea"
        condition.id = "Haarlem"
        val geometry = GeojsonPolygon()
        GeoUtil.addPoint(geometry, 4.599516, 52.42857)
        GeoUtil.addPoint(geometry, 4.686921, 52.42857)
        GeoUtil.addPoint(geometry, 4.686921, 52.338906)
        GeoUtil.addPoint(geometry, 4.599516, 52.338906)
        GeoUtil.addPoint(geometry, 4.599516, 52.42857)
        condition.setReturnArea(geometry)
        val period = SystemHours()
        period.startTime = "12:18"
        period.endTime = "13:02"
        period.setDays(Arrays.asList(Day.MON))
        condition.setReturnHours(Arrays.asList(period))
        return Arrays.asList(condition as OneOflegConditionsItems)
    }
}
