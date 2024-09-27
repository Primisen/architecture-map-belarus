package by.architecturemap.belarus.service

import by.architecturemap.belarus.data.AuthenticationRequest
import by.architecturemap.belarus.data.AuthenticationResponse

interface AuthenticationService {
    fun authentication(authenticationRequest: AuthenticationRequest): AuthenticationResponse
}
