package tech.arnav.conduit.conduit_springboot_kotlin.security.jwt

import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationConverter
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest

@Service
class JWTAuthenticationConverter : AuthenticationConverter {
    class AuthHeaderMissingException : AuthenticationException("Authorization header missing")
    class AuthHeaderInvalidException : AuthenticationException("Authorization header does not contain token")

    override fun convert(request: HttpServletRequest): Authentication {
        return request.getHeader("Authorization")?.let { header ->
            if (!header.startsWith("Token ")) throw AuthHeaderInvalidException()

            val jwt = header.replace("Token ", "")
            JWTAuthentication(jwt)
        } ?: kotlin.run {
            throw AuthHeaderMissingException()
        }

    }
}