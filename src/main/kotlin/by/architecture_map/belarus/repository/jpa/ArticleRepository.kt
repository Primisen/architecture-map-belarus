package by.architecture_map.belarus.repository.jpa

import by.architecture_map.belarus.entity.Article
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ArticleRepository : JpaRepository<Article, Int> {
}