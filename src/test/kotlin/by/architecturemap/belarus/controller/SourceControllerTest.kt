package by.architecturemap.belarus.controller

import by.architecturemap.belarus.dto.SourceDTO
import by.architecturemap.belarus.entity.Source
import by.architecturemap.belarus.exception.NotFoundException
import by.architecturemap.belarus.service.SourceService
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(SourceController::class)
@Import(ControllerTestConfiguration::class)
class SourceControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    lateinit var sourceService: SourceService

    private val sourceId = 1
    private val sourceName = "Planeta Belarus"
    private val sourceUrl = "https://..."

    private val source = Source(
        name = sourceName,
        url = sourceUrl
    )

    private val sourceDto = SourceDTO(
        name = "New name",
        url = "https://new"
    )

    private val baseUrl = "/sources"

    @WithMockUser(username = "testuser", roles = ["USER"])
    @Test
    fun should_ReturnListOfSourcesJsonWithStatus200_When_FindAllSourcesRequest() {
        // arrange
        val sources: List<Source> = listOf(source, source)
        `when`(sourceService.findAll()).thenReturn(sources)

        // act & assert
        mockMvc.perform(get(baseUrl))
            .andExpect(status().isOk)
    }

    @WithMockUser(username = "admin", roles = ["ADMIN"])
    @Test
    fun should_ReturnSourceJsonWithStatus201_When_CreatingSource() {
        // arrange
        val sourceJson = objectMapper.writeValueAsString(source)
        `when`(sourceService.create(source)).thenReturn(source)

        // act & assert
        mockMvc.perform(
            post(baseUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(sourceJson)
                .with(csrf())
        )
            .andExpect(status().isCreated)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.name").value(sourceName))
            .andExpect(jsonPath("$.url").value(sourceUrl))
    }

    @WithMockUser(username = "admin", roles = ["ADMIN"])
    @Test
    fun should_ReturnStatus204_When_DeleteSourceWithValidId() {
        // arrange
        doNothing().`when`(sourceService).delete(sourceId)

        // act & assert
        mockMvc.perform(delete("$baseUrl/$sourceId").with(csrf()))
            .andExpect(status().isNoContent)
    }

    @WithMockUser(username = "admin", roles = ["ADMIN"])
    @Test
    fun should_ReturnStatus404_When_DeleteSourceWithInvalidId() {
        // arrange
        val invalidId = 999
        doThrow(NotFoundException("Not found")).`when`(sourceService).delete(invalidId)

        // act & assert
        mockMvc.perform(delete("$baseUrl/$invalidId").with(csrf()))
            .andExpect(status().isNotFound)
    }

    @WithMockUser(username = "admin", roles = ["ADMIN"])
    @Test
    fun should_ReturnStatus204_When_UpdateSourceWithValidData() {
        // arrange
        val sourceJson = objectMapper.writeValueAsString(source)
        `when`(sourceService.update(sourceId, source)).thenReturn(source)

        // act & assert
        mockMvc.perform(
            put("$baseUrl/$sourceId")
                .contentType(MediaType.APPLICATION_JSON)
                .content(sourceJson)
                .with(csrf())
        )
            .andExpect(status().isNoContent)
    }

    @WithMockUser(username = "admin", roles = ["ADMIN"])
    @Test
    fun should_ReturnStatus204_When_PatchUpdateWithValidSourceDTO() {
        // arrange
        val sourceDtoJson = objectMapper.writeValueAsString(sourceDto)
        `when`(sourceService.patchUpdate(sourceId, sourceDto)).thenReturn(source)

        // act & assert
        mockMvc.perform(
            patch("$baseUrl/$sourceId")
                .contentType(MediaType.APPLICATION_JSON)
                .content(sourceDtoJson)
                .with(csrf())
        )
            .andExpect(status().isNoContent)
    }
}
