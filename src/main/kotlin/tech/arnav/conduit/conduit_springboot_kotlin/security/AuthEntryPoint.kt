package tech.arnav.conduit.conduit_springboot_kotlin.security

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.server.ServletServerHttpResponse
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import tech.arnav.conduit.conduit_springboot_kotlin.entities.ErrorEntity
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthEntryPoint(
    @Autowired val objectMapper: ObjectMapper
) : AuthenticationEntryPoint {


    private fun handleAuthException(ex: AuthenticationException): ResponseEntity<ErrorEntity> {
        val error = ErrorEntity.create(ex.message ?: "Authentication error")
        val status = when (ex) {
            else -> HttpStatus.UNAUTHORIZED
        }
        return ResponseEntity(error, status)
    }

    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        val responseEntity = handleAuthException(authException)
        ServletServerHttpResponse(response).apply {
            setStatusCode(responseEntity.statusCode)
            response.contentType = MediaType.APPLICATION_JSON_VALUE
            body.write(objectMapper.writeValueAsString(responseEntity.body).toByteArray())
        }
    }
}