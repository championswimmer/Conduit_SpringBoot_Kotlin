package tech.arnav.conduit.conduit_springboot_kotlin.security.jwt

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.server.ServletServerHttpResponse
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFilter
import org.springframework.stereotype.Service
import tech.arnav.conduit.conduit_springboot_kotlin.entities.ErrorEntity

@Service
class JWTAuthenticationFilter(
    @Autowired authenticationManager: JWTAuthenticationManager,
    @Autowired authenticationConverter: JWTAuthenticationConverter
) : AuthenticationFilter(authenticationManager, authenticationConverter) {
    @Autowired lateinit var objectMapper: ObjectMapper

    init {
        this.setFailureHandler { request, response, authException ->
            val responseEntity = handleAuthException(authException)
            ServletServerHttpResponse(response).apply {
                setStatusCode(responseEntity.statusCode)
                response.contentType = MediaType.APPLICATION_JSON_VALUE
                body.write(objectMapper.writeValueAsString(responseEntity.body).toByteArray())
            }
        }
    }

    private fun handleAuthException(ex: AuthenticationException): ResponseEntity<ErrorEntity> {
        val error = ErrorEntity.create(ex.message ?: "Authentication error")
        val status = when (ex) {
            else -> HttpStatus.UNAUTHORIZED
        }
        return ResponseEntity(error, status)
    }
}