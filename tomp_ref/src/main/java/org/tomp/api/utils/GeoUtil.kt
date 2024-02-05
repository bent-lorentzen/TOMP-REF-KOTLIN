package org.tomp.api.utils

import io.swagger.model.Coordinates
import io.swagger.model.GeojsonLine
import io.swagger.model.GeojsonPoint
import io.swagger.model.GeojsonPolygon
import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.Envelope
import org.locationtech.jts.geom.Geometry
import org.locationtech.jts.geom.GeometryFactory
import org.locationtech.jts.geom.Polygon
import org.tomp.api.model.parking.LonLatLocation
import java.util.function.Function
import java.util.stream.Collectors
import javax.validation.Valid
import javax.validation.constraints.NotNull
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

object GeoUtil {
    var gf = GeometryFactory()
    @JvmStatic
		fun isNearby(
        coordinates: @NotNull @Valid Coordinates?, coordinates2: @NotNull @Valid Coordinates?,
        meters: @Valid Double
    ): Boolean {
        val distance = distanceInMeters(
            coordinates!!.lat!!, coordinates.lng!!,
            coordinates2!!.lat!!, coordinates2.lng!!
        )
        return distance < meters
    }

    /*
	 * Distance in meters
	 */
    fun distanceInMeters(lat1: Float, lng1: Float, lat2: Float, lng2: Float): Float {
        val earthRadius = 6371000.0 // meters
        val dLat = Math.toRadians((lat2 - lat1).toDouble())
        val dLng = Math.toRadians((lng2 - lng1).toDouble())
        val a =
            sin(dLat / 2) * sin(dLat / 2) + cos(Math.toRadians(lat1.toDouble())) * cos(Math.toRadians(lat2.toDouble())) * sin(dLng / 2) * sin(dLng / 2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        return (earthRadius * c).toFloat()
    }

    @JvmStatic
		fun toCoordinates(lat: Double, lng: Double): Coordinates {
        val c = Coordinates()
        c.lat = lat.toFloat()
        c.lng = lng.toFloat()
        return c
    }

    fun toPolygon(location: LonLatLocation?, radius: Double): GeojsonPolygon {
        val c = Coordinates()
        c.lng = location.getLongitude()
        c.lat = location.getLatitude()
        return toPolygon(c, radius)
    }

    fun addPoint(polygon: GeojsonPolygon, lng: Float?, lat: Float?) {
        if (polygon.isEmpty()) {
            polygon.add(GeojsonLine())
        }
        val e = GeojsonPoint()
        e.add(lng)
        e.add(lat)
        polygon[0]!!.add(e)
    }

    fun addPoint(polygon: GeojsonPolygon?, lng: Double, lat: Double) {
        addPoint(polygon, lng.toFloat().toDouble(), lat.toFloat().toDouble())
    }

    fun toPolygon(location: Coordinates?, radius: Double): GeojsonPolygon {
        val p = GeojsonPolygon()
        val r = radius.toFloat()
        addPoint(p, (location!!.lng!! - r).toDouble(), (location.lat!! - r).toDouble())
        addPoint(p, (location.lng!! + r).toDouble(), (location.lat!! - r).toDouble())
        addPoint(p, (location.lng!! + r).toDouble(), (location.lat!! + r).toDouble())
        addPoint(p, (location.lng!! - r).toDouble(), (location.lat!! + r).toDouble())
        addPoint(p, (location.lng!! - r).toDouble(), (location.lat!! - r).toDouble())
        return p
    }

    fun getBoundingBox(geometry: Geometry?): Envelope {
        val envelope = Envelope()
        val enclosingGeometry = geometry!!.envelope
        val enclosingCoordinates = enclosingGeometry.coordinates
        for (c in enclosingCoordinates) {
            envelope.expandToInclude(c)
        }
        return envelope
    }

    fun toPolygon(serviceArea: GeojsonPolygon?): Polygon {
        val points: MutableList<Coordinate> = ArrayList()
        for (coordinate in serviceArea!![0]!!) {
            points.add(toCoordinate(coordinate))
        }
        return gf.createPolygon(points.toArray(arrayOf()))
    }

    private fun toCoordinate(coordinates: @NotNull @Valid GeojsonPoint?): Coordinate {
        val c = Coordinate()
        c.setX(coordinates!![0]!!.toDouble())
        c.setY(coordinates[1]!!.toDouble())
        return c
    }

    fun getCoordinatesFromPolygon(serviceArea: @Valid GeojsonPolygon?): List<Coordinates> {
        val coordinates: MutableList<Coordinates> = ArrayList()
        for (line in serviceArea!!) {
            val points = line.stream().map<Coordinates>(Function { point: GeojsonPoint ->
                val c = Coordinates()
                c.lng = point[0]
                c.lat = point[1]
                c
            }).collect(Collectors.toList())
            coordinates.addAll(points)
        }
        return coordinates
    }
}
