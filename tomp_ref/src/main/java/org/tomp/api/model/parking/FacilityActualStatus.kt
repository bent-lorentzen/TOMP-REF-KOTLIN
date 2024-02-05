package org.tomp.api.model.parking

import com.fasterxml.jackson.annotation.JsonProperty

class FacilityActualStatus {
    @JsonProperty("lastUpdated")
    var lastUpdated: Long = 0

    @JsonProperty("open")
    var isOpen = false

    @JsonProperty("full")
    var isFull = false

    @JsonProperty("parkingCapacity")
    var parkingCapacity: Long = 0

    @JsonProperty("vacantSpaces")
    var vacantSpaces: Long = 0
}
