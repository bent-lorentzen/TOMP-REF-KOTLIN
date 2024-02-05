package org.tomp.api.planning

import io.swagger.model.Planning
import io.swagger.model.PlanningRequest
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException
import javax.validation.Valid

@Component
@ConditionalOnProperty(value = ["tomp.providers.planning"], havingValue = "none", matchIfMissing = false)
class NoPlanningProvider : PlanningProvider {
    override fun getOptions(body: @Valid PlanningRequest?, acceptLanguage: String?, bookingIntent: Boolean): Planning? {
        throw ResponseStatusException(HttpStatus.NOT_IMPLEMENTED)
    }
}
