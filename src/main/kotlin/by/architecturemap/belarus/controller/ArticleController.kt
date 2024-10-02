package by.architecturemap.belarus.controller

import by.architecturemap.belarus.entity.Article
import by.architecturemap.belarus.service.ArticleService
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/articles")
@CrossOrigin(origins = ["http://localhost:4200", "http://localhost:7200", "*"])
class ArticleController(
    private val articleService: ArticleService
) {

    @PostMapping("/")
    fun create(@Valid @RequestBody article: Article): ResponseEntity<Article> =
        ResponseEntity(articleService.create(article), HttpStatus.CREATED)

    @GetMapping("/")
    fun findAll(): List<Article> = articleService.findAll()

    @GetMapping("/{id}")
    fun find(@PathVariable id: Int): Article = articleService.find(id)

    @Operation(summary = "Finding articles in Elasticsearch")
    @GetMapping
    fun articleSearch(request: String): List<Article> = articleService.find(request)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody updatedArticle: Article): ResponseEntity<Article> =
        ResponseEntity(articleService.update(id, updatedArticle), HttpStatus.NO_CONTENT)

    @PatchMapping("/{id}")
    fun patchUpdate(@PathVariable id: Int, @RequestBody article: Article): ResponseEntity<Article> =
        ResponseEntity(articleService.patchUpdate(id, article), HttpStatus.NO_CONTENT)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<String> =
        articleService.delete(id).let { ResponseEntity(HttpStatus.NO_CONTENT) }
}
