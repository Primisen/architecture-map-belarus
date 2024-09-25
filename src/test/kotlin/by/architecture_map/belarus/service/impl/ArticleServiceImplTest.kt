package by.architecture_map.belarus.service.impl

import by.architecture_map.belarus.entity.Article
import by.architecture_map.belarus.entity.Image
import by.architecture_map.belarus.exception.NotFoundException
import by.architecture_map.belarus.repository.jpa.ArticleRepository
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import java.util.*

class ArticleServiceImplTest {

    private val articleRepository: ArticleRepository = mockk()
    private val elasticsearchOperations: ElasticsearchOperations = mockk()
    private val articleService = ArticleServiceImpl(articleRepository, elasticsearchOperations)

    @Test
    fun whenCreateArticle_thenSaveArticle() {
        //given
        val article = Article(
            title = "Title",
            content = "Really beautiful",
            shortDescription = "Short description",
            demonstrativeImage = Image("http://..")
        )
            .apply { id = 1 }

        every { articleRepository.save(article) } returns article

        //when
        val result = articleService.create(article)

        //then
        verify(exactly = 1) { articleRepository.save(article) }
        assertEquals(article, result)
    }

    @Test
    fun whenFindAllArticles_thenReturnListOfArticles() {
        //given
        val articles = listOf(
            Article(
                title = "Title",
                content = "Really beautiful",
                shortDescription = "Short description",
                demonstrativeImage = Image("http://..")
            )
                .apply { id = 1 },
            Article(
                title = "Title",
                content = "Beautiful",
                shortDescription = "Short description",
                demonstrativeImage = Image("http://..")
            )
                .apply { id = 2 }
        )
        every { articleRepository.findAll() } returns articles

        //when
        val result = articleService.findAll()

        //then
        verify(exactly = 1) { articleRepository.findAll() }
        assertEquals(articles, result)
    }

    @Test
    fun whenFind_thenReturnArticle() {
        //given
        var id = 1
        val article = Article(
            title = "Title",
            content = "Really beautiful",
            shortDescription = "Short description",
            demonstrativeImage = Image("http://..")
        )
            .apply { id = id }
        every { articleRepository.findById(id) } returns Optional.of(article)

        //when
        val result = articleService.find(id)

        //then
        verify(exactly = 1) { articleRepository.findById(id) }
        assertEquals(article, result)
    }

    @Test
    fun whenUpdate_thenUpdateArticle() {
        //given
        var id = 1
        val existingArticle = Article(
            title = "Title",
            content = "Really beautiful",
            shortDescription = "Short description",
            demonstrativeImage = Image("http://..")
        )
            .apply { id = id }
        val updatedArticle = Article(
            title = "Title",
            content = "Beautiful",
            shortDescription = "Short description",
            demonstrativeImage = Image("http://..")
        )
            .apply { id = id }
        every { articleRepository.findById(id) } returns Optional.of(existingArticle)
        every { articleRepository.save(existingArticle) } returns existingArticle.apply {
            title = updatedArticle.title
            content = updatedArticle.content
            shortDescription = updatedArticle.shortDescription
            demonstrativeImage = updatedArticle.demonstrativeImage
            tag = updatedArticle.tag
        }

        //when
        val result = articleService.update(id, updatedArticle)

        //then
        verify(exactly = 1) { articleRepository.findById(id) }
        verify(exactly = 1) { articleRepository.save(existingArticle) }
        assertEquals(existingArticle, result)
    }

    @Test
    fun whenDelete_thenDeleteArticle() {
        //given
        val id = 1
        val article = mockk<Article>()
        every { articleRepository.findById(id) } returns Optional.of(article)
        every { articleRepository.deleteById(id) } just Runs

        //when
        val result = articleService.delete(id)

        //then
        verify(exactly = 1) { articleRepository.findById(id) }
        verify(exactly = 1) { articleRepository.deleteById(id) }
    }

    @Test
    fun whenDeleteArticleAndArticleDoesNotExists_thenThrowNotFoundException() {
        //given
        val id = 1
        every { articleRepository.findById(id) } returns Optional.empty()

        //when & then
        assertThrows(NotFoundException::class.java) {
            articleService.delete(id)
        }

        //verify
        verify(exactly = 1) { articleRepository.findById(id) }
        verify(exactly = 0) { articleRepository.deleteById(id) }
    }
}