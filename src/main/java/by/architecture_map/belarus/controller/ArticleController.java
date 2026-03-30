package by.architecture_map.belarus.controller;

import by.architecture_map.belarus.entity.Article;
import by.architecture_map.belarus.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/articles")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:7200", "*"})
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/")
    public ResponseEntity<Article> create(@RequestBody Article article) {
        return new ResponseEntity<>(articleService.create(article), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public List<Article> findAll() {
        return articleService.findAll();
    }

    @GetMapping("/{id}")
    public Article find(@PathVariable int id) {
        return articleService.find(id);
    }

//    @Operation(summary = "Finding articles in Elasticsearch")
//    @GetMapping
//    public List<Article> articleSearch(String request) { return articleService.find(request); }

    @PutMapping("/{id}")
    public ResponseEntity<Article> update(@PathVariable int id, @RequestBody Article updatedArticle) {
        return new ResponseEntity<>(articleService.update(id, updatedArticle), HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Article> patchUpdate(@PathVariable int id, @RequestBody Article article) {
        return new ResponseEntity<>(articleService.patchUpdate(id, article), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        articleService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
