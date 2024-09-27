package by.architecturemap.belarus.repository.elasticsearch

import by.architecturemap.belarus.entity.Article
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import org.springframework.stereotype.Repository

@Repository
interface ArticleElasticsearchRepository : ElasticsearchRepository<Article, Int>
