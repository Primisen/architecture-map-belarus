package by.architecture_map.belarus.controller

import by.architecture_map.belarus.entity.Construction
import by.architecture_map.belarus.exception.NotFoundException
import by.architecture_map.belarus.service.ConstructionService
import org.springframework.http.HttpHeaders
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
    fun create(@RequestBody construction: Construction): ResponseEntity<String> {
        val savedConstruction = constructionService.create(construction)

        val headers = HttpHeaders()
        headers.add("Location", savedConstruction.id.toString())

        return ResponseEntity(headers, HttpStatus.CREATED)
    }

    @GetMapping("/")
    fun findAll(): List<Construction> {
        return constructionService.findAll()
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): Construction {
        return constructionService.findById(id) ?: throw NotFoundException("Construction not found with id: $id")
    }

    @PutMapping("/{id}")
    fun updateById(@PathVariable id: Int, @RequestBody constructionUpdates: Construction): ResponseEntity<String> {
        if (constructionService.updateById(id, constructionUpdates) == null) {
            throw NotFoundException("Construction not found with id: $id")
        }
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @PatchMapping("/{id}")
    fun patchById(@PathVariable id: Int, @RequestBody construction: Construction): ResponseEntity<String> {
        constructionService.patchById(id, construction)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Int): ResponseEntity<String> {
        if (!constructionService.deleteById(id)) {
            throw NotFoundException("Construction not found with id: $id")
        }
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
