package org.tomp.api.planning

import io.swagger.model.ConditionPostponedCommit
import io.swagger.model.ConditionRequireBookingData
import io.swagger.model.ConditionRequireBookingData.RequiredFieldsEnum
import io.swagger.model.Leg
import io.swagger.model.OneOflegConditionsItems
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component
import org.threeten.bp.temporal.ChronoUnit

@Component
@ConditionalOnProperty(value = ["tomp.providers.planning"], havingValue = "taxi", matchIfMissing = false)
class TaxiPlanningProvider : SharedCarPlanningProvider() {
    override fun getConditionsForLeg(leg: Leg?, acceptLanguage: String?): List<OneOflegConditionsItems?>? {
        val conditions: MutableList<OneOflegConditionsItems?> = ArrayList()
        val condition = ConditionPostponedCommit()
        condition.conditionType = "conditionPostponedCommit"
        condition.ultimateResponseTime = ChronoUnit.SECONDS.addTo(this.startTime, -3600)
        conditions.add(condition)
        val bookingDataCondition = ConditionRequireBookingData()
        bookingDataCondition.conditionType = "conditionRequireBookingData"
        bookingDataCondition.addRequiredFieldsItem(RequiredFieldsEnum.FROM_ADDRESS)
        bookingDataCondition.addRequiredFieldsItem(RequiredFieldsEnum.TO_ADDRESS)
        conditions.add(bookingDataCondition)
        return conditions
    }
}
