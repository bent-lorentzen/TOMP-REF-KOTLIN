package org.tomp.api.model.parking

import com.fasterxml.jackson.annotation.JsonProperty

class ParkingFacility {
    @JsonProperty("name")
    var name: String? = null

    @JsonProperty("uuid")
    var uuid: String? = null

    @JsonProperty("staticDataUrl")
    var staticDataUrl: String? = null

    @JsonProperty("dynamicDataUrl")
    var dynamicDataUrl: String? = null

    @JsonProperty("limitedAccess")
    var isLimitedAccess = false

    @JsonProperty("geoLocation")
    var geoLocation: LonLatLocation? = null
}
