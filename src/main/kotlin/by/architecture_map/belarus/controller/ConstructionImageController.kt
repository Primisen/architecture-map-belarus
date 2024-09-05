package by.architecture_map.belarus.controller

import by.architecture_map.belarus.entity.ConstructionImage
import by.architecture_map.belarus.exception.NotFoundException
import by.architecture_map.belarus.service.ConstructionImageService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/construction-images")
@CrossOrigin(origins = ["http://localhost:4200", "http://localhost:7200", "*"])
class ConstructionImageController(
        private val constructionImageService: ConstructionImageService
) {

    @Operation(summary = "Save image of construction")
    @PostMapping("/")
    fun create(@RequestBody constructionImage: ConstructionImage): ResponseEntity<String> {
        val savedConstructionImage = constructionImageService.create(constructionImage)

        val headers = HttpHeaders()
        headers.add("Location", savedConstructionImage.id.toString())

        return ResponseEntity(headers, HttpStatus.CREATED)
    }

    @Operation(summary = "Getting list of random images of construction for home page")
    @GetMapping("/")
    fun getRandomConstructionImages(@RequestParam(required = false) usedId: String?): MutableList<ConstructionImage> {
        return constructionImageService.getRandomAndUniqueImages(usedId)
    }

    @Operation(summary = "Get constructions images by architectural style")
    @GetMapping("/architectural-styles/{architecturalStyleId}")
    fun getByConstructionArchitecturalStyleId(@PathVariable architecturalStyleId: Int): MutableList<ConstructionImage> {
        return constructionImageService.getByConstructionArchitecturalStyleId(architecturalStyleId)
    }

    @Operation(summary = "Get images with the same architectural style by constructionId across images of current construction")
    @GetMapping("/similar/{constructionId}")
    fun getImagesWithSameArchitecturalStyleByConstructionIdAcrossImagesOfCurrentConstruction(@PathVariable constructionId: Int): MutableList<ConstructionImage> {
        return constructionImageService.getImagesWithSameArchitecturalStyleByConstructionIdAcrossImagesOfCurrentConstruction(constructionId)
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Int): ResponseEntity<String> {
        if (!constructionImageService.deleteById(id)) {
            throw NotFoundException("Construction image not found with id: $id")
        }
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
