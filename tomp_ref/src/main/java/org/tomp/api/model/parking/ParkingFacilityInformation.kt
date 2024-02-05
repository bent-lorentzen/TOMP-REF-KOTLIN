package org.tomp.api.model.parking

import com.fasterxml.jackson.annotation.JsonProperty

class ParkingFacilityInformation {
    @JsonProperty("description")
    var description: String? = null

    @JsonProperty("identifier")
    var identifier: String? = null

    @JsonProperty("name")
    var name: String? = null

    @JsonProperty("specifications")
    var specifications: Array<Specification>

    @JsonProperty("accessPoints")
    private var accessPoints: Array<AccessPoints>
    fun getAccessPoints(): Array<AccessPoints>? {
        return accessPoints
    }

    fun setAccessPoints(accessPoints: Array<AccessPoints>) {
        this.accessPoints = accessPoints
    }
}
