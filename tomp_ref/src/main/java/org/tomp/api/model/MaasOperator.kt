package org.tomp.api.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModelProperty
import io.swagger.model.GeojsonPolygon
import java.util.Objects
import javax.annotation.Generated

/**
 * MaasOperator
 */
@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2020-05-06T06:58:30.612Z[GMT]")
open class MaasOperator {
    /**
     * Get id
     *
     * @return id
     */
    @get:ApiModelProperty(value = "")
    @JsonProperty("id")
    var id: String? = null

    /**
     * Get type
     *
     * @return type
     */
    @get:ApiModelProperty(value = "")
    @JsonProperty("type")
    var type: MaasEnvironmentType? = null

    /**
     * Get name
     *
     * @return name
     */
    @get:ApiModelProperty(value = "")
    @JsonProperty("name")
    var name: String? = null

    /**
     * Get url
     *
     * @return url
     */
    @get:ApiModelProperty(value = "")
    @JsonProperty("url")
    var url: String? = null

    /**
     * Get version
     *
     * @return version
     */
    @get:ApiModelProperty(value = "")
    @JsonProperty("version")
    var version: String? = null

    /**
     * can be a thumbprint of a certificate
     *
     * @return validationToken
     */
    @get:ApiModelProperty(value = "can be a thumbprint of a certificate")
    @JsonProperty("validationToken")
    var validationToken: String? = null

    /**
     * Get servicedArea
     *
     * @return servicedArea
     */
    @get:ApiModelProperty(value = "")
    @JsonProperty("servicedArea")
    var servicedArea: GeojsonPolygon? = null
    fun id(id: String?): MaasOperator {
        this.id = id
        return this
    }

    fun type(type: MaasEnvironmentType?): MaasOperator {
        this.type = type
        return this
    }

    fun name(name: String?): MaasOperator {
        this.name = name
        return this
    }

    fun url(url: String?): MaasOperator {
        this.url = url
        return this
    }

    fun version(version: String?): MaasOperator {
        this.version = version
        return this
    }

    fun validationToken(validationToken: String?): MaasOperator {
        this.validationToken = validationToken
        return this
    }

    fun servicedArea(servicedArea: GeojsonPolygon?): MaasOperator {
        this.servicedArea = servicedArea
        return this
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val maasOperator = o as MaasOperator
        return id == maasOperator.id && type == maasOperator.type && name == maasOperator.name && url == maasOperator.url && version == maasOperator.version && validationToken == maasOperator.validationToken && servicedArea == maasOperator.servicedArea
    }

    override fun hashCode(): Int {
        return Objects.hash(id, type, name, url, version, validationToken, servicedArea)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("class MaasOperator {\n")
        sb.append("    id: ").append(toIndentedString(id)).append("\n")
        sb.append("    type: ").append(toIndentedString(type)).append("\n")
        sb.append("    name: ").append(toIndentedString(name)).append("\n")
        sb.append("    url: ").append(toIndentedString(url)).append("\n")
        sb.append("    version: ").append(toIndentedString(version)).append("\n")
        sb.append("    validationToken: ").append(toIndentedString(validationToken)).append("\n")
        sb.append("    servicedArea: ").append(toIndentedString(servicedArea)).append("\n")
        sb.append("}")
        return sb.toString()
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private fun toIndentedString(o: Any?): String {
        return o?.toString()?.replace("\n", "\n    ") ?: "null"
    }
}
