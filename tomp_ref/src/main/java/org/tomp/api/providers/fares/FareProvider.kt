package org.tomp.api.providers.fares

import io.swagger.model.Fare

interface FareProvider {
    val fare: Fare?
}
