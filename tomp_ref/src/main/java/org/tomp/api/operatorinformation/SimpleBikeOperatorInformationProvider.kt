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
import org.tomp.api.utils.ExternalFileService
import org.tomp.api.utils.ObjectFromFileProvider
import java.util.Arrays
import java.util.Collections

@Component
@ConditionalOnProperty(value = ["tomp.providers.operatorinformation"], havingValue = "bike", matchIfMissing = false)
class SimpleBikeOperatorInformationProvider : OperatorInformationProvider {
    @Autowired
    var configuration: ExternalConfiguration? = null

    @Autowired
    var fileService: ExternalFileService? = null
    override fun getAvailableAssetTypes(acceptLanguage: String?): List<AssetType?>? {
        val provider = ObjectFromFileProvider<Array<AssetType>>()
        val list = ArrayList<AssetType?>()
        val assets = provider.getObject(acceptLanguage, Array<AssetType>::class.java, configuration.getAssetFile())!!
        Collections.addAll(list, *assets)
        return list
    }

    override fun getOperatorInformation(acceptLanguage: String?): SystemInformation? {
        val info = SystemInformation()
        info.systemId = "maas-3234434"
        info.email = "email@bike-operator.org"
        info.setLanguage(Arrays.asList(acceptLanguage))
        info.name = "Bike Operator"
        return info
    }

    override fun getStations(acceptLanguage: String?): List<StationInformation?>? {
        val provider = ObjectFromFileProvider<Array<StationInformation>>()
        return Arrays.asList(
            *provider.getObject(acceptLanguage, Array<StationInformation>::class.java, configuration.getStationsFile())
        )
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
            println(regionArray[i].toString())
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
}
