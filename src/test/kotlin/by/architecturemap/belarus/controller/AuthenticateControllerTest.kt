package by.architecturemap.belarus.controller

import by.architecturemap.belarus.data.AuthenticationRequest
import by.architecturemap.belarus.data.AuthenticationResponse
import by.architecturemap.belarus.service.AuthenticationService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(AuthenticateController::class)
@Import(ControllerTestConfiguration::class)
class AuthenticateControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    lateinit var authenticationService: AuthenticationService

    private val username = "test@gmail.com"
    private val password = "1245678"

    private val authenticationRequest = AuthenticationRequest(
        username = username,
        password = password
    )

    private val baseUrl = "/auth"

    @WithMockUser(username = "testuser", roles = ["USER"])
    @Test
    fun should_ReturnStatus200_When_AuthenticateWithValidAuthRequest() {
        // arrange
        val authRequestJson = objectMapper.writeValueAsString(authenticationRequest)

        `when`(
            authenticationService.authentication(authenticationRequest)
        ).thenReturn(AuthenticationResponse(accessToken = "hjgf76467w9q8trtse8fPOUOpsdfsd487GTTF"))

        // act & assert
        mockMvc.perform(
            post("$baseUrl/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(authRequestJson)
                .with(csrf())
        ).andExpect(status().isOk)
    }
}
