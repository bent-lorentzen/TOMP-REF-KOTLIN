package org.tomp.api.model.parking

import com.fasterxml.jackson.annotation.JsonProperty

class AccessPoints {
    @JsonProperty("accessPointLocation")
    private var accessPointLocation: Array<LonLatLocation>
    fun getAccessPointLocation(): Array<LonLatLocation>? {
        return accessPointLocation
    }

    fun setAccessPointLocation(accessPointLocation: Array<LonLatLocation>) {
        this.accessPointLocation = accessPointLocation
    }
}
