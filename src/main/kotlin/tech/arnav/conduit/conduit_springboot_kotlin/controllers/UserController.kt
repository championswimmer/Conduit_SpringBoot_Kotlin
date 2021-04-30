package tech.arnav.conduit.conduit_springboot_kotlin.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.HttpClientErrorException
import tech.arnav.conduit.conduit_springboot_kotlin.entities.ErrorEntity
import tech.arnav.conduit.conduit_springboot_kotlin.entities.UserEntity
import tech.arnav.conduit.conduit_springboot_kotlin.services.UserService
import java.lang.Exception
import java.net.http.HttpResponse

@RestController
class UserController {

    @Autowired lateinit var userService: UserService

    @GetMapping("/profiles/{username}")
    fun getProfile(@PathVariable("username") username: String): ResponseEntity<UserEntity> {
        return ResponseEntity.ok(userService.findUserByUsername(username))
    }

    @ExceptionHandler
    fun handleNotFoundException(e: Exception) : ResponseEntity<ErrorEntity> {
        val errorEntity = ErrorEntity.create(e.message ?: "Something wrong has happened")
        val statusCode: HttpStatus = when (e) {
            is UserService.UserNotFoundException -> HttpStatus.NOT_FOUND
            else -> HttpStatus.INTERNAL_SERVER_ERROR
        }
        return ResponseEntity(errorEntity, statusCode)
    }



}