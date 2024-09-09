package by.architecture_map.belarus.controller

import by.architecture_map.belarus.entity.Construction
import by.architecture_map.belarus.service.ConstructionService
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
@RequestMapping("/constructions")
@CrossOrigin(origins = ["http://localhost:4200", "http://localhost:7200", "*"])
class ConstructionController(
    private val constructionService: ConstructionService
) {

    @PostMapping("/")
    fun create(@RequestBody construction: Construction): ResponseEntity<Construction> {
        val createdConstruction = constructionService.create(construction)
        return ResponseEntity(createdConstruction, HttpStatus.CREATED)
    }

    @GetMapping("/")
    fun findAll(): List<Construction> {
        return constructionService.findAll()
    }

    @GetMapping("/{id}")
    fun find(@PathVariable id: Int): Construction {
        return constructionService.find(id)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody constructionUpdates: Construction): ResponseEntity<String> {
        constructionService.update(id, constructionUpdates)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @PatchMapping("/{id}")
    fun patchUpdate(@PathVariable id: Int, @RequestBody construction: Construction): ResponseEntity<String> {
        constructionService.patchUpdate(id, construction)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<String> {
        constructionService.delete(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
