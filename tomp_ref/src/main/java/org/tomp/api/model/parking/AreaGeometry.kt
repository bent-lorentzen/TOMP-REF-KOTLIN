package org.tomp.api.model.parking

import com.fasterxml.jackson.annotation.JsonProperty

class AreaGeometry {
    @JsonProperty("type")
    var type: String? = null

    @JsonProperty("coordinates")
    var polygon: Array<Array<Array<Float>>>
}
