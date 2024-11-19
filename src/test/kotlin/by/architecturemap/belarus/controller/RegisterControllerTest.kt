package by.architecturemap.belarus.controller

import by.architecturemap.belarus.dto.RegistrationUserDTO
import by.architecturemap.belarus.dto.UserDTO
import by.architecturemap.belarus.service.RegisterService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.Mockito.doNothing
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(RegisterController::class)
@Import(ControllerTestConfiguration::class)
class RegisterControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    lateinit var registerService: RegisterService

    private val username = "test@gmail.com"
    private val password = "1245678"

    private val registrationUserDto = RegistrationUserDTO(
        username = username,
        password = password
    )

    private val userDTO = UserDTO(
        username = username
    )

    private val baseUrl = "/register"

    @WithMockUser(username = "testuser", roles = ["USER"])
    @Test
    fun should_ReturnStatus201_When_RegisteringWithRegistrationUserDto() {
        // arrange
        val registrationUserDtoJson = objectMapper.writeValueAsString(registrationUserDto)
        `when`(registerService.register(registrationUserDto)).thenReturn(userDTO)

        // act & assert
        mockMvc.perform(
            post(baseUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(registrationUserDtoJson)
                .with(csrf())
        )
            .andExpect(status().isCreated)
    }

    @WithMockUser(username = "testuser", roles = ["USER"])
    @Test
    fun should_ReturnStatus200_When_ConfirmingEmailWithToken() {
        // arrange
        val token = "gf766433ttr7wsefrw6ertq7jOIJLF"
        doNothing().`when`(registerService).confirmEmail(token)

        // act & assert
        mockMvc.perform(
            get("$baseUrl/confirm-email")
                .param("token", token)
        )
            .andExpect(status().isOk)
    }
}
