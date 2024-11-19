package by.architecturemap.belarus.controller

import by.architecturemap.belarus.dto.UpdatePasswordDTO
import by.architecturemap.belarus.dto.UserDTO
import by.architecturemap.belarus.entity.User
import by.architecturemap.belarus.exception.NotFoundException
import by.architecturemap.belarus.service.UserService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.Mockito.doNothing
import org.mockito.Mockito.doThrow
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(UserController::class)
@Import(ControllerTestConfiguration::class)
class UserControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    lateinit var userService: UserService

    private val userId = 1
    private val username = "usen@test.com"

    private val user = User(
        username = username,
        password = "12345678".toCharArray()
    )

    private val userDto = UserDTO(
        username = username,
        name = "Pawel"
    )

    private val baseUrl = "/users"

    @WithMockUser(username = "testuser", roles = ["USER"])
    @Test
    fun should_ReturnListOfUserDtoJsonWithStatus200_When_FindAllUsersRequest() {
        // arrange
        `when`(userService.findAll()).thenReturn(listOf(userDto, userDto))

        // act & assert
        mockMvc.perform(get(baseUrl))
            .andExpect(status().isOk)
    }

    @WithMockUser(username = "testuser", roles = ["USER"])
    @Test
    fun should_ReturnStatus200_When_FindUserRequestById() {
        // arrange
        `when`(userService.find(userId)).thenReturn(user)

        // act & assert
        mockMvc.perform(get("$baseUrl/$userId"))
            .andExpect(status().isOk)
    }

    @WithMockUser(username = "testuser", roles = ["USER"])
    @Test
    fun should_ReturnStatus200_When_FindUserRequestByUsername() {
        // arrange
        `when`(userService.find(username)).thenReturn(user)

        // act & assert
        mockMvc.perform(get("$baseUrl/username/$username"))
            .andExpect(status().isOk)
    }

    @WithMockUser(username = "admin", roles = ["ADMIN"])
    @Test
    fun should_ReturnStatus204_When_DeleteUserWithValidId() {
        // arrange
        doNothing().`when`(userService).delete(userId)

        // act & assert
        mockMvc.perform(delete("$baseUrl/$userId").with(csrf()))
            .andExpect(status().isNoContent)
    }

    @WithMockUser(username = "admin", roles = ["ADMIN"])
    @Test
    fun should_ReturnStatus404_When_DeleteUserWithInvalidId() {
        // arrange
        val invalidId = 999
        doThrow(NotFoundException("Not found")).`when`(userService).delete(invalidId)

        // act & assert
        mockMvc.perform(delete("$baseUrl/$invalidId").with(csrf()))
            .andExpect(status().isNotFound)
    }

    @WithMockUser(username = "admin", roles = ["ADMIN"])
    @Test
    fun should_ReturnStatus204_When_UpdateUserWithValidUserDTO() {
        // arrange
        val userJson = objectMapper.writeValueAsString(user)
        `when`(userService.update(userId, userDto)).thenReturn(userDto)

        // act & assert
        mockMvc.perform(
            put("$baseUrl/$userId")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson)
                .with(csrf())
        )
            .andExpect(status().isNoContent)
    }

    @WithMockUser(username = "user", roles = ["USER"])
    @Test
    fun should_ReturnStatus204_When_UpdatePasswordWithValidData() {
        // arrange
        val updatePasswordDTO = UpdatePasswordDTO(
            userId = userId,
            oldPassword = "1245678",
            newPassword = "87654321"
        )
        val updatePasswordDtoJson = objectMapper.writeValueAsString(updatePasswordDTO)
        doNothing().`when`(userService).updatePassword(updatePasswordDTO)

        // act & assert
        mockMvc.perform(
            put("$baseUrl/update-password")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatePasswordDtoJson)
                .with(csrf())
        )
            .andExpect(status().isNoContent)
    }
}
