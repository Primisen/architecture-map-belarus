package by.architecture_map.belarus.service

import by.architecture_map.belarus.data.AuthenticationRequest
import by.architecture_map.belarus.data.AuthenticationResponse

interface AuthenticationService {
    fun authentication(authenticationRequest: AuthenticationRequest): AuthenticationResponse
}
