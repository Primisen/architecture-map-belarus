package by.architecturemap.belarus.service.impl

import by.architecturemap.belarus.data.AuthenticationRequest
import by.architecturemap.belarus.properties.JwtProperties
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails

class AuthenticationServiceImplTest {

    private val authManager: AuthenticationManager = mockk()
    private val userDetailsService: CustomUserDetailsService = mockk()
    private val tokenService: JwtServiceImpl = mockk()
    private val jwtProperties: JwtProperties = mockk()

    private val authenticationService = AuthenticationServiceImpl(
        authManager,
        userDetailsService,
        tokenService,
        jwtProperties
    )

    @Test
    fun givenValidAuthenticationRequest_whenAuthenticate_thenReturnValidAuthenticationResponse() {
        // arrange
        val username = "testuser"
        val password = "password123"
        val authenticationRequest = AuthenticationRequest(username, password)

        val mockUserDetails: UserDetails = mockk()
        val mockToken = "mocked.jwt.token"

        every { authManager.authenticate(any()) } returns mockk()
        every { userDetailsService.loadUserByUsername(username) } returns mockUserDetails
        every { tokenService.createJwt(userDetails = mockUserDetails, expirationDate = any()) } returns mockToken
        every { jwtProperties.accessTokenExpiration } returns 3600000

        // act
        val actualResponse = authenticationService.authentication(authenticationRequest)

        // assert
        assertEquals(mockToken, actualResponse.accessToken)

        // verify
        verify {
            authManager.authenticate(match {
                it is UsernamePasswordAuthenticationToken &&
                        it.name == username && it.credentials == password
            })
        }
        verify { userDetailsService.loadUserByUsername(username) }
    }
}
