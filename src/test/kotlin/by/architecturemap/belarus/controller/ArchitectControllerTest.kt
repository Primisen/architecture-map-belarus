package by.architecturemap.belarus.controller

import by.architecturemap.belarus.entity.Architect
import by.architecturemap.belarus.service.ArchitectService
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(ArchitectController::class)
@Import(ControllerTestConfiguration::class)
class ArchitectControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    lateinit var architectService: ArchitectService

    private val architectId = 1
    private val architectName = "Yan"
    private val architectSurname = "Kaspyarovich"
    private val architectYearsOfLife = "1720-я — 1792"

    private val architect = Architect(
        name = architectName,
        surname = architectSurname,
        yearsOfLife = architectYearsOfLife
    )

    private val baseUrl = "/architects"

    @WithMockUser(username = "testuser", roles = ["USER"])
    @Test
    fun should_ReturnArchitectJsonWithStatus200_When_FindArchitectById() {
        // arrange
        `when`(architectService.find(architectId)).thenReturn(architect)

        // act & assert
        mockMvc.perform(get("$baseUrl/$architectId"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.name").value(architectName))
            .andExpect(jsonPath("$.surname").value(architectSurname))
            .andExpect(jsonPath("$.yearsOfLife").value(architectYearsOfLife))
    }

    @WithMockUser(username = "testuser", roles = ["USER"])
    @Test
    fun should_ReturnListOfArchitectsJsonWithStatus200_When_FindAllArchitects() {
        // arrange
        val architects: List<Architect> = listOf(architect, architect)
        `when`(architectService.findAll()).thenReturn(architects)

        // act & assert
        mockMvc.perform(get(baseUrl))
            .andExpect(status().isOk)
    }

    @WithMockUser(username = "admin", roles = ["ADMIN"])
    @Test
    fun should_ReturnArchitectJsonWithStatus201_When_CreateArchitect() {
        // arrange
        val architectJson = objectMapper.writeValueAsString(architect)
        `when`(architectService.create(architect)).thenReturn(architect)

        // act & assert
        mockMvc.perform(
            post(baseUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(architectJson)
                .with(csrf())
        )
            .andExpect(status().isCreated)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.name").value(architectName))
            .andExpect(jsonPath("$.surname").value(architectSurname))
            .andExpect(jsonPath("$.yearsOfLife").value(architectYearsOfLife))
    }
}
