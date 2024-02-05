package org.tomp.api.mp

import com.fasterxml.jackson.core.JsonProcessingException
import io.swagger.client.ApiException
import io.swagger.model.AssetType
import io.swagger.model.Coordinates
import io.swagger.model.GeojsonLine
import io.swagger.model.GeojsonPoint
import io.swagger.model.GeojsonPolygon
import io.swagger.model.SystemInformation
import io.swagger.model.SystemRegion
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component
import org.tomp.api.model.LookupService
import org.tomp.api.model.MaasEnvironmentType
import org.tomp.api.model.MaasOperator
import org.tomp.api.model.Segment
import org.tomp.api.model.TransportOperator
import org.tomp.api.utils.ClientUtil
import org.tomp.api.utils.ExternalFileService
import org.tomp.api.utils.GeoUtil
import java.util.Collections
import javax.annotation.PostConstruct

@Component
@ConditionalOnProperty(value = ["tomp.providers.planning"], havingValue = "maasprovider", matchIfMissing = false)
class TOProvider @Autowired constructor(
    private val clientUtil: ClientUtil,
    private val lookupService: LookupService,
    private val fileService: ExternalFileService
) {
    var cache: MutableList<TransportOperator> = ArrayList()
    @PostConstruct
    private fun populateTOs() {
        getOperatorsInArea(fileService.getArea())
    }

    private fun getOperatorsInArea(area: GeojsonPolygon?) {
        val data: Array<MaasOperator?>?
        try {
            data = lookupService.findOperators(area)
            for (i in data.indices) {
                if (data[i].getType() == MaasEnvironmentType.TO) {
                    val operator = TransportOperator()
                    operator.name = data[i].getName()
                    operator.id = data[i].getId()
                    operator.url = data[i].getUrl()
                    populateTransportOperatorInfo(operator)
                }
            }
        } catch (e: JsonProcessingException) {
            log.error(e.message)
        }
    }

    fun clearCache() {
        cache.clear()
    }

    fun getTransportOperators(segment: Segment?): List<TransportOperator> {
        if (cache.isEmpty()) {
            populateTOs()
        }
        if (!segmentInCache(segment)) {
            getOperatorsInArea(getBoundingBox(segment))
        }
        return cache
    }

    fun getTransportOperator(id: String): TransportOperator? {
        for (o in cache) {
            if (o.id == id) return o
        }
        return null
    }

    private fun getBoundingBox(segment: Segment?): GeojsonPolygon {
        val p = GeojsonPolygon()
        var minLng = Double.MAX_VALUE
        var minLat = Double.MAX_VALUE
        var maxLng = Double.MIN_VALUE
        var maxLat = Double.MIN_VALUE
        var lat = segment!!.from!!.coordinates!!.lat!!.toDouble()
        if (lat < minLat) minLat = lat
        if (lat > maxLat) maxLat = lat
        var lng = segment.from!!.coordinates!!.lng!!.toDouble()
        if (lng < minLng) minLng = lng
        if (lng > maxLng) maxLng = lng
        lat = segment.to!!.coordinates!!.lat!!.toDouble()
        if (lat < minLat) minLat = lat
        if (lat > maxLat) maxLat = lat
        lng = segment.to!!.coordinates!!.lng!!.toDouble()
        if (lng < minLng) minLng = lng
        if (lng > maxLng) maxLng = lng
        val start = toCoordinates(minLng, minLat)
        p.add(GeojsonLine())
        p[0]!!.add(start)
        p[0]!!.add(toCoordinates(minLng, maxLat))
        p[0]!!.add(toCoordinates(maxLng, maxLat))
        p[0]!!.add(toCoordinates(maxLng, minLat))
        p[0]!!.add(start)
        return p
    }

    private fun toCoordinates(minLng: Double, minLat: Double): GeojsonPoint {
        val start = GeojsonPoint()
        start.add(minLng.toFloat())
        start.add(minLat.toFloat())
        return start
    }

    private fun segmentInCache(segment: Segment?): Boolean {
        if (segment != null) {
            for (operator in cache) {
                for (region in operator.regions) {
                    if (isRegion(region, segment.from!!.coordinates)
                        || isRegion(region, segment.to!!.coordinates)
                    ) {
                        return true
                    }
                }
            }
            return false
        }
        return true
    }

    private fun isRegion(region: SystemRegion?, coord: Coordinates?): Boolean {
        var minLng = Double.MAX_VALUE
        var minLat = Double.MAX_VALUE
        var maxLng = Double.MIN_VALUE
        var maxLat = Double.MIN_VALUE
        for (p in GeoUtil.getCoordinatesFromPolygon(region!!.getServiceArea())) {
            val lat = p!!.lat!!.toDouble()
            if (lat < minLat) minLat = lat
            if (lat > maxLat) maxLat = lat
            val lng = p.lng!!.toDouble()
            if (lng < minLng) minLng = lng
            if (lng > maxLng) maxLng = lng
        }
        return if (coord!!.lat!!.toDouble() < minLat || coord.lat!!.toDouble() > maxLat || coord.lng!!.toDouble() < minLng || coord.lng!!.toDouble() > maxLng) false else true
    }

    private fun populateTransportOperatorInfo(operator: TransportOperator) {
        try {
            getSystemInformation(operator)
            getAssetInformation(operator)
            getRegionInformation(operator)
            cache.add(operator)
        } catch (e: Exception) {
            log.error(e.message)
        }
        log.info("Fetch info of {}", operator.name)
    }

    @Throws(ApiException::class)
    private fun getRegionInformation(operator: TransportOperator) {
        val regions = clientUtil.get(operator, "/operator/regions", Array<SystemRegion>::class.java)
        val list: List<SystemRegion> = ArrayList()
        Collections.addAll(list, *regions)
        operator.regions = list
    }

    @Throws(ApiException::class)
    private fun getAssetInformation(to: TransportOperator) {
        val assets = clientUtil.get(to, "/operator/available-assets", Array<AssetType>::class.java)
        for (assetType in assets) {
            to.assetClasses.add(assetType.assetClass)
        }
    }

    @Throws(ApiException::class)
    private fun getSystemInformation(to: TransportOperator) {
        val info = clientUtil.get(to, "/operator/information", SystemInformation::class.java)
        to.name = info!!.name
        to.id = info.systemId
    }

    companion object {
        private val log = LoggerFactory.getLogger(TOProvider::class.java)
    }
}
