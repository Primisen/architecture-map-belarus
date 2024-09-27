package by.architecture_map.belarus.service.impl

import by.architecture_map.belarus.data.AuthenticationRequest
import by.architecture_map.belarus.data.AuthenticationResponse
import by.architecture_map.belarus.properties.JwtProperties
import by.architecture_map.belarus.service.AuthenticationService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthenticationServiceImpl(
    private val authManager: AuthenticationManager,
    private val userDetailsService: CustomUserDetailsService,
    private val tokenService: JwtServiceImpl,
    private val jwtProperties: JwtProperties,
) : AuthenticationService {

    override fun authentication(authenticationRequest: AuthenticationRequest): AuthenticationResponse =
        authManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authenticationRequest.username,
                authenticationRequest.password
            )
        )
            .let {
                AuthenticationResponse(
                    accessToken = createAccessToken(userDetailsService.loadUserByUsername(authenticationRequest.username))
                )
            }

    private fun createAccessToken(user: UserDetails) = tokenService.createJwt(
        userDetails = user,
        expirationDate = getAccessTokenExpiration()
    )

    private fun getAccessTokenExpiration(): Date =
        Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration)
}
