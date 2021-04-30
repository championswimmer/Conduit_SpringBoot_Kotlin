package tech.arnav.conduit.conduit_springboot_kotlin.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import tech.arnav.conduit.conduit_springboot_kotlin.entities.ErrorEntity

@ControllerAdvice
class GlobalErrorHandler {

    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception): ResponseEntity<ErrorEntity> {
        val errorEntity = ErrorEntity.create(ex.message ?: "Something wrong has happened")
        val statusCode: HttpStatus = when (ex) {
            else -> HttpStatus.INTERNAL_SERVER_ERROR
        }
        return ResponseEntity(errorEntity, statusCode)
    }
}