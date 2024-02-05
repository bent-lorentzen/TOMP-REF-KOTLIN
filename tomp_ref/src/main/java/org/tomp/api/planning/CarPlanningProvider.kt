package org.tomp.api.planning

import io.swagger.model.AssetClass
import io.swagger.model.AssetProperties
import io.swagger.model.AssetProperties.EnergyLabelEnum
import io.swagger.model.AssetType
import io.swagger.model.Fare
import io.swagger.model.FarePart
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component

@Component
@ConditionalOnProperty(value = ["tomp.providers.planning"], havingValue = "car", matchIfMissing = false)
class CarPlanningProvider : BasePlanningProvider() {
    protected override val fare: Fare?
        protected get() {
            val fare = Fare()
            val part = FarePart()
            part.type = FarePart.TypeEnum.FIXED
            part.currencyCode = "EUR"
            part.amount = 3.33.toFloat()
            part.vatRate = 21.0.toFloat()
            fare.addPartsItem(part)
            return fare
        }
    protected override val assetType: AssetType?
        protected get() {
            val assetType = AssetType()
            assetType.assetClass = AssetClass.CAR
            assetType.assetSubClass = "Small car"
            val sharedProperties = AssetProperties()
            sharedProperties.model = "Peugeot 208"
            sharedProperties.energyLabel = EnergyLabelEnum.A
            assetType.sharedProperties = sharedProperties
            return assetType
        }
}
