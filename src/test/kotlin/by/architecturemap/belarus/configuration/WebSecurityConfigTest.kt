package by.architecturemap.belarus.configuration

import by.architecturemap.belarus.repository.jpa.UserRepository
import by.architecturemap.belarus.service.impl.CustomUserDetailsService
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ContextConfiguration(classes = [WebSecurityConfig::class])
@WebMvcTest(WebSecurityConfig::class)
@AutoConfigureMockMvc
@Import(WebSecurityConfig::class)
class WebSecurityConfigTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var userRepository: UserRepository

    @MockBean
    private lateinit var jwtAuthenticationFilter: JwtAuthenticationFilter

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var userDetailsService: UserDetailsService

    @Test
    fun should_AllowUnauthenticatedAccessToPublicEndpoints() {
        mockMvc.get("/")
            .andExpect { status().isOk }
    }

    @Test
    fun should_ForbiddenAccessToProtectedEndpointsWithoutAuthentication() {
        mockMvc.get("/users/1")
            .andExpect { status().isForbidden }
    }

    @Test
    @WithMockUser(roles = ["USER"])
    fun should_AllowAuthenticatedUserToAccessProtectedEndpoint() {
        mockMvc.get("/users/1")
            .andExpect { status().isOk }
    }

    @Test
    @WithMockUser(roles = ["ADMIN"])
    fun should_AllowAdminToAccessAdminProtectedEndpoints() {
        mockMvc.post("/architects")
            .andExpect { status().isOk }
    }

    @Test
    @WithMockUser(roles = ["USER"])
    fun should_ForbiddenNonAdminUserAccessToAdminProtectedEndpoints() {
        mockMvc.post("/architects")
            .andExpect { status().isForbidden }
    }

    @Test
    fun should_DenyAccessToAdminProtectedEndpointsWithoutAuthentication() {
        mockMvc.get("/users/1")
            .andExpect { status().isForbidden }
    }

    @Test
    fun userDetailsService_ShouldReturnCustomUserDetailsService() {
        assertTrue(userDetailsService is CustomUserDetailsService)
    }

    @Test
    fun authenticationManager_ShouldBeInjectedAndNotNull() {
        assertNotNull(authenticationManager)
    }

    @Test
    fun jwtAuthenticationFilter_ShouldBeInjectedAndNotNull() {
        assertNotNull(jwtAuthenticationFilter)
    }

    @Test
    fun userRepository_ShouldBeInjectedAndNotNull() {
        assertNotNull(userRepository)
    }
}
