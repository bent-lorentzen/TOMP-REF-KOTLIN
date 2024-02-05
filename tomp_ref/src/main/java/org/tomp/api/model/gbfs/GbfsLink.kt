package org.tomp.api.model.gbfs

import com.fasterxml.jackson.annotation.JsonProperty

class GbfsLink {
    @JsonProperty("name")
    var name: String? = null

    @JsonProperty("url")
    var url: String? = null
}
