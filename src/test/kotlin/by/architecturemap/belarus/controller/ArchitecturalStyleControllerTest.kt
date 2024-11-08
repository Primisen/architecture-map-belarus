package by.architecturemap.belarus.controller

import by.architecturemap.belarus.dto.ArchitecturalStyleDTO
import by.architecturemap.belarus.entity.ArchitecturalStyle
import by.architecturemap.belarus.exception.NotFoundException
import by.architecturemap.belarus.service.ArchitecturalStyleService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.Mockito
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(ArchitecturalStyleController::class)
@Import(ControllerTestConfiguration::class)
class ArchitecturalStyleControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    lateinit var architecturalStyleService: ArchitecturalStyleService

    private val baseUrl = "/architectural-styles"

    private val architecturalStyleId = 1
    private val architecturalStyleName = "Віленскае барока"

    private val architecturalStyle = ArchitecturalStyle(
        name =  architecturalStyleName
    )

    private val architecturalStyleDto = ArchitecturalStyleDTO(
        name = "New name"
    )

    @WithMockUser(username = "testuser", roles = ["USER"])
    @Test
    fun should_ReturnListOfStylesJsonWithCode200_When_FindAll() {
        // arrange
        val architecturalStyles: List<ArchitecturalStyle> = listOf(architecturalStyle, architecturalStyle)
        `when`(architecturalStyleService.findAll()).thenReturn(architecturalStyles)

        // act & assert
        mockMvc.perform(get(baseUrl))
            .andExpect(status().isOk)
    }

    @WithMockUser(username = "testuser", roles = ["USER"])
    @Test
    fun should_ReturnStyleJsonWithCode200_When_FindStyleById() {
        // arrange
        `when`(architecturalStyleService.find(architecturalStyleId)).thenReturn(architecturalStyle)

        // act & assert
        mockMvc.perform(get("$baseUrl/$architecturalStyleId"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.name").value(architecturalStyleName))
    }

    @WithMockUser(username = "admin", roles = ["ADMIN"])
    @Test
    fun should_ReturnStyleJsonWithStatus201_When_CreateStyle() {
        // arrange
        val architecturalStyleJson = objectMapper.writeValueAsString(architecturalStyle)
        `when`(architecturalStyleService.create(architecturalStyle)).thenReturn(architecturalStyle)

        // act & assert
        mockMvc.perform(
            MockMvcRequestBuilders.post(baseUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(architecturalStyleJson)
                .with(csrf())
        )
            .andExpect(status().isCreated)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.name").value(architecturalStyleName))
    }

    @WithMockUser(username = "admin", roles = ["ADMIN"])
    @Test
    fun should_ReturnStatus204_When_DeleteValidStyle() {
        // arrange
        Mockito.doNothing().`when`(architecturalStyleService).delete(architecturalStyleId)

        // act & assert
        mockMvc.perform(delete("$baseUrl/$architecturalStyleId").with(csrf()))
            .andExpect(status().isNoContent)
    }

    @WithMockUser(username = "admin", roles = ["ADMIN"])
    @Test
    fun should_ReturnStatus404_When_DeleteInvalidStyle() {
        // arrange
        val invalidId = 999
        doThrow(NotFoundException("Not found")).`when`(architecturalStyleService).delete(invalidId)

        // act & assert
        mockMvc.perform(delete("$baseUrl/$invalidId").with(csrf()))
            .andExpect(status().isNotFound)
    }

    @WithMockUser(username = "admin", roles = ["ADMIN"])
    @Test
    fun should_ReturnStatus204_When_UpdateStyle() {
        // arrange
        val sourceJson = objectMapper.writeValueAsString(architecturalStyle)
        `when`(architecturalStyleService.update(architecturalStyleId, architecturalStyle))
            .thenReturn(architecturalStyle)

        // act & assert
        mockMvc.perform(
            put("$baseUrl/$architecturalStyleId")
                .contentType(MediaType.APPLICATION_JSON)
                .content(sourceJson)
                .with(csrf())
        )
            .andExpect(status().isNoContent)
    }

    @WithMockUser(username = "admin", roles = ["ADMIN"])
    @Test
    fun should_ReturnStatus204_When_PatchUpdateStyle() {
        // arrange
        val sourceDtoJson = objectMapper.writeValueAsString(architecturalStyleDto)
        `when`(architecturalStyleService.patchUpdate(architecturalStyleId, architecturalStyleDto))
            .thenReturn(architecturalStyle)

        // act & assert
        mockMvc.perform(
            patch("$baseUrl/$architecturalStyleId")
                .contentType(MediaType.APPLICATION_JSON)
                .content(sourceDtoJson)
                .with(csrf())
        )
            .andExpect(status().isNoContent)
    }

}
