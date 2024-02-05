package org.tomp.api.tripexecution

import io.swagger.model.Leg
import io.swagger.model.LegEvent
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException

@Component
@ConditionalOnProperty(value = ["tomp.providers.tripexecution"], havingValue = "none", matchIfMissing = false)
class NoTripExecutionProvider : TripExecutionProvider {
    override fun prepare(body: LegEvent?, acceptLanguage: String?, id: String?, maasId: String?): Leg? {
        throw ResponseStatusException(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun assignAsset(body: LegEvent?, acceptLanguage: String?, id: String?, maasId: String?): Leg? {
        throw ResponseStatusException(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun reserve(body: LegEvent?, acceptLanguage: String?, id: String?, maasId: String?): Leg? {
        throw ResponseStatusException(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun setInUse(body: LegEvent?, acceptLanguage: String?, id: String?, maasId: String?): Leg? {
        throw ResponseStatusException(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun pause(body: LegEvent?, acceptLanguage: String?, id: String?, maasId: String?): Leg? {
        throw ResponseStatusException(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun startFinishing(body: LegEvent?, acceptLanguage: String?, id: String?, maasId: String?): Leg? {
        throw ResponseStatusException(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun finish(body: LegEvent?, acceptLanguage: String?, id: String?, maasId: String?): Leg? {
        throw ResponseStatusException(HttpStatus.NOT_IMPLEMENTED)
    }
}
