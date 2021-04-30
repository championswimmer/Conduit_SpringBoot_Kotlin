package tech.arnav.conduit.conduit_springboot_kotlin.security.jwt

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.security.SignatureException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationConverter
import org.springframework.stereotype.Service
import tech.arnav.conduit.conduit_springboot_kotlin.services.JWTService
import javax.servlet.http.HttpServletRequest

@Service
class JWTAuthenticationConverter(
    @Autowired val jwtService: JWTService
) : AuthenticationConverter {
    class JWTAuthException(msg: String?) : AuthenticationException(msg)

    override fun convert(request: HttpServletRequest): JWTAuthentication? {
        return request.getHeader("Authorization")?.let { header ->
            if (!header.startsWith("Token ")) null
            else try { jwtService.decodeJwt(header.replace("Token ", "")) } catch (ex: Exception) {
                when (ex) {
                    is ExpiredJwtException -> throw JWTAuthException("JWT Expired")
                    is SignatureException -> throw JWTAuthException("JWT Signature did not match")
                    else -> throw JWTAuthException("Could not read JWT")
                }
            }
        }

    }
}