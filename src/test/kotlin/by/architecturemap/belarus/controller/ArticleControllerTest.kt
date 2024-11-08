package by.architecturemap.belarus.controller

import by.architecturemap.belarus.dto.ArticleDTO
import by.architecturemap.belarus.entity.Article
import by.architecturemap.belarus.entity.Image
import by.architecturemap.belarus.exception.NotFoundException
import by.architecturemap.belarus.service.ArticleService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.Mockito
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

@WebMvcTest(ArticleController::class)
@Import(ControllerTestConfiguration::class)
class ArticleControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    lateinit var articleService: ArticleService

    private val baseUrl = "/articles"

    private val articleId = 1
    private val content = "Content"
    private val title = "Title"
    private val shortDescription = "Short description"
    private val demonstrativeImage = Image()

    private val article = Article(
        content = content,
        title = title,
        shortDescription = shortDescription,
        demonstrativeImage = demonstrativeImage
    )

    private val articleDto = ArticleDTO(
        title = "New title"
    )

    @WithMockUser(username = "testuser", roles = ["USER"])
    @Test
    fun should_ReturnListOfArticlesJsonWithCode200_When_FindAllArticles() {
        // arrange
        val articles: List<Article> = listOf(article, article)

        `when`(articleService.findAll()).thenReturn(articles)

        // act & assert
        mockMvc.perform(get(baseUrl))
            .andExpect(status().isOk)
    }

    @WithMockUser(username = "testuser", roles = ["USER"])
    @Test
    fun should_ReturnArticleJsonWithCode200_When_FindArticleById() {
        // arrange
        `when`(articleService.find(articleId)).thenReturn(article)

        // act & assert
        mockMvc.perform(get("$baseUrl/$articleId"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.content").value(content))
            .andExpect(jsonPath("$.title").value(title))
            .andExpect(jsonPath("$.shortDescription").value(shortDescription))
    }

    @WithMockUser(username = "testuser", roles = ["USER"])
    @Test
    fun should_ReturnStatus200_When_SearchArticle() {
        // arrange
        val searchRequest = "Сялянская хата 19 ст"
        `when`(articleService.find(searchRequest)).thenReturn(listOf(article))

        // act & assert
        mockMvc.perform(
            get("$baseUrl/search")
                .param("searchRequest", searchRequest)
        ).andExpect(status().isOk)
    }

    @WithMockUser(username = "admin", roles = ["ADMIN"])
    @Test
    fun should_ReturnArticleJsonWithStatus201_When_CreateArticle() {
        // arrange
        val articleJson = objectMapper.writeValueAsString(article)
        `when`(articleService.create(article)).thenReturn(article)

        // act & assert
        mockMvc.perform(
            post(baseUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(articleJson)
                .with(csrf())
        )
            .andExpect(status().isCreated)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.content").value(content))
            .andExpect(jsonPath("$.title").value(title))
            .andExpect(jsonPath("$.shortDescription").value(shortDescription))
    }

    @WithMockUser(username = "admin", roles = ["ADMIN"])
    @Test
    fun should_ReturnStatus204_When_DeleteValidArticle() {
        // arrange
        Mockito.doNothing().`when`(articleService).delete(articleId)

        // act & assert
        mockMvc.perform(
            delete("$baseUrl/$articleId").with(csrf())
        ).andExpect(status().isNoContent)
    }

    @WithMockUser(username = "admin", roles = ["ADMIN"])
    @Test
    fun should_ReturnStatus404_When_DeleteInvalidArticle() {
        // arrange
        val invalidId = 999
        Mockito.doThrow(NotFoundException("Not found")).`when`(articleService).delete(invalidId)

        // act & assert
        mockMvc.perform(
            delete("$baseUrl/$invalidId").with(csrf())
        ).andExpect(status().isNotFound)
    }

    @WithMockUser(username = "admin", roles = ["ADMIN"])
    @Test
    fun should_ReturnStatus204_When_UpdateArticle() {
        // arrange
        val sourceJson = objectMapper.writeValueAsString(article)
        `when`(articleService.update(articleId, article)).thenReturn(article)

        // act & assert
        mockMvc.perform(
            put("$baseUrl/$articleId")
                .contentType(MediaType.APPLICATION_JSON)
                .content(sourceJson)
                .with(csrf())
        ).andExpect(status().isNoContent)
    }

    @WithMockUser(username = "admin", roles = ["ADMIN"])
    @Test
    fun should_ReturnStatus204_When_PatchUpdateArticle() {
        // arrange
        val sourceDtoJson = objectMapper.writeValueAsString(articleDto)
        `when`(articleService.patchUpdate(articleId, articleDto)).thenReturn(article)

        // act & assert
        mockMvc.perform(
            patch("$baseUrl/$articleId")
                .contentType(MediaType.APPLICATION_JSON)
                .content(sourceDtoJson)
                .with(csrf())
        ).andExpect(status().isNoContent)
    }
}
