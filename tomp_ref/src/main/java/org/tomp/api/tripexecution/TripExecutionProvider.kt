package org.tomp.api.tripexecution

import io.swagger.model.Leg
import io.swagger.model.LegEvent

interface TripExecutionProvider {
    fun prepare(body: LegEvent?, acceptLanguage: String?, id: String?, maasId: String?): Leg?
    fun assignAsset(body: LegEvent?, acceptLanguage: String?, id: String?, maasId: String?): Leg?
    fun reserve(body: LegEvent?, acceptLanguage: String?, id: String?, maasId: String?): Leg?
    fun setInUse(body: LegEvent?, acceptLanguage: String?, id: String?, maasId: String?): Leg?
    fun pause(body: LegEvent?, acceptLanguage: String?, id: String?, maasId: String?): Leg?
    fun startFinishing(body: LegEvent?, acceptLanguage: String?, id: String?, maasId: String?): Leg?
    fun finish(body: LegEvent?, acceptLanguage: String?, id: String?, maasId: String?): Leg?
}
