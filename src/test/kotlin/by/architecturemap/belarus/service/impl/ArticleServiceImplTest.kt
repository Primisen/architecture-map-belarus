package by.architecturemap.belarus.service.impl

import by.architecturemap.belarus.dto.ArticleDTO
import by.architecturemap.belarus.entity.Article
import by.architecturemap.belarus.entity.Image
import by.architecturemap.belarus.entity.Tag
import by.architecturemap.belarus.exception.NotFoundException
import by.architecturemap.belarus.repository.jpa.ArticleRepository
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.data.elasticsearch.core.SearchHit
import org.springframework.data.elasticsearch.core.SearchHits
import org.springframework.data.elasticsearch.core.query.CriteriaQuery
import java.util.Optional

class ArticleServiceImplTest {

    private val articleRepository: ArticleRepository = mockk()
    private val elasticsearchOperations: ElasticsearchOperations = mockk()
    private val articleService = ArticleServiceImpl(articleRepository, elasticsearchOperations)

    private val id = 1
    private lateinit var article: Article
    private lateinit var updatedArticle: Article

    @BeforeEach
    fun setUp() {
        article = Article(
            title = "Title",
            content = "Really beautiful",
            shortDescription = "Short description",
            demonstrativeImage = Image("http://..")
        )
        updatedArticle = Article(
            title = "Updated Title",
            content = "Updated content",
            shortDescription = "Updated short description",
            demonstrativeImage = Image("http://updated..")
        )
    }

    @Test
    fun givenArticle_whenCreateArticle_thenReturnSavedArticle() {
        // arrange
        val expected = Article(
            title = article.title,
            content = article.content,
            shortDescription = article.shortDescription,
            demonstrativeImage = article.demonstrativeImage
        )
        every { articleRepository.save(article) } returns article

        // act
        val actual = articleService.create(article)

        // assert
        assertEquals(expected, actual)
    }

    @Test
    fun whenFindAllArticles_thenReturnListOfArticles() {
        // arrange
        val expected = listOf(article, article)
        every { articleRepository.findAll() } returns expected

        // act
        val actual = articleService.findAll()

        // assert
        assertEquals(expected, actual)
    }

    @Test
    fun givenArticleId_whenFindArticle_thenReturnFoundArticle() {
        // arrange
        val expected = Article(
            title = article.title,
            content = article.content,
            shortDescription = article.shortDescription,
            demonstrativeImage = article.demonstrativeImage
        )
        every { articleRepository.findById(id) } returns Optional.of(article)

        // act
        val actual = articleService.find(id)

        // assert
        assertEquals(expected, actual)
    }

    @Test
    fun givenArticleId_whenDeleteArticle_thenCheckThatDeleteFunctionWasCall() {
        // arrange
        every { articleRepository.findById(id) } returns Optional.of(article)
        every { articleRepository.deleteById(id) } just Runs

        // act
        articleService.delete(id)

        // assert
        verify(exactly = 1) { articleRepository.findById(id) }
        verify(exactly = 1) { articleRepository.deleteById(id) }
    }

    @Test
    fun givenIdOfNotExistedArticle_whenDeleteArticle_thenThrowNotFoundException() {
        // arrange
        every { articleRepository.findById(id) } returns Optional.empty()

        // act & assert
        assertThrows<NotFoundException> { articleService.delete(id) }
        verify(exactly = 1) { articleRepository.findById(id) }
        verify(exactly = 0) { articleRepository.deleteById(id) }
    }

    @Test
    fun givenUpdatedArticle_whenUpdate_thenReturnUpdatedAndSavedArticle() {
        // arrange
        val expected = Article(
            title = updatedArticle.title,
            content = updatedArticle.content,
            shortDescription = updatedArticle.shortDescription,
            demonstrativeImage = updatedArticle.demonstrativeImage
        )
        every { articleRepository.findById(id) } returns Optional.of(article)
        every { articleRepository.save(article) } returns article

        // act
        val actual = articleService.update(id, updatedArticle)

        // assert
        assertEquals(expected, actual)
    }

    @Test
    fun givenValidArticleDTO_whenPatchUpdate_thenUpdateArticle() {
        // arrange
        val exists = article
        val patchArticle = ArticleDTO(
            title = "New title",
            content = "Beautiful",
            shortDescription = "New short description",
            demonstrativeImage = Image("https://.."),
            tag = listOf(Tag(name = "Сядзібы"))
        )
        val expected = Article(
            title = patchArticle.title!!,
            content = patchArticle.content!!,
            shortDescription = patchArticle.shortDescription!!,
            demonstrativeImage = patchArticle.demonstrativeImage!!,
            tag = patchArticle.tag!!
        )

        every { articleRepository.findById(id) } returns Optional.of(exists)
        every { articleRepository.save(exists) } returns exists

        // act
        val actual = articleService.patchUpdate(id, patchArticle)

        // assert
        verify(exactly = 1) { articleRepository.findById(id) }
        verify(exactly = 1) { articleRepository.save(exists) }
        assertEquals(expected, actual)
    }

    @Test
    fun givenArticleDTOWithBlankFields_whenPatchUpdate_thenUpdateArticle() {
        // arrange
        val exists = article
        val patchArticle = ArticleDTO(
            title = "",
            content = "",
            shortDescription = "",
            tag = emptyList()
        )
        val expected = Article(
            title = exists.title,
            content = exists.content,
            shortDescription = exists.shortDescription,
            demonstrativeImage = exists.demonstrativeImage,
            tag = exists.tag
        )

        every { articleRepository.findById(id) } returns Optional.of(exists)
        every { articleRepository.save(exists) } returns exists

        // act
        val actual = articleService.patchUpdate(id, patchArticle)

        // assert
        verify(exactly = 1) { articleRepository.findById(id) }
        verify(exactly = 1) { articleRepository.save(exists) }
        assertEquals(expected, actual)
    }

    @Test
    fun givenArticleDTOWithNullFields_whenPatchUpdate_thenUpdateArticle() {
        // arrange
        val exists = article
        val patchArticle = ArticleDTO(
            title = null,
            content = null,
            shortDescription = null,
            demonstrativeImage = null,
        )
        val expected = Article(
            title = exists.title,
            content = exists.content,
            shortDescription = exists.shortDescription,
            demonstrativeImage = exists.demonstrativeImage,
            tag = exists.tag
        )

        every { articleRepository.findById(id) } returns Optional.of(exists)
        every { articleRepository.save(exists) } returns exists

        // act
        val actual = articleService.patchUpdate(id, patchArticle)

        // assert
        verify(exactly = 1) { articleRepository.findById(id) }
        verify(exactly = 1) { articleRepository.save(exists) }
        assertEquals(expected, actual)
    }

    @Test
    fun whenFind_thenReturnMatchingArticles() {
        // arrange
        val searchRequest = "Title"
        val mockArticle = article
        val mockSearchHits: SearchHits<Article> = mockk()
        val mockSearchHit: SearchHit<Article> = mockk()
        val expected = listOf(mockArticle)

        every { mockSearchHit.content } returns mockArticle
        every { mockSearchHits.searchHits } returns listOf(mockSearchHit)
        every { elasticsearchOperations.search(any<CriteriaQuery>(), Article::class.java) } returns mockSearchHits

        // act
        val actual = articleService.find(searchRequest)

        // assert
        assertNotNull(actual)
        assertEquals(expected, actual)
    }

    @Test
    fun whenFindWithEmptyRequest_thenReturnEmptyList() {
        // arrange
        val searchRequest = ""
        val mockSearchHits: SearchHits<Article> = mockk()
        val expected = emptyList<Article>()

        every { mockSearchHits.searchHits } returns emptyList()
        every { elasticsearchOperations.search(any<CriteriaQuery>(), Article::class.java) } returns mockSearchHits

        // act
        val actual = articleService.find(searchRequest)

        // assert
        assertNotNull(actual)
        assertEquals(expected, actual)
    }
}
