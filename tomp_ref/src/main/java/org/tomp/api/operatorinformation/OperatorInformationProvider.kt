package org.tomp.api.operatorinformation

import io.swagger.model.AssetType
import io.swagger.model.EndpointImplementation
import io.swagger.model.StationInformation
import io.swagger.model.SystemCalendar
import io.swagger.model.SystemHours
import io.swagger.model.SystemInformation
import io.swagger.model.SystemPricingPlan
import io.swagger.model.SystemRegion

interface OperatorInformationProvider {
    fun getAvailableAssetTypes(acceptLanguage: String?): List<AssetType?>?
    fun getOperatorInformation(acceptLanguage: String?): SystemInformation?
    fun getStations(acceptLanguage: String?): List<StationInformation?>?
    fun getRegions(acceptLanguage: String?): MutableList<SystemRegion?>?
    fun getPricingPlans(acceptLanguage: String?): List<SystemPricingPlan>
    fun getHours(acceptLanguage: String?): List<SystemHours>
    fun getCalendar(acceptLanguage: String?): List<SystemCalendar>
    fun getMeta(acceptLanguage: String?): List<EndpointImplementation?>?
}
