package org.tomp.api.model.gbfs

import com.fasterxml.jackson.annotation.JsonProperty

class GbfsLanguageFeed {
    @JsonProperty("feeds")
    var feeds: Array<GbfsLink>
}
