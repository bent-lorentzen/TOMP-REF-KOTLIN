package org.tomp.api.providers.conditions

import io.swagger.model.Leg
import io.swagger.model.OneOflegConditionsItems
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component
import org.tomp.api.configuration.ExternalConfiguration
import org.tomp.api.utils.ObjectFromFileProvider

@Component
@ConditionalOnProperty(value = ["tomp.condition-file"], matchIfMissing = false)
class FileBasedConditionProvider : ConditionProvider {
    @Autowired
    protected var configuration: ExternalConfiguration? = null
    fun getConditions(acceptLanguage: String?): List<OneOflegConditionsItems> {
        val conditionFileProvider = ObjectFromFileProvider<Array<OneOflegConditionsItems>>()
        val conditions = conditionFileProvider.getObject(
            acceptLanguage,
            Array<OneOflegConditionsItems>::class.java, configuration.getConditionFile()
        )!!
        val conditionList: MutableList<OneOflegConditionsItems> = ArrayList()
        for (c in conditions) {
            conditionList.add(c)
        }
        return conditionList
    }

    override fun getApplyingConditions(acceptLanguage: String?, result: Leg?): List<OneOflegConditionsItems> {
        return getConditions(acceptLanguage)
    }
}
