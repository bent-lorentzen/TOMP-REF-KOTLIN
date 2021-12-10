package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * An error that the service may send, e.g. in case of invalid input, missing authorization or internal service error. See https://github.com/TOMP-WG/TOMP-API/wiki/Error-handling-in-TOMP for further explanation of error code.
 */
@Schema(description = "An error that the service may send, e.g. in case of invalid input, missing authorization or internal service error. See https://github.com/TOMP-WG/TOMP-API/wiki/Error-handling-in-TOMP for further explanation of error code.")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-12-10T11:36:21.130Z[GMT]")


public class Error   {
  @JsonProperty("errorcode")
  private Integer errorcode = null;

  @JsonProperty("type")
  private String type = null;

  @JsonProperty("title")
  private String title = null;

  @JsonProperty("status")
  private Integer status = null;

  @JsonProperty("detail")
  private String detail = null;

  @JsonProperty("instance")
  private String instance = null;

  public Error errorcode(Integer errorcode) {
    this.errorcode = errorcode;
    return this;
  }

  /**
   * The TOMP specific error code. See https://github.com/TOMP-WG/TOMP-API/wiki/Error-handling-in-TOMP for more details of this error.
   * @return errorcode
   **/
  @Schema(description = "The TOMP specific error code. See https://github.com/TOMP-WG/TOMP-API/wiki/Error-handling-in-TOMP for more details of this error.")
  
    public Integer getErrorcode() {
    return errorcode;
  }

  public void setErrorcode(Integer errorcode) {
    this.errorcode = errorcode;
  }

  public Error type(String type) {
    this.type = type;
    return this;
  }

  /**
   * The category of this type of error.
   * @return type
   **/
  @Schema(description = "The category of this type of error.")
  
    public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Error title(String title) {
    this.title = title;
    return this;
  }

  /**
   * A short, human-readable summary of the problem type.  It SHOULD NOT change from occurrence to occurrence of the problem, except to match Content-Language
   * @return title
   **/
  @Schema(description = "A short, human-readable summary of the problem type.  It SHOULD NOT change from occurrence to occurrence of the problem, except to match Content-Language")
  
    public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Error status(Integer status) {
    this.status = status;
    return this;
  }

  /**
   * The HTTP status code ([RFC7231], Section 6) generated by the origin server for this occurrence of the problem.
   * @return status
   **/
  @Schema(description = "The HTTP status code ([RFC7231], Section 6) generated by the origin server for this occurrence of the problem.")
  
    public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Error detail(String detail) {
    this.detail = detail;
    return this;
  }

  /**
   * A human-readable explanation specific to this occurrence of the problem, could match Content-Language
   * @return detail
   **/
  @Schema(description = "A human-readable explanation specific to this occurrence of the problem, could match Content-Language")
  
    public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

  public Error instance(String instance) {
    this.instance = instance;
    return this;
  }

  /**
   * A URI reference that identifies the specific occurrence of the problem.  It may or may not yield further information if dereferenced.
   * @return instance
   **/
  @Schema(description = "A URI reference that identifies the specific occurrence of the problem.  It may or may not yield further information if dereferenced.")
  
    public String getInstance() {
    return instance;
  }

  public void setInstance(String instance) {
    this.instance = instance;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Error error = (Error) o;
    return Objects.equals(this.errorcode, error.errorcode) &&
        Objects.equals(this.type, error.type) &&
        Objects.equals(this.title, error.title) &&
        Objects.equals(this.status, error.status) &&
        Objects.equals(this.detail, error.detail) &&
        Objects.equals(this.instance, error.instance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(errorcode, type, title, status, detail, instance);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Error {\n");
    
    sb.append("    errorcode: ").append(toIndentedString(errorcode)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    detail: ").append(toIndentedString(detail)).append("\n");
    sb.append("    instance: ").append(toIndentedString(instance)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
