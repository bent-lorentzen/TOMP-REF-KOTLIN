package org.tomp.api.utils

import com.fasterxml.jackson.databind.ObjectMapper
import io.swagger.model.Address
import io.swagger.model.Coordinates
import io.swagger.model.GeojsonLine
import io.swagger.model.GeojsonPoint
import io.swagger.model.GeojsonPolygon
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.tomp.api.configuration.GeoDecodeConfiguration
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.Reader
import java.net.URL
import java.nio.charset.StandardCharsets

@Component
class GeoCoderUtil {
    @Autowired
    var geoDecodeConfiguration: GeoDecodeConfiguration? = null
    var mapper = ObjectMapper()
    fun getRegionByName(name: String?): GeojsonPolygon? {
        val url = geoDecodeConfiguration.getEncodeUrl() + name
        try {
            URL(url).openStream().use { `is` ->
                val rd = BufferedReader(InputStreamReader(`is`, StandardCharsets.UTF_8))
                val jsonText = readAll(rd)
                val results: Array<HashMap<String, Any>> = mapper.readValue<Array<HashMap<*, *>>>(jsonText, Array<HashMap>::class.java)
                if (results.size > 0) {
                    val bb = results[0]["boundingbox"] as ArrayList<String>?
                    val p = GeojsonPolygon()
                    addPoint(bb, 2, 0, p)
                    addPoint(bb, 3, 0, p)
                    addPoint(bb, 3, 1, p)
                    addPoint(bb, 2, 1, p)
                    addPoint(bb, 2, 0, p)
                    return p
                }
            }
        } catch (e: Exception) {
            log.error(e.message)
        }
        return null
    }

    fun getPhysicalAddressByCoordinate(coordinates: Coordinates?): Address? {
        val address = Address()
        return getPhysicalAddressByCoordinate(coordinates, address)
    }

    fun getPhysicalAddressByCoordinate(coordinates: Coordinates?, address: Address): Address? {
        val decodeUrl = geoDecodeConfiguration.getDecodeUrl()
        if (decodeUrl == null || decodeUrl.isEmpty()) {
            return null
        }
        val url = (decodeUrl + "&" + geoDecodeConfiguration.getLon() + "=" + coordinates!!.lng + "&"
                + geoDecodeConfiguration.getLat() + "=" + coordinates.lat)
        var map: Map<String, Any>? = HashMap()
        try {
            URL(url).openStream().use { `is` ->
                val rd = BufferedReader(InputStreamReader(`is`, StandardCharsets.UTF_8))
                val jsonText = readAll(rd)
                map = mapper.readValue<Map<*, *>>(jsonText, MutableMap::class.java)
            }
        } catch (e: Exception) {
            log.error(e.message)
        }
        val street = JsonUtil.getValue(geoDecodeConfiguration.getStreetAddress(), map)
        address.streetAddress = street
        val area = JsonUtil.getValue(geoDecodeConfiguration.getArea(), map)
        address.areaReference = area
        address.postalCode = JsonUtil.getValue(geoDecodeConfiguration.getPostalCode(), map)
        address.country = JsonUtil.getValue(geoDecodeConfiguration.getCountry(), map)
        return address
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

    private fun addPoint(bb: ArrayList<String>?, indexLng: Int, indexLat: Int, p: GeojsonPolygon) {
        if (p.isEmpty()) {
            p.add(GeojsonLine())
        }
        val c = GeojsonPoint()
        c.add(bb!![indexLng].toFloat())
        c.add(bb[indexLat].toFloat())
        p[0]!!.add(c)
    }

    val isActive: Boolean
        get() = geoDecodeConfiguration!!.isActive
    val isDecodingActive: Boolean
        get() = geoDecodeConfiguration!!.isActive && geoDecodeConfiguration.getDecodeUrl() != null && !geoDecodeConfiguration.getDecodeUrl().isEmpty()

    companion object {
        private val log = LoggerFactory.getLogger(GeoCoderUtil::class.java)
    }
}
