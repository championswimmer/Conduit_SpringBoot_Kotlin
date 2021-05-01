package tech.arnav.conduit.conduit_springboot_kotlin.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import tech.arnav.conduit.conduit_springboot_kotlin.entities.ErrorEntity
import tech.arnav.conduit.conduit_springboot_kotlin.entities.UserEntity
import tech.arnav.conduit.conduit_springboot_kotlin.services.UserService

@RestController
class UserController {

    interface UserSignupDto {
        val username: String
        val email: String
        val password: String
    }

    @Autowired
    lateinit var userService: UserService

    @GetMapping("/profiles/{username}")
    fun getProfile(@PathVariable("username") username: String): ResponseEntity<UserEntity> {
        return ResponseEntity.ok(userService.findUserByUsername(username))
    }

    @PostMapping("/users")
    fun signupUser(@RequestBody body: UserSignupDto): ResponseEntity<UserEntity> {
        val createdUser = userService.createUser(body.email, body.username, body.password)
        return ResponseEntity(createdUser, HttpStatus.CREATED)
    }

    @ExceptionHandler
    fun handleNotFoundException(e: Exception): ResponseEntity<ErrorEntity> {
        val errorEntity = ErrorEntity.create(e.message ?: "Something wrong has happened")
        val statusCode: HttpStatus = when (e) {
            is UserService.UserNotFoundException -> HttpStatus.NOT_FOUND
            else -> HttpStatus.INTERNAL_SERVER_ERROR
        }
        return ResponseEntity(errorEntity, statusCode)
    }


}