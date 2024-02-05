package org.tomp.api.planning

import io.swagger.model.Planning
import io.swagger.model.PlanningRequest
import javax.validation.Valid

interface PlanningProvider {
    fun getOptions(body: @Valid PlanningRequest?, acceptLanguage: String?, bookingIntent: Boolean): Planning?
}
