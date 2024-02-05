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
import org.tomp.api.configuration.ExternalConfiguration
import org.tomp.api.repository.RegionContainer
import org.tomp.api.utils.ExternalFileService
import org.tomp.api.utils.ObjectFromFileProvider
import java.util.Collections

@Component
@ConditionalOnProperty(value = ["tomp.providers.operatorinformation"], havingValue = "generic", matchIfMissing = true)
class GenericOperatorInformationProvider : OperatorInformationProvider, RegionContainer {
    @Autowired
    var configuration: ExternalConfiguration? = null

    @Autowired
    var fileService: ExternalFileService? = null
    override fun getAvailableAssetTypes(acceptLanguage: String?): List<AssetType?>? {
        val provider = ObjectFromFileProvider<Array<AssetType>>()
        val list = ArrayList<AssetType?>()
        Collections.addAll(list, *provider.getObject(acceptLanguage, Array<AssetType>::class.java, configuration.getAssetFile()))
        return list
    }

    override fun getOperatorInformation(acceptLanguage: String?): SystemInformation? {
        val provider = ObjectFromFileProvider<SystemInformation>()
        return provider.getObject(acceptLanguage, SystemInformation::class.java, configuration.getSystemInformationFile())
    }

    override fun getStations(acceptLanguage: String?): List<StationInformation?>? {
        val provider = ObjectFromFileProvider<Array<StationInformation>>()
        val stationArray = provider.getObject(
            acceptLanguage, Array<StationInformation>::class.java,
            configuration.getStationsFile()
        )!!
        val stations: MutableList<StationInformation?> = ArrayList()
        for (i in stationArray.indices) {
            stations.add(stationArray[i])
        }
        return stations
    }

    override fun getRegions(acceptLanguage: String?): MutableList<SystemRegion?>? {
        val provider = ObjectFromFileProvider<Array<SystemRegion>>()
        val regionArray = provider.getObject(
            acceptLanguage, Array<SystemRegion>::class.java,
            configuration.getRegionsFile()
        )!!
        val regions: MutableList<SystemRegion?> = ArrayList()
        for (i in regionArray.indices) {
            regions.add(regionArray[i])
        }
        return regions
    }

    override fun getPricingPlans(acceptLanguage: String?): List<SystemPricingPlan> {
        return ArrayList()
    }

    override fun getHours(acceptLanguage: String?): List<SystemHours> {
        return ArrayList()
    }

    override fun getCalendar(acceptLanguage: String?): List<SystemCalendar> {
        return ArrayList()
    }

    override fun getMeta(acceptLanguage: String?): List<EndpointImplementation?>? {
        return fileService.getEndPoints()
    }

    override val regions: MutableList<SystemRegion?>?
        get() = getRegions("")
}
