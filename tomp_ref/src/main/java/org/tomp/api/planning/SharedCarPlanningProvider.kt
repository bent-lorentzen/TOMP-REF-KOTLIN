package org.tomp.api.planning

import io.swagger.model.ConditionPostponedCommit
import io.swagger.model.ConditionRequireBookingData
import io.swagger.model.ConditionRequireBookingData.RequiredFieldsEnum
import io.swagger.model.Fare
import io.swagger.model.FarePart
import io.swagger.model.FarePart.UnitTypeEnum
import io.swagger.model.Leg
import io.swagger.model.OneOflegConditionsItems
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component
import org.threeten.bp.temporal.ChronoUnit

@Component
@ConditionalOnProperty(value = ["tomp.providers.planning"], havingValue = "shared-car", matchIfMissing = false)
open class SharedCarPlanningProvider : GenericPlanningProvider() {
    protected override val fare: Fare?
        protected get() {
            val fare = Fare()
            fare.isEstimated = true
            val part = FarePart()
            part.amount = 5f
            part.currencyCode = "EUR"
            part.type = FarePart.TypeEnum.FIXED
            fare.addPartsItem(part)
            val part2 = FarePart()
            part2.amount = 1f
            part2.currencyCode = "EUR"
            part2.type = FarePart.TypeEnum.FLEX
            part2.units = 0.5f
            part2.unitType = UnitTypeEnum.HOUR
            fare.addPartsItem(part2)
            return fare
        }

    override fun getConditionsForLeg(leg: Leg?, acceptLanguage: String?): List<OneOflegConditionsItems?>? {
        val conditions = super.getConditionsForLeg(leg, acceptLanguage)
        val condition = ConditionPostponedCommit()
        condition.conditionType = "conditionPostponedCommit"
        condition.ultimateResponseTime = ChronoUnit.SECONDS.addTo(this.startTime, -3600)
        conditions!!.add(condition)
        val bookingDataCondition = ConditionRequireBookingData()
        bookingDataCondition.conditionType = "conditionRequireBookingData"
        bookingDataCondition.addRequiredFieldsItem(RequiredFieldsEnum.LICENSES)
        conditions.add(bookingDataCondition)
        return conditions
    }
}
