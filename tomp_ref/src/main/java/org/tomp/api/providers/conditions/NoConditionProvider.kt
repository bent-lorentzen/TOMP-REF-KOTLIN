package org.tomp.api.providers.conditions

import io.swagger.model.Leg
import io.swagger.model.OneOflegConditionsItems
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component
import org.tomp.api.configuration.ExternalConfiguration

@Component
@ConditionalOnProperty(value = ["tomp.condition-file"], havingValue = "empty", matchIfMissing = false)
class NoConditionProvider : ConditionProvider {
    @Autowired
    protected var configuration: ExternalConfiguration? = null
    override fun getApplyingConditions(acceptLanguage: String?, result: Leg?): List<OneOflegConditionsItems> {
        return ArrayList()
    }
}
