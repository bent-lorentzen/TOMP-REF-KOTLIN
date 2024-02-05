package org.tomp.api.providers.fares

import io.swagger.model.Fare
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component

@Component
@ConditionalOnProperty(value = ["tomp.fare-file"], matchIfMissing = true)
class NoFareProvider : FareProvider {
    override val fare: Fare?
        get() = Fare()
}
