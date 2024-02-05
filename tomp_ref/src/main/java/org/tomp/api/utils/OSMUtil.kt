package org.tomp.api.utils

import com.fasterxml.jackson.databind.ObjectMapper
import io.swagger.client.ApiException
import io.swagger.model.Coordinates
import io.swagger.model.Place
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.IOException
import java.io.InputStreamReader
import java.io.UnsupportedEncodingException
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

object OSMUtil {
    private val mapper = ObjectMapper()
    @JvmStatic
		@Throws(ApiException::class, IOException::class)
    fun getOverpassObjects(qlCondition: String?, bbox: DoubleArray): List<Place> {
        val url = URL("http://overpass-api.de/api/interpreter")
        val con = url.openConnection() as HttpURLConnection
        con.setRequestMethod("GET")
        val parameters: MutableMap<String, String> = HashMap()
        parameters["data"] = ("[out:json];node(" + bbox[0] + "," + bbox[1] + "," + bbox[2] + "," + bbox[3] + ")["
                + qlCondition + "];out;")
        con.setDoOutput(true)
        val out = DataOutputStream(con.outputStream)
        out.writeBytes(getParamsString(parameters))
        out.flush()
        out.close()
        val status = con.getResponseCode()
        val content = StringBuilder()
        if (status == 200) {
            val `in` = BufferedReader(InputStreamReader(con.inputStream))
            var inputLine: String?
            while (`in`.readLine().also { inputLine = it } != null) {
                content.append(inputLine)
            }
            `in`.close()
        }
        con.disconnect()
        val map: HashMap<String, Any> = mapper.readValue(content.toString(), HashMap::class.java)
        val elements = map["elements"] as List<Any>?
        val places: MutableList<Place> = ArrayList()
        for (elementObject in elements!!) {
            val element = elementObject as HashMap<String, Any>
            val id = element["id"].toString().toLong()
            val lat = element["lat"].toString().toDouble()
            val lon = element["lon"].toString().toDouble()
            val p = Place()
            val coordinates = Coordinates()
            coordinates.lat = lat.toFloat()
            coordinates.lng = lon.toFloat()
            val extraInfo = HashMap<String, Any>()
            extraInfo["osm_id"] = id
            p.setExtraInfo(extraInfo)
            p.coordinates = coordinates
            p.stationId = id.toString()
            places.add(p)
        }
        return places
    }

    @Throws(UnsupportedEncodingException::class)
    private fun getParamsString(params: Map<String, String>): String {
        val result = StringBuilder()
        for ((key, value) in params) {
            result.append(URLEncoder.encode(key, "UTF-8"))
            result.append("=")
            result.append(URLEncoder.encode(value, "UTF-8"))
            result.append("&")
        }
        val resultString = result.toString()
        return if (resultString.length > 0) resultString.substring(0, resultString.length - 1) else resultString
    }
}
