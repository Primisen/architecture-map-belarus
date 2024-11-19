package by.architecturemap.belarus.repository.jpa

import by.architecturemap.belarus.entity.Article
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ArticleRepository : JpaRepository<Article, Int>
