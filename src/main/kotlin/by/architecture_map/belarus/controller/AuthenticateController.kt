package by.architecture_map.belarus.controller

import by.architecture_map.belarus.data.AuthenticationRequest
import by.architecture_map.belarus.data.AuthenticationResponse
import by.architecture_map.belarus.service.AuthenticationService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = ["http://localhost:7200", "http://localhost:4200", "*"])
class AuthenticateController(private val authenticationService: AuthenticationService) {

    @PostMapping("/login")
    fun authenticate(@RequestBody authRequest: AuthenticationRequest): AuthenticationResponse =
        authenticationService.authentication(authRequest)
}
