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
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException
import org.tomp.api.configuration.ExternalConfiguration
import org.tomp.api.configuration.OverpassConfiguration
import org.tomp.api.providers.assets.AssetProvider
import org.tomp.api.providers.regions.RegionProvider
import org.tomp.api.utils.ExternalFileService
import org.tomp.api.utils.GeoUtil
import org.tomp.api.utils.OSMUtil
import java.util.Arrays
import javax.annotation.PostConstruct

@Component
@ConditionalOnProperty(value = ["tomp.providers.operatorinformation"], havingValue = "overpass", matchIfMissing = false)
class OsmOperatorInformationProvider : OperatorInformationProvider {
    @Autowired
    var fileService: ExternalFileService? = null

    @Autowired
    var configuration: ExternalConfiguration? = null

    @Autowired
    var overpassConfiguration: OverpassConfiguration? = null

    @Autowired(required = false)
    var assetProvider: AssetProvider? = null

    @Autowired(required = false)
    var operatorInformationProvider: OperatorInformationProvider? = null

    @Autowired(required = false)
    var regionProvider: RegionProvider? = null
    var stations: MutableList<StationInformation?>? = null
    @PostConstruct
    fun postConstruct() {
        getStations("en")
    }

    override fun getAvailableAssetTypes(acceptLanguage: String?): List<AssetType?>? {
        return if (assetProvider != null) {
            assetProvider.getAssetTypes()
        } else ArrayList()
    }

    override fun getOperatorInformation(acceptLanguage: String?): SystemInformation? {
        if (operatorInformationProvider != null) {
            return operatorInformationProvider!!.getOperatorInformation(acceptLanguage)
        }
        throw ResponseStatusException(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun getStations(acceptLanguage: String?): List<StationInformation?>? {
        if (stations == null) {
            stations = ArrayList()
            val area = fileService.getArea()
            val boundingBox = GeoUtil.getBoundingBox(GeoUtil.toPolygon(area))
            val bb = doubleArrayOf(
                boundingBox!!.minY, boundingBox.minX, boundingBox.maxY,
                boundingBox.maxX
            )
            try {
                val overpassObjects = OSMUtil.getOverpassObjects(overpassConfiguration.getQl(), bb)
                for (p in overpassObjects!!) {
                    val e = StationInformation()
                    e.coordinates = p!!.coordinates
                    e.stationId = p.stationId
                    stations.add(e)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return stations
    }

    override fun getRegions(acceptLanguage: String?): MutableList<SystemRegion?>? {
        if (regionProvider != null) {
            return regionProvider!!.getRegions(acceptLanguage)
        }
        val region = SystemRegion()
        region.regionId = configuration.getMaasId()
        region.name = configuration.getAppName()
        region.setServiceArea(fileService.getArea())
        return Arrays.asList(region)
    }

    override fun getPricingPlans(acceptLanguage: String?): List<SystemPricingPlan> {
        throw ResponseStatusException(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun getHours(acceptLanguage: String?): List<SystemHours> {
        throw ResponseStatusException(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun getCalendar(acceptLanguage: String?): List<SystemCalendar> {
        throw ResponseStatusException(HttpStatus.NOT_IMPLEMENTED)
    }

    override fun getMeta(acceptLanguage: String?): List<EndpointImplementation?>? {
        return fileService.getEndPoints()
    }
}
