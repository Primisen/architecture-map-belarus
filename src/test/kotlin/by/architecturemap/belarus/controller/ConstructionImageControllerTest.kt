package by.architecturemap.belarus.controller

import by.architecturemap.belarus.entity.ConstructionImage
import by.architecturemap.belarus.exception.NotFoundException
import by.architecturemap.belarus.service.ConstructionImageService
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(ConstructionImageController::class)
@Import(ControllerTestConfiguration::class)
class ConstructionImageControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    lateinit var constructionImageService: ConstructionImageService

    private val constructionImageId = 1

    private val architecturalStyleId = 1

    private val constructionImage = ConstructionImage()

    private val baseUrl = "/construction-images"

    @WithMockUser(username = "admin", roles = ["ADMIN"])
    @Test
    fun should_ReturnStatus201_When_CreatingImage() {
        // arrange
        val imageJson = objectMapper.writeValueAsString(constructionImage)
        `when`(constructionImageService.create(constructionImage)).thenReturn(constructionImage)

        // act & assert
        mockMvc.perform(
            post(baseUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(imageJson)
                .with(csrf())
        ).andExpect(status().isCreated)
    }

    @WithMockUser(username = "admin", roles = ["USER"])
    @Test
    fun should_ReturnStatus200_When_FindingImageByArchitecturalStyleId() {
        // arrange
        `when`(constructionImageService.find(architecturalStyleId))
            .thenReturn(listOf(constructionImage, constructionImage))

        // act & assert
        mockMvc.perform(get("$baseUrl/architectural-styles/$architecturalStyleId"))
            .andExpect(status().isOk)
    }

    @WithMockUser(username = "admin", roles = ["USER"])
    @Test
    fun should_ReturnStatus200_When_GettingImagesOfConstructionsWithSameArchitecturalStyle() {
        // arrange
        val constructionId = 1
        `when`(constructionImageService.getImagesOfConstructionsWithSameArchitecturalStyle(constructionId))
            .thenReturn(listOf(constructionImage, constructionImage))

        // act & assert
        mockMvc.perform(get("$baseUrl/similar/$constructionId"))
            .andExpect(status().isOk)
    }

    @WithMockUser(username = "admin", roles = ["USER"])
    @Test
    fun should_ReturnStatus200_When_GettingRandomConstructionImages() {
        // arrange
        val usedId = "[1, 2, 3]"
        `when`(constructionImageService.getRandomAndUniqueImages(usedId))
            .thenReturn(listOf(constructionImage))

        // act & assert
        mockMvc.perform(
            get(baseUrl)
                .param("userId", usedId)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk)
    }

    @WithMockUser(username = "admin", roles = ["ADMIN"])
    @Test
    fun should_ReturnStatus204_When_DeletingImageWithValidId() {
        // arrange
        doNothing().`when`(constructionImageService).delete(constructionImageId)

        // act & assert
        mockMvc.perform(
            delete("$baseUrl/$constructionImageId").with(csrf())
        ).andExpect(status().isNoContent)
    }

    @WithMockUser(username = "admin", roles = ["ADMIN"])
    @Test
    fun should_ReturnStatus404_When_DeletingImageWithInvalidId() {
        // arrange
        val invalidId = 999
        doThrow(NotFoundException("Not found")).`when`(constructionImageService).delete(invalidId)

        // act & assert
        mockMvc.perform(
            delete("$baseUrl/$invalidId").with(csrf())
        ).andExpect(status().isNotFound)
    }
}
