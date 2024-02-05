package org.tomp.api.providers.conditions

import io.swagger.model.Leg
import io.swagger.model.OneOflegConditionsItems

interface ConditionProvider {
    fun getApplyingConditions(acceptLanguage: String?, result: Leg?): List<OneOflegConditionsItems>
}
