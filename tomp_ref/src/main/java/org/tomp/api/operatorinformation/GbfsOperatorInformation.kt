package org.tomp.api.operatorinformation

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import io.swagger.model.AssetType
import io.swagger.model.Coordinates
import io.swagger.model.Day
import io.swagger.model.Day.Companion.fromValue
import io.swagger.model.EndpointImplementation
import io.swagger.model.StationInformation
import io.swagger.model.SystemCalendar
import io.swagger.model.SystemHours
import io.swagger.model.SystemHours.UserTypeEnum
import io.swagger.model.SystemInformation
import io.swagger.model.SystemPricingPlan
import io.swagger.model.SystemRegion
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component
import org.tomp.api.configuration.GbfsDataConfiguration
import org.tomp.api.model.gbfs.Gbfs
import org.tomp.api.model.gbfs.GbfsLanguageFeed
import org.tomp.api.repository.GbfsRepository
import org.tomp.api.utils.ExternalFileService
import org.tomp.api.utils.GeoCoderUtil
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.Reader
import java.net.URL
import java.nio.charset.StandardCharsets
import java.util.Arrays
import java.util.Locale
import java.util.Timer
import java.util.TimerTask
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import java.util.concurrent.ForkJoinPool
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.ScheduledThreadPoolExecutor
import java.util.concurrent.TimeUnit
import javax.annotation.PostConstruct

@Component
@ConditionalOnProperty(value = ["tomp.providers.operatorinformation"], havingValue = "gbfs", matchIfMissing = false)
class GbfsOperatorInformation : OperatorInformationProvider {
    @Autowired
    var configuration: GbfsDataConfiguration? = null

    @Autowired
    var geocoderUtil: GeoCoderUtil? = null

    @Autowired
    var repository: GbfsRepository? = null

    @Autowired
    private val fileService: ExternalFileService? = null
    private val mapper = ObjectMapper()
    private var gbfs: Gbfs? = null
    private val task: TimerTask = object : TimerTask() {
        override fun run() {
            processAssetInformation()
        }
    }

    @PostConstruct
    fun readGbfsInformation() {
        configureMapper()
        processGbfsUrls()
        processOperatorInformation()
        processRegionInformation()
        processStationInformation()
        processAssetInformation()
        processOpeningHoursInformation()
        processOpeningDaysInformation()
        startTimer()
    }

    private fun processOpeningDaysInformation() {
        val systemDays: HashMap<String, Any>? = getObjectFromUrl(getLink("system_calendar"), HashMap::class.java)
        if (systemDays != null) {
            val calendarData = (systemDays["data"] as HashMap<String?, Any?>?)!!["calendars"] as ArrayList<HashMap<String, Any>>?
            processCalendars(calendarData)
        }
    }

    private fun processCalendars(calendarData: ArrayList<HashMap<String, Any>>?) {
        repository.getSystemCalendar().clear()
        for (info in calendarData!!) {
            val calendar = SystemCalendar()
            if (info.containsKey("start_year")) calendar.startYear = info["start_year"].toString().toInt()
            if (info.containsKey("start_month")) calendar.startMonth = info["start_month"].toString().toInt()
            if (info.containsKey("start_day")) calendar.startDay = info["start_day"].toString().toInt()
            if (info.containsKey("end_year")) calendar.endYear = info["end_year"].toString().toInt()
            if (info.containsKey("end_month")) calendar.endMonth = info["end_month"].toString().toInt()
            if (info.containsKey("end_day")) calendar.endDay = info["end_day"].toString().toInt()
            repository.getSystemCalendar().add(calendar)
        }
    }

    private fun processOpeningHoursInformation() {
        val systemHours: HashMap<String, Any>? = getObjectFromUrl(getLink("system_hours"), HashMap::class.java)
        if (systemHours != null) {
            val hours = (systemHours["data"] as HashMap<String?, Any?>?)!!["rental_hours"] as ArrayList<HashMap<String, Any>>?
            processSystemHours(hours)
        }
    }

    private fun processSystemHours(systemHourData: ArrayList<HashMap<String, Any>>?) {
        repository.getSystemHours().clear()
        for (info in systemHourData!!) {
            val hours = SystemHours()
            if (info["user_types"].toString().contains("nonmember")) {
                hours.userType = UserTypeEnum.NON_MEMBERS
            } else {
                hours.userType = UserTypeEnum.MEMBER
            }
            hours.setDays(toDays(info["days"]))
            hours.startTime = info["start_time"].toString()
            hours.endTime = info["end_time"].toString()
            repository.getSystemHours().add(hours)
        }
    }

    private fun toDays(dayList: Any?): List<Day?> {
        val days: MutableList<Day?> = ArrayList()
        val importDays = dayList as List<String>?
        for (importDay in importDays!!) {
            days.add(fromValue(importDay.uppercase(Locale.getDefault())))
        }
        return days
    }

    private fun processGbfsUrls() {
        gbfs = getObjectFromUrl(configuration.getOpendataUrl(), Gbfs::class.java)
        repository!!.setLanguages(Arrays.asList(*gbfs.getGbfsdata().keys.toArray(arrayOf<String>())))
    }

    private fun <T> getObjectFromUrl(url: String?, valueType: Class<T>): T? {
        try {
            URL(url).openStream().use { `is` ->
                val rd = BufferedReader(InputStreamReader(`is`, StandardCharsets.UTF_8))
                val jsonText = readAll(rd)
                return mapper.readValue(jsonText, valueType)
            }
        } catch (e: Exception) {
            log.error(e.message)
        }
        return null
    }

    private fun processOperatorInformation() {
        val systemInformation: HashMap<String, Any>? = getObjectFromUrl(getLink("system_information"), HashMap::class.java)
        if (systemInformation != null) {
            repository!!.setOperatorInformation(systemInformation["data"] as HashMap<String, String>?)
        }
    }

    private fun processStationInformation() {
        val stationInformation: HashMap<String, Any>? = getObjectFromUrl(getLink("station_information"), HashMap::class.java)
        if (stationInformation != null) {
            val stationData = (stationInformation["data"] as HashMap<String?, Any?>?)!!["stations"] as ArrayList<HashMap<String, Any>>?
            processStations(stationData)
        }
    }

    private fun processAssetInformation() {
        var systemInformation: HashMap<String?, Any?>? = getObjectFromUrl(getLink("station_status"), HashMap::class.java)
        if (systemInformation != null) {
            repository!!.setBikesAtStations(
                (systemInformation["data"] as HashMap<String?, Any?>?)
                    .get("stations") as ArrayList<HashMap<String?, Any>>?
            )
        }
        systemInformation = getObjectFromUrl(getLink("free_bike_status"), HashMap::class.java)
        if (systemInformation != null) {
            repository!!.setFreeBikes(
                (systemInformation["data"] as HashMap<String?, Any?>?)
                    .get("bikes") as ArrayList<HashMap<String?, Any>>?
            )
        }
    }

    private fun processRegionInformation() {
        val regionInformation: HashMap<String, Any>? = getObjectFromUrl(getLink("system_regions"), HashMap::class.java)
        if (regionInformation != null) {
            val regions = (regionInformation["data"] as HashMap<String?, Any?>?)!!["regions"] as ArrayList<HashMap<String, String>>?
            geocodeRegions(regions)
        }
    }

    private fun configureMapper() {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    }

    private fun getLink(key: String): String? {
        var feed: GbfsLanguageFeed? = null
        feed = if (gbfs.getGbfsdata().containsKey("en")) {
            gbfs.getGbfsdata()["en"]
        } else {
            gbfs.getGbfsdata().values.stream().findFirst().get()
        }
        for (link in feed.getFeeds()) {
            if (link.name == key) {
                return link.url
            }
        }
        return null
    }

    private fun geocodeRegions(regionInformation: ArrayList<HashMap<String, String>>?) {
        if (regionInformation != null) {
            for (region in regionInformation) {
                val systemRegion = SystemRegion()
                systemRegion.regionId = region["region_id"]
                systemRegion.name = region["name"]
                if (geocoderUtil!!.isActive) {
                    systemRegion.setServiceArea(geocoderUtil!!.getRegionByName(region["name"])!!)
                }
                repository.getRegions().add(systemRegion)
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    log.error(e.message)
                }
            }
        }
    }

    private fun startTimer() {
        val timer = Timer("GBFSTimer")
        val delay = configuration.getRefreshInMillis()
        timer.schedule(task, delay)
    }

    override fun getAvailableAssetTypes(acceptLanguage: String?): List<AssetType?>? {
        return repository.getAssets()
    }

    override fun getOperatorInformation(acceptLanguage: String?): SystemInformation? {
        return repository!!.getOperatorInformation()
    }

    override fun getStations(acceptLanguage: String?): List<StationInformation?>? {
        return repository.getStations()
    }

    private fun processStations(stationData: ArrayList<HashMap<String, Any>>?) {
        repository.getStations().clear()
        var delay = 0
        for (info in stationData!!) {
            val station = StationInformation()
            val coordinates = Coordinates()
            coordinates.lat = info["lat"].toString().toDouble().toFloat()
            coordinates.lng = info["lon"].toString().toDouble().toFloat()
            station.coordinates = coordinates
            station.name = info["name"].toString()
            if (info.containsKey("region_id")) {
                station.regionId = info["region_id"].toString()
            }
            station.stationId = info["station_id"].toString()
            repository.getStations().add(station)
            delay += 1
            if (geocoderUtil!!.isDecodingActive) {
                CompletableFuture.runAsync({ geodecodeStation(station) }, delayedExecutor(delay.toLong(), TimeUnit.SECONDS))
            }
        }
    }

    private fun geodecodeStation(station: StationInformation) {
        station.physicalAddress = geocoderUtil!!.getPhysicalAddressByCoordinate(station.coordinates)
    }

    override fun getRegions(acceptLanguage: String?): MutableList<SystemRegion?>? {
        return repository.getRegions()
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
        private val log = LoggerFactory.getLogger(GbfsOperatorInformation::class.java)
        private val SCHEDULER: ScheduledExecutorService = ScheduledThreadPoolExecutor(0)
        @JvmOverloads
        fun delayedExecutor(delay: Long, unit: TimeUnit?, executor: Executor = ForkJoinPool.commonPool()): Executor {
            return Executor { r: Runnable? -> SCHEDULER.schedule({ executor.execute(r) }, delay, unit) }
        }
    }
}
