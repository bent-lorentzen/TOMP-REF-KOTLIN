package org.tomp.api.repository

import io.swagger.model.Asset
import io.swagger.model.AssetClass
import io.swagger.model.AssetProperties
import io.swagger.model.AssetType
import io.swagger.model.Coordinates
import io.swagger.model.Place
import io.swagger.model.StationInformation
import io.swagger.model.SystemCalendar
import io.swagger.model.SystemHours
import io.swagger.model.SystemInformation
import io.swagger.model.SystemRegion
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component
import org.tomp.api.utils.GeoUtil
import java.util.Arrays
import javax.validation.Valid
import javax.validation.constraints.NotNull

@Component
@ConditionalOnProperty(value = ["tomp.providers.operatorinformation"], havingValue = "gbfs", matchIfMissing = false)
class GbfsRepository : RegionContainer, StationContainer {
    private var operatorInformation: Map<String, String>? = null
    private var languages: List<String>? = null
    override val regions: MutableList<SystemRegion?> = ArrayList()
    override val stations: MutableList<StationInformation?> = ArrayList()
    val systemHours: MutableList<SystemHours?> = ArrayList()
    val systemCalendar: MutableList<SystemCalendar?> = ArrayList()
    private var bikesAtStations: ArrayList<HashMap<String?, Any>>? = null
    private var freeBikes: ArrayList<HashMap<String?, Any>>? = null
    fun getOperatorInformation(): SystemInformation {
        val info = SystemInformation()
        info.email = operatorInformation!!["email"]
        info.setLanguage(languages)
        info.name = operatorInformation!!["name"]
        info.operator = operatorInformation!!["operator"]
        info.phoneNumber = operatorInformation!!["phone_number"]
        info.systemId = operatorInformation!!["system_id"]
        return info
    }

    fun setOperatorInformation(operatorInformation: Map<String, String>?) {
        this.operatorInformation = operatorInformation
    }

    fun setLanguages(languages: List<String>?) {
        this.languages = languages
    }

    val assets: List<AssetType>
        get() {
            val assets: MutableList<AssetType> = ArrayList()
            if (bikesAtStations != null) {
                for (e in bikesAtStations!!) {
                    val assetType = AssetType()
                    val stationId = e["station_id"].toString()
                    val assetsItem = Asset()
                    assetType.addAssetsItem(assetsItem)
                    copyStationValues(stationId, assetType)
                    val sharedProperties = AssetProperties()
                    assetType.sharedProperties = sharedProperties
                    sharedProperties.name = "Station $stationId"
                    assetType.assetClass = AssetClass.BICYCLE
                    assetType.nrAvailable = e["num_bikes_available"].toString().toInt()
                    assets.add(assetType)
                }
            }
            if (freeBikes != null) {
                val assetType = AssetType()
                assetType.assetClass = AssetClass.BICYCLE
                for (e in freeBikes!!) {
                    val asset = Asset()
                    val properties = AssetProperties()
                    asset.overriddenProperties = properties
                    properties.name = "Bike " + e["bike_id"]
                    asset.id = e["bike_id"].toString()
                    properties.location = toPlace(e["lat"]!!, e["lon"]!!)
                    assetType.addAssetsItem(asset)
                }
                assets.add(assetType)
            }
            return assets
        }

    private fun copyStationValues(stationId: String, assetType: AssetType) {
        for (station in stations) {
            if (station!!.stationId == stationId) {
                val place = Place()
                place.coordinates = getCoordinates(stationId)
                place.stationId = stationId
                place.name = station.name
                place.physicalAddress = station.physicalAddress
                assetType.getAssets()!![0].overriddenProperties!!.location = place
            }
        }
    }

    private fun getCoordinates(stationId: String): Coordinates? {
        for (station in stations) {
            if (station!!.stationId == stationId) {
                return station.coordinates
            }
        }
        return null
    }

    private fun toPlace(lat: Any, lng: Any): Place {
        val p = Place()
        val coordinates = Coordinates()
        coordinates.lat = lat as Float
        coordinates.lng = lng as Float
        p.coordinates = coordinates
        return p
    }

    fun setBikesAtStations(bikesAtStations: ArrayList<HashMap<String?, Any>>?) {
        this.bikesAtStations = bikesAtStations
    }

    fun setFreeBikes(freeBikes: ArrayList<HashMap<String?, Any>>?) {
        this.freeBikes = freeBikes
    }

    fun getNearestAssets(from: @NotNull @Valid Place?, radius: @Valid Int?): List<AssetType> {
        val results: MutableList<AssetType> = ArrayList()
        for (assetType in assets) {
            if (assetType.nrAvailable == null) {
                for (asset in assetType.getAssets()!!) {
                    if (GeoUtil.isNearby(
                            asset.overriddenProperties!!.location!!.coordinates,
                            from!!.coordinates, radius!!.toDouble()
                        )
                    ) {
                        val clone = clone(assetType)
                        clone.setAssets(Arrays.asList(asset))
                        results.add(clone)
                    }
                }
            } else if (GeoUtil.isNearby(
                    assetType.getAssets()!![0].overriddenProperties!!.location!!.coordinates,
                    from!!.coordinates, radius!!.toDouble()
                )
            ) {
                results.add(assetType)
            }
        }
        return results
    }

    private fun clone(assetType: AssetType): AssetType {
        val typeOfAsset = AssetType()
        val sharedProperties = AssetProperties()
        sharedProperties.name = assetType.sharedProperties!!.name
        typeOfAsset.sharedProperties = sharedProperties
        return typeOfAsset
    }
}
