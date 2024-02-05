package org.tomp.api.configuration

import io.swagger.model.Error
import org.slf4j.LoggerFactory
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.ServletRequestBindingException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import org.tomp.api.exceptions.MissingArgumentException

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
class RestExceptionHandler : ResponseEntityExceptionHandler() {
    init {
        log.info("ExceptionHandler active")
    }

    @ExceptionHandler(IllegalArgumentException::class, MissingArgumentException::class) // ,
    // AuthenticationException.class
    // })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    protected fun handleHttpMessageNotReadable(exception: RuntimeException): Any {
        log.error(exception.message, exception)
        return toError(exception, HttpStatus.BAD_REQUEST)
    }

    private fun toError(exception: Exception, status: HttpStatus): Any {
        val error = Error()
        error.status = status.value()
        error.title = exception.message
        error.type = status.name
        return error
    }

    override fun handleServletRequestBindingException(
        exception: ServletRequestBindingException,
        headers: HttpHeaders, status: HttpStatus, request: WebRequest
    ): ResponseEntity<Any> {
        log.error(exception.message, exception)
        return ResponseEntity(toError(exception, status), headers, status)
    }

    override fun handleHttpMessageNotReadable(
        exception: HttpMessageNotReadableException,
        headers: HttpHeaders, status: HttpStatus, request: WebRequest
    ): ResponseEntity<Any> {
        log.error(exception.message, exception)
        return ResponseEntity(toError(exception, status), headers, status)
    }

    override fun handleHttpRequestMethodNotSupported(
        exception: HttpRequestMethodNotSupportedException, headers: HttpHeaders, status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        log.error(exception.message, exception)
        return ResponseEntity(toError(exception, status), headers, status)
    }

    override fun handleMethodArgumentNotValid(
        exception: MethodArgumentNotValidException,
        headers: HttpHeaders, status: HttpStatus, request: WebRequest
    ): ResponseEntity<Any> {
        log.error(exception.message, exception)
        return ResponseEntity(toError(exception, status), headers, status)
    }

    companion object {
        private val log = LoggerFactory.getLogger(RestExceptionHandler::class.java)
    }
}