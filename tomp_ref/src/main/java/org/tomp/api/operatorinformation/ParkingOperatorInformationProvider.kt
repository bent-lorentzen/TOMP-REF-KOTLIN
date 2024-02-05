package org.tomp.api.operatorinformation

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import io.swagger.model.AssetClass
import io.swagger.model.AssetProperties
import io.swagger.model.AssetType
import io.swagger.model.Coordinates
import io.swagger.model.EndpointImplementation
import io.swagger.model.GeojsonPolygon
import io.swagger.model.StationInformation
import io.swagger.model.SystemCalendar
import io.swagger.model.SystemHours
import io.swagger.model.SystemInformation
import io.swagger.model.SystemPricingPlan
import io.swagger.model.SystemRegion
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component
import org.tomp.api.configuration.ExternalConfiguration
import org.tomp.api.configuration.ParkingDataConfiguration
import org.tomp.api.model.parking.DynamicParkingData
import org.tomp.api.model.parking.ParkingData
import org.tomp.api.model.parking.ParkingFacilityDynamicInformation
import org.tomp.api.model.parking.ParkingFacilityInformation
import org.tomp.api.model.parking.StaticParkingData
import org.tomp.api.repository.ParkingRepository
import org.tomp.api.utils.ExternalFileService
import org.tomp.api.utils.GeoUtil
import org.tomp.api.utils.ObjectFromFileProvider
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.Reader
import java.net.URL
import java.nio.charset.StandardCharsets
import java.util.Arrays
import java.util.Timer
import java.util.TimerTask
import javax.annotation.PostConstruct

@Component
@ConditionalOnProperty(value = ["tomp.providers.operatorinformation"], havingValue = "parking", matchIfMissing = false)
class ParkingOperatorInformationProvider : OperatorInformationProvider {
    var task: TimerTask = object : TimerTask() {
        override fun run() {
            refreshDynamicData()
        }
    }

    @Autowired
    var repository: ParkingRepository? = null

    @Autowired
    var configuration: ParkingDataConfiguration? = null

    @Autowired
    var fileConfiguration: ExternalConfiguration? = null

    @Autowired
    var fileService: ExternalFileService? = null
    var mapper = ObjectMapper()
    @PostConstruct
    fun postConstructor() {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        readUrls()
        val timer = Timer("ParkingTimer")
        val delay = configuration.getRefreshInMillis()
        timer.schedule(task, delay)
    }

    private fun readUrls() {
        val url = configuration.getOpendataUrl()
        var parkingData: ParkingData? = null
        try {
            URL(url).openStream().use { `is` ->
                val rd = BufferedReader(InputStreamReader(`is`, StandardCharsets.UTF_8))
                val jsonText = readAll(rd)
                parkingData = mapper.readValue(jsonText, ParkingData::class.java)
            }
        } catch (e: Exception) {
            log.error(e.message)
            return
        }
        fetchInfo(parkingData)
    }

    private fun fetchInfo(parkingData: ParkingData?) {
        val uuids = Arrays.asList(*configuration.getUuids())
        val namePart = configuration.getNameContains()

        // Arrays.asList(parkingData.getParkingFacilities()).stream().parallel().forEach(facility
        // -> {
        for (facility in parkingData.getParkingFacilities()) {
            try {
                if (uuids.isEmpty() || uuids.contains(facility.uuid)) {
                    if (namePart == null || facility.name.indexOf(namePart) != -1) {
                        if (facility.geoLocation != null) {
                            val e = SystemRegion()
                            e.name = facility.name
                            e.regionId = facility.uuid
                            e.setServiceArea(GeoUtil.toPolygon(facility.geoLocation, configuration.getR()))
                            repository!!.regions!!.add(e)
                        }
                        registrateStaticInfo(facility.staticDataUrl)
                        val dynamicDataUrl = facility.dynamicDataUrl
                        if (dynamicDataUrl != null) {
                            registrateDynamicInfo(dynamicDataUrl)
                            repository.getDynamicUrls()[facility.uuid] = dynamicDataUrl
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun registrateDynamicInfo(url: String?) {
        if (url != null) {
            var parkingData: DynamicParkingData? = null
            try {
                URL(url).openStream().use { `is` ->
                    val rd = BufferedReader(InputStreamReader(`is`, StandardCharsets.UTF_8))
                    val jsonText = readAll(rd)
                    parkingData = mapper.readValue(jsonText, DynamicParkingData::class.java)
                }
            } catch (e: Exception) {
                log.error(e.message)
                return
            }
            if (parkingData != null) {
                repository.getDynamicData()[parkingData.getParkingFacilityDynamicInformation().identifier] =
                    parkingData.getParkingFacilityDynamicInformation()
            }
        }
    }

    private fun registrateStaticInfo(url: String?) {
        var parkingData: StaticParkingData? = null
        try {
            URL(url).openStream().use { `is` ->
                val rd = BufferedReader(InputStreamReader(`is`, StandardCharsets.UTF_8))
                val jsonText = readAll(rd)
                parkingData = mapper.readValue(jsonText, StaticParkingData::class.java)
            }
        } catch (e: Exception) {
            log.error(e.message)
            return
        }
        val information = parkingData.getInformation()
        repository.getStaticData()[information.identifier] = information
        repository!!.regions!!.addAll(convertToRegion(information))
        repository.getStations().add(convertToStation(information))
        log.info("Registrated {}", url)
    }

    private fun convertToStation(information: ParkingFacilityInformation?): StationInformation {
        val station = StationInformation()
        station.name = information.getName()
        station.stationId = information.getIdentifier()
        station.regionId = information.getIdentifier()
        return station
    }

    private fun convertToRegion(information: ParkingFacilityInformation?): List<SystemRegion?> {
        val regionList: MutableList<SystemRegion?> = ArrayList()
        if (information!!.accessPoints != null && information.accessPoints!!.size > 0) {
            for (point in information.accessPoints!!) {
                if (point != null && point.accessPointLocation != null) {
                    for (location in point.accessPointLocation!!) {
                        val region = SystemRegion()
                        region.name = information.name
                        region.regionId = information.identifier
                        region.setServiceArea(GeoUtil.toPolygon(location, configuration.getR()))
                        regionList.add(region)
                        break
                    }
                }
            }
        } else {
            for (area in information.specifications) {
                if (area != null) {
                    val region = SystemRegion()
                    region.name = information.name
                    region.regionId = information.identifier
                    val areaGeometry = area.areaGeometry
                    if (areaGeometry != null && areaGeometry.type == "Polygon") {
                        region.setServiceArea(toServiceArea(areaGeometry.polygon))
                    }
                    // TODO MultiPolygon
                    regionList.add(region)
                }
            }
        }
        return regionList
    }

    private fun toServiceArea(area: Array<Array<Array<Float?>?>?>?): GeojsonPolygon {
        val p = GeojsonPolygon()
        for (c in toCoordinates(area)) {
            GeoUtil.addPoint(p, c.lng, c.lat)
        }
        return p
    }

    private fun toCoordinates(polygon: Array<Array<Array<Float?>?>?>?): List<Coordinates> {
        val coords: MutableList<Coordinates> = ArrayList()
        if (polygon != null) {
            for (b in polygon) {
                if (b != null) {
                    for (c in b) {
                        val e = Coordinates()
                        e.lng = c!![0]
                        e.lat = c[1]
                        coords.add(e)
                    }
                    if (coords[0] !== coords[coords.size - 1]) {
                        coords.add(coords[0])
                    }
                }
            }
        }
        return coords
    }

    @Throws(IOException::class)
    private fun readAll(rd: Reader): String {
        val sb = StringBuilder()
        var cp: Int
        while (rd.read().also { cp = it } != -1) {
            sb.append(cp.toChar())
        }
        return sb.toString()
    }

    override fun getAvailableAssetTypes(acceptLanguage: String?): List<AssetType?>? {
        val assets: MutableList<AssetType?> = ArrayList()
        for (data in repository.getDynamicData().values) {
            assets.add(convertToAssetType(data))
        }
        return assets
    }

    private fun convertToAssetType(data: ParkingFacilityDynamicInformation?): AssetType {
        val type = AssetType()
        val sharedProperties = AssetProperties()
        sharedProperties.name = data.getName()
        type.sharedProperties = sharedProperties
        type.assetClass = AssetClass.PARKING
        type.nrAvailable = data.getFacilityActualStatus().vacantSpaces.toInt()
        return type
    }

    override fun getStations(acceptLanguage: String?): List<StationInformation?>? {
        return repository.getStations()
    }

    override fun getRegions(acceptLanguage: String?): MutableList<SystemRegion?>? {
        return repository!!.regions
    }

    override fun getOperatorInformation(acceptLanguage: String?): SystemInformation? {
        val provider = ObjectFromFileProvider<SystemInformation>()
        return provider.getObject(
            acceptLanguage, SystemInformation::class.java,
            fileConfiguration.getSystemInformationFile()
        )
    }

    fun refreshDynamicData() {
        for ((_, value) in repository.getDynamicUrls()) {
            registrateDynamicInfo(value)
        }
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

    companion object {
        private val log = LoggerFactory.getLogger(ParkingOperatorInformationProvider::class.java)
    }
}
