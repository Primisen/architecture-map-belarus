package by.architecturemap.belarus.controller

import by.architecturemap.belarus.dto.ConstructionDTO
import by.architecturemap.belarus.dto.ConstructionSearchingDTO
import by.architecturemap.belarus.entity.Address
import by.architecturemap.belarus.entity.ArchitecturalStyle
import by.architecturemap.belarus.entity.Construction
import by.architecturemap.belarus.exception.NotFoundException
import by.architecturemap.belarus.service.ConstructionService
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

@WebMvcTest(ConstructionController::class)
@Import(ControllerTestConfiguration::class)
class ConstructionControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    lateinit var constructionService: ConstructionService

    private val constructionId = 1
    private val constructionName = "Касцёл у Сар'і"

    private val construction = Construction(
        name = constructionName,
        architecturalStyle = ArchitecturalStyle(name = "Віленскае барока"),
        address = Address(region = "Віцебская вобласць")
    )

    private val constructionDto = ConstructionDTO(
        name = "New name"
    )

    private val baseUrl = "/constructions"

    @WithMockUser(username = "testuser", roles = ["USER"])
    @Test
    fun should_ReturnConstructionJsonWithStatus200_When_FindConstructionById() {
        // arrange
        `when`(constructionService.find(constructionId)).thenReturn(construction)

        // act & assert
        mockMvc.perform(get("$baseUrl/$constructionId"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.name").value(constructionName))
    }

    @WithMockUser(username = "testuser", roles = ["USER"])
    @Test
    fun should_ReturnListOfConstructionsJsonWithStatus200_When_FindAllConstructions() {
        // arrange
        val constructions = listOf(construction, construction)
        `when`(constructionService.findAll()).thenReturn(constructions)

        // act & assert
        mockMvc.perform(get(baseUrl))
            .andExpect(status().isOk)
    }

    @WithMockUser(username = "admin", roles = ["ADMIN"])
    @Test
    fun should_ReturnConstructionJsonWithStatus201_When_CreatingConstruction() {
        // arrange
        val constructionJson = objectMapper.writeValueAsString(construction)
        `when`(constructionService.create(construction)).thenReturn(construction)

        // act & assert
        mockMvc.perform(
            post(baseUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(constructionJson)
                .with(csrf())
        )
            .andExpect(status().isCreated)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.name").value(constructionName))
    }

    @WithMockUser(username = "admin", roles = ["ADMIN"])
    @Test
    fun should_ReturnStatus204_When_DeletingConstructionWithValidId() {
        // arrange
        doNothing().`when`(constructionService).delete(constructionId)

        // act & assert
        mockMvc.perform(
            delete("$baseUrl/$constructionId").with(csrf())
        ).andExpect(status().isNoContent)
    }

    @WithMockUser(username = "admin", roles = ["ADMIN"])
    @Test
    fun should_ReturnStatus404_When_DeletingConstructionWithInvalidId() {
        // arrange
        val invalidId = 999
        doThrow(NotFoundException("Not found")).`when`(constructionService).delete(invalidId)

        // act & assert
        mockMvc.perform(
            delete("$baseUrl/$invalidId").with(csrf())
        ).andExpect(status().isNotFound)
    }

    @WithMockUser(username = "admin", roles = ["ADMIN"])
    @Test
    fun should_ReturnStatus204_When_UpdatingConstruction() {
        // arrange
        val constructionJson = objectMapper.writeValueAsString(construction)
        `when`(constructionService.update(constructionId, construction)).thenReturn(construction)

        // act & assert
        mockMvc.perform(
            put("$baseUrl/$constructionId")
                .contentType(MediaType.APPLICATION_JSON)
                .content(constructionJson)
                .with(csrf())
        ).andExpect(status().isNoContent)
    }

    @WithMockUser(username = "admin", roles = ["ADMIN"])
    @Test
    fun should_ReturnStatus204_When_PatchUpdatingConstruction() {
        // arrange
        val constructionDtoJson = objectMapper.writeValueAsString(constructionDto)
        `when`(constructionService.patchUpdate(constructionId, constructionDto)).thenReturn(construction)

        // act & assert
        mockMvc.perform(
            patch("$baseUrl/$constructionId")
                .contentType(MediaType.APPLICATION_JSON)
                .content(constructionDtoJson)
                .with(csrf())
        ).andExpect(status().isNoContent)
    }

    @WithMockUser(username = "testuser", roles = ["USER"])
    @Test
    fun should_ReturnStatus200_When_SearchingConstruction() {
        // arrange
        val architecturalStyleId = "1"
        val region = "Віцебская вобласць"
        val district = "Мёрскі раён"
        val buildingCenturyFrom = "17"
        val buildingCenturyTo = "19"

        `when`(
            constructionService.find(
                ConstructionSearchingDTO(
                    architecturalStyleId,
                    region,
                    district,
                    buildingCenturyFrom,
                    buildingCenturyTo
                )
            )
        ).thenReturn(listOf(construction))

        // act & assert
        mockMvc.perform(
            get("$baseUrl/search")
                .param("architecturalStyleId", architecturalStyleId)
                .param("region", region)
                .param("district", district)
                .param("buildingCenturyFrom", buildingCenturyFrom)
                .param("buildingCenturyTo", buildingCenturyTo)
        ).andExpect(status().isOk)
    }
}
