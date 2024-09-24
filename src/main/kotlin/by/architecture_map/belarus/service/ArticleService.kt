package by.architecture_map.belarus.service

import by.architecture_map.belarus.entity.Article

interface ArticleService {

    fun create(article: Article): Article
    fun find(id: Int): Article

    /**
     * Using for finding Articles in Elasticsearch
     */
    fun find(request: String): List<Article>
    fun findAll(): List<Article>
    fun update(id: Int, updatedArticle: Article): Article
    fun patchUpdate(id: Int, updatedArticle: Article): Article
    fun delete(id: Int)
}
