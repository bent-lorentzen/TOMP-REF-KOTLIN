package io.swagger.api

import javax.annotation.Generated

@Generated(value = ["io.swagger.codegen.v3.generators.java.SpringCodegen"], date = "2020-03-24T15:10:42.894Z[GMT]")
class NotFoundException(private val code: Int, msg: String?) : ApiException(code, msg)
