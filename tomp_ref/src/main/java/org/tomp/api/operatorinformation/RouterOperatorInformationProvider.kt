package org.tomp.api.operatorinformation

import io.swagger.model.AssetType
import io.swagger.model.EndpointImplementation
import io.swagger.model.StationInformation
import io.swagger.model.SystemCalendar
import io.swagger.model.SystemHours
import io.swagger.model.SystemInformation
import io.swagger.model.SystemPricingPlan
import io.swagger.model.SystemRegion
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component
import org.tomp.api.utils.RouterUtil
import java.util.Arrays

@Component
@ConditionalOnProperty(value = ["tomp.providers.operatorinformation"], havingValue = "router", matchIfMissing = false)
open class RouterOperatorInformationProvider : OperatorInformationProvider {
    @Autowired
    var routerUtil: RouterUtil? = null
    override fun getAvailableAssetTypes(acceptLanguage: String?): List<AssetType?>? {
        return try {
            val assetTypes = routerUtil!!.sendToTO(
                "GET", "/operator/available-assets/", Array<AssetType>::class.java, "",
                acceptLanguage
            )!!
            Arrays.asList(*assetTypes)
        } catch (e: Exception) {
            e.printStackTrace()
            ArrayList()
        }
    }

    override fun getOperatorInformation(acceptLanguage: String?): SystemInformation? {
        return try {
            routerUtil!!.sendToTO("GET", "/operator/information/", SystemInformation::class.java, "", acceptLanguage)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override fun getStations(acceptLanguage: String?): List<StationInformation?>? {
        return try {
            val assetTypes = routerUtil!!.sendToTO(
                "GET", "/operator/stations/",
                Array<StationInformation>::class.java, "", acceptLanguage
            )!!
            Arrays.asList(*assetTypes)
        } catch (e: Exception) {
            e.printStackTrace()
            ArrayList()
        }
    }

    override fun getRegions(acceptLanguage: String?): MutableList<SystemRegion?>? {
        return try {
            val assetTypes = routerUtil!!.sendToTO(
                "GET", "/operator/regions/", Array<SystemRegion>::class.java, "",
                acceptLanguage
            )!!
            Arrays.asList(*assetTypes)
        } catch (e: Exception) {
            e.printStackTrace()
            ArrayList()
        }
    }

    override fun getPricingPlans(acceptLanguage: String?): List<SystemPricingPlan> {
        return try {
            val assetTypes = routerUtil!!.sendToTO(
                "GET", "/operator/pricing/", Array<SystemPricingPlan>::class.java,
                "", acceptLanguage
            )!!
            Arrays.asList(*assetTypes)
        } catch (e: Exception) {
            e.printStackTrace()
            ArrayList()
        }
    }

    override fun getHours(acceptLanguage: String?): List<SystemHours> {
        return try {
            val assetTypes = routerUtil!!.sendToTO(
                "GET", "/operator/operating-hours/", Array<SystemHours>::class.java, "",
                acceptLanguage
            )!!
            Arrays.asList(*assetTypes)
        } catch (e: Exception) {
            e.printStackTrace()
            ArrayList()
        }
    }

    override fun getCalendar(acceptLanguage: String?): List<SystemCalendar> {
        return try {
            val assetTypes = routerUtil!!.sendToTO(
                "GET", "/operator/operating-calendar/",
                Array<SystemCalendar>::class.java, "", acceptLanguage
            )!!
            Arrays.asList(*assetTypes)
        } catch (e: Exception) {
            e.printStackTrace()
            ArrayList()
        }
    }

    override fun getMeta(acceptLanguage: String?): List<EndpointImplementation?>? {
        return try {
            val assetTypes = routerUtil!!.sendToTO(
                "GET", "/operator/meta/",
                Array<EndpointImplementation>::class.java, "", acceptLanguage
            )!!
            Arrays.asList(*assetTypes)
        } catch (e: Exception) {
            e.printStackTrace()
            ArrayList()
        }
    }
}
