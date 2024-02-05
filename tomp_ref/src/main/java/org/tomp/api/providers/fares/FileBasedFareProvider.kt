package org.tomp.api.providers.fares

import io.swagger.model.Fare
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component
import org.tomp.api.configuration.ExternalConfiguration
import org.tomp.api.utils.ObjectFromFileProvider

@Component
@Primary
@ConditionalOnProperty(value = ["tomp.fare-file"], matchIfMissing = false)
class FileBasedFareProvider : FareProvider {
    @Autowired
    var configuration: ExternalConfiguration? = null
    override val fare: Fare?
        get() {
            val conditionFileProvider = ObjectFromFileProvider<Fare>()
            return conditionFileProvider.getObject("", Fare::class.java, configuration.getFareFile())
        }
}
