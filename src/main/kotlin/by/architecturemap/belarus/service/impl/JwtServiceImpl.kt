package by.architecturemap.belarus.service.impl

import by.architecturemap.belarus.properties.JwtProperties
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets
import java.util.Date

@Service
class JwtServiceImpl(jwtProperties: JwtProperties) {

    private val secretKey = Keys.hmacShaKeyFor(
        jwtProperties.key.toByteArray(StandardCharsets.UTF_8)
    )

    fun createJwt(
        userDetails: UserDetails,
        expirationDate: Date,
        additionalClaims: Map<String, Any> = emptyMap()
    ): String =
        Jwts.builder()
            .claims()
            .subject(userDetails.username)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(expirationDate)
            .add(additionalClaims)
            .and()
            .signWith(secretKey)
            .compact()

    fun isValid(token: String, userDetails: UserDetails): Boolean =
        run {
            return userDetails.username == extractEmail(token) && !isExpired(token)
        }

    fun extractEmail(token: String): String? =
        getAllClaims(token)
            .subject

    private fun isExpired(token: String): Boolean =
        getAllClaims(token)
            .expiration
            .before(Date(System.currentTimeMillis()))

    private fun getAllClaims(token: String): Claims =
        Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .payload

}
