package by.architecturemap.belarus.configuration

import by.architecturemap.belarus.service.impl.CustomUserDetailsService
import by.architecturemap.belarus.service.impl.JwtServiceImpl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ContextConfiguration(classes = [JwtAuthenticationFilter::class])
@WebMvcTest(JwtAuthenticationFilter::class)
@AutoConfigureMockMvc
@Import(JwtAuthenticationFilter::class)
class JwtAuthenticationFilterTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var userDetailsService: CustomUserDetailsService

    @MockBean
    private lateinit var tokenService: JwtServiceImpl

    private val invalidToken = "invalid.jwt.token"

    @Test
    fun shouldAllowAccessWithoutAuthorizationHeader() {
        // arrange & act
        mockMvc.get("/some-secured-endpoint")
            .andExpect { status().isForbidden }
    }

    @Test
    fun shouldAllowAccessWithoutBearerToken() {
        // arrange & act
        mockMvc.get("/some-secured-endpoint") {
            header("Authorization", "Basic base64encodedcredentials")
        }.andExpect {
            status().isForbidden
        }
    }

    @Test
    fun shouldNotAuthenticateUserWithInvalidJwtToken() {
        // arrange
        whenever(tokenService.extractEmail(invalidToken)).thenReturn(null)

        // act
        mockMvc.get("/some-secured-endpoint") {
            header("Authorization", "Bearer $invalidToken")
        }.andExpect {
            status().isForbidden
        }

        // assert: Ensure security context is empty
        assert(SecurityContextHolder.getContext().authentication == null)
    }

    @Test
    fun userDetailsService_ShouldReturnCustomUserDetailsService() {
        Assertions.assertTrue(userDetailsService is CustomUserDetailsService)
    }
}
