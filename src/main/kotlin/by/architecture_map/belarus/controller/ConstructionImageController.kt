package by.architecture_map.belarus.controller

import by.architecture_map.belarus.entity.ConstructionImage
import by.architecture_map.belarus.service.ConstructionImageService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
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
    fun create(@RequestBody constructionImage: ConstructionImage): ResponseEntity<ConstructionImage> =
        ResponseEntity(constructionImageService.create(constructionImage), HttpStatus.CREATED)

    @Operation(summary = "Getting list of random images of construction for home page")
    @GetMapping("/")
    fun getRandomConstructionImages(
        @Parameter(
            name = "usedId",
            description = "The construction image ids, which have already been obtained from the database by one user and are keeping in order not to select them again"
        )
        @RequestParam(required = false) usedId: String?
    ): List<ConstructionImage> = constructionImageService.getRandomAndUniqueImages(usedId)

    @Operation(summary = "Get constructions images by construction architectural style id")
    @GetMapping("/architectural-styles/{architecturalStyleId}")
    fun find(@PathVariable architecturalStyleId: Int): List<ConstructionImage> =
        constructionImageService.find(architecturalStyleId)

    @Operation(summary = "Get images with the same architectural style by constructionId across images of current construction")
    @GetMapping("/similar/{constructionId}")
    fun getImagesOfConstructionsWithSameArchitecturalStyle(@PathVariable constructionId: Int): List<ConstructionImage> =
        constructionImageService.getImagesOfConstructionsWithSameArchitecturalStyle(constructionId)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<String> =
        constructionImageService.delete(id).let { ResponseEntity(HttpStatus.NO_CONTENT) }

    @Operation(summary = "Update image visibility (show value) by image id")
    @PutMapping("/{id}/visibility")
    fun updateVisibility(
        @PathVariable id: Int,
        @RequestParam visibility: Boolean
    ): ResponseEntity<String> =
        constructionImageService.updateVisibility(id, visibility)
            .let { ResponseEntity(HttpStatus.OK) }
}
