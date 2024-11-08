package by.architecturemap.belarus.service.impl

import by.architecturemap.belarus.properties.JwtProperties
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.springframework.security.core.userdetails.UserDetails
import java.util.Date

class JwtServiceImplTest {

    private val jwtProperties = mockk<JwtProperties> {
        every { key } returns "mysecretkeymysecretkeymysecretkeymysecretkey"
    }
    private lateinit var jwtService: JwtServiceImpl
    private lateinit var secretKey: javax.crypto.SecretKey

    @BeforeEach
    fun setUp() {
        jwtService = JwtServiceImpl(jwtProperties)
        secretKey = Keys.hmacShaKeyFor(jwtProperties.key.toByteArray())
    }

    @Test
    fun createJwt_shouldGenerateValidToken() {
        // arrange
        val userDetails = mockk<UserDetails> {
            every { username } returns "test@example.com"
        }
        val expirationDate = Date(System.currentTimeMillis() + 1000 * 60 * 60)
        val additionalClaims = mapOf("role" to "USER")

        // act
        val actualToken = jwtService.createJwt(userDetails, expirationDate, additionalClaims)

        // assert
        val claims = Jwts.parser().verifyWith(secretKey).build().parseClaimsJws(actualToken).body
        assertEquals("test@example.com", claims.subject)
        assertEquals("USER", claims["role"])
    }

    @Test
    fun isValid_shouldReturnTrueForValidToken() {
        // arrange
        val userDetails = mockk<UserDetails> {
            every { username } returns "test@example.com"
        }
        val expirationDate = Date(System.currentTimeMillis() + 1000 * 60 * 60)
        val token = jwtService.createJwt(userDetails, expirationDate)

        // act
        val isValid = jwtService.isValid(token, userDetails)

        // assert
        assertTrue(isValid)
    }

    @Test
    fun extractEmail_shouldReturnCorrectEmail() {
        // arrange
        val userDetails = mockk<UserDetails> {
            every { username } returns "test@example.com"
        }
        val expirationDate = Date(System.currentTimeMillis() + 1000 * 60 * 60)
        val token = jwtService.createJwt(userDetails, expirationDate)

        // act
        val actualEmail = jwtService.extractEmail(token)

        // assert
        assertEquals("test@example.com", actualEmail)
    }
}
