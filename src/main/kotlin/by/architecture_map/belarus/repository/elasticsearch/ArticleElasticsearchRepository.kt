package by.architecture_map.belarus.repository.elasticsearch

import by.architecture_map.belarus.entity.Article
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import org.springframework.stereotype.Repository

@Repository
interface ArticleElasticsearchRepository : ElasticsearchRepository<Article, Int> {
}