package by.architecture_map.belarus.service.impl

import by.architecture_map.belarus.entity.Article
import by.architecture_map.belarus.exception.NotFoundException
import by.architecture_map.belarus.repository.jpa.ArticleRepository
import by.architecture_map.belarus.service.ArticleService
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.data.elasticsearch.core.SearchHits
import org.springframework.data.elasticsearch.core.query.Criteria
import org.springframework.data.elasticsearch.core.query.CriteriaQuery
import org.springframework.stereotype.Service

@Service
class ArticleServiceImpl(
    private val articleRepository: ArticleRepository,
    private val elasticsearchOperations: ElasticsearchOperations
) : ArticleService {
    override fun create(article: Article): Article = articleRepository.save(article)

    override fun find(id: Int): Article = articleRepository.findById(id)
        .orElseThrow { throw NotFoundException("Construction not found with id: $id") }

    override fun find(request: String): List<Article> =
        request
            .run {
                var criteria = Criteria()
                if (request.isNotEmpty()) {
                    criteria = criteria
                        .or("title").contains(request)
                        .or("content").contains(request)
                        .or("shortDescription").contains(request)
                        .or("tag.name").contains(request)
                }
                val query = CriteriaQuery(criteria)

                val searchHits: SearchHits<Article> = elasticsearchOperations.search(query, Article::class.java)
                searchHits.map { it.content }.toList()
            }

    override fun findAll(): List<Article> = articleRepository.findAll()

    override fun update(id: Int, updatedArticle: Article): Article =
        applyUpdate(id) {
            title = updatedArticle.title
            content = updatedArticle.content
            shortDescription = updatedArticle.shortDescription
            demonstrativeImage = updatedArticle.demonstrativeImage
            tag = updatedArticle.tag
        }

    override fun patchUpdate(id: Int, updatedArticle: Article): Article =
        applyUpdate(id) {
            if (!updatedArticle.title.isNullOrEmpty())
                title = updatedArticle.title
            if (!updatedArticle.content.isNullOrEmpty())
                content = updatedArticle.content
            if (!updatedArticle.shortDescription.isNullOrEmpty())
                shortDescription = updatedArticle.shortDescription
            if (updatedArticle.demonstrativeImage != null)
                demonstrativeImage = updatedArticle.demonstrativeImage
            if (!updatedArticle.tag.isNullOrEmpty())
                tag = updatedArticle.tag
        }

    override fun delete(id: Int) {
        find(id).also { articleRepository.deleteById(id) }
    }

    private fun applyUpdate(id: Int, update: Article.() -> Unit): Article =
        find(id)
            .apply(update)
            .let { articleRepository.save(it) }
}
