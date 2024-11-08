package by.architecturemap.belarus.controller

import by.architecturemap.belarus.service.impl.CustomUserDetailsService
import by.architecturemap.belarus.service.impl.JwtServiceImpl
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.mock.mockito.MockBean

@TestConfiguration
open class ControllerTestConfiguration {

    @MockBean
    lateinit var customUserDetailsService: CustomUserDetailsService

    @MockBean
    lateinit var jwtServiceImpl: JwtServiceImpl
}
