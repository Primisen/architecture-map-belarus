package by.architecturemap.belarus.controller

import by.architecturemap.belarus.entity.User
import by.architecturemap.belarus.entity.UserImage
import by.architecturemap.belarus.service.UserImageService
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(UserImageController::class)
@Import(ControllerTestConfiguration::class)
class UserImageControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    lateinit var userImageService: UserImageService

    private val userImageId = 1

    private val userImage = UserImage(user = User(username = "user@gmail.com", password = "12345678".toCharArray()))

    private val baseUrl = "/user-images"
    @WithMockUser(username = "user", roles = ["USER"])
    @Test
    fun should_ReturnStatus201_When_AddUserImages() {
        // arrange
        val userImageJson = objectMapper.writeValueAsString(listOf(userImage))
        `when`(userImageService.addImages(listOf(userImage))).thenReturn(listOf(userImage))

        // act & assert
        mockMvc.perform(
            post(baseUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userImageJson)
                .with(csrf())
        )
            .andExpect(status().isCreated)
    }

    @WithMockUser(username = "user", roles = ["USER"])
    @Test
    fun should_ReturnStatus204_When_ApproveUserImage() {
        // arrange
        val sourceJson = objectMapper.writeValueAsString(userImage)
        `when`(userImageService.approveImage(userImageId)).thenReturn(userImage)

        // act & assert
        mockMvc.perform(
            put("$baseUrl/$userImageId")
                .contentType(MediaType.APPLICATION_JSON)
                .content(sourceJson)
                .with(csrf())
        )
            .andExpect(status().isNoContent)
    }
}
