package org.tomp.api.model.parking

import com.fasterxml.jackson.annotation.JsonProperty

class ParkingFacilityDynamicInformation {
    @JsonProperty("description")
    var description: String? = null

    @JsonProperty("identifier")
    var identifier: String? = null

    @JsonProperty("name")
    var name: String? = null

    @JsonProperty("facilityActualStatus")
    var facilityActualStatus: FacilityActualStatus? = null
}
