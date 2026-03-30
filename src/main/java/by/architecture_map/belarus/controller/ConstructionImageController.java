package by.architecture_map.belarus.controller;

import by.architecture_map.belarus.entity.ConstructionImage;
import by.architecture_map.belarus.service.ConstructionImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/construction-images")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:7200", "*"})
@RequiredArgsConstructor
public class ConstructionImageController {

    private final ConstructionImageService constructionImageService;

    @Operation(summary = "Save image of construction")
    @PostMapping("/")
    public ResponseEntity<ConstructionImage> create(@RequestBody ConstructionImage constructionImage) {
        return new ResponseEntity<>(constructionImageService.create(constructionImage), HttpStatus.CREATED);
    }

    @Operation(summary = "Getting list of random images of construction for home page")
    @GetMapping("/")
    public List<ConstructionImage> getRandomConstructionImages(
            @Parameter(
                    name = "usedId",
                    description = "The construction image ids, which have already been obtained from the database by one user and are keeping in order not to select them again"
            )
            @RequestParam(required = false) String usedId) {
        return constructionImageService.getRandomAndUniqueImages(usedId);
    }

    @Operation(summary = "Get constructions images by construction architectural style id")
    @GetMapping("/architectural-styles/{architecturalStyleId}")
    public List<ConstructionImage> find(@PathVariable int architecturalStyleId) {
        return constructionImageService.find(architecturalStyleId);
    }

    @Operation(summary = "Get images with the same architectural style by constructionId across images of current construction")
    @GetMapping("/similar/{constructionId}")
    public List<ConstructionImage> getImagesOfConstructionsWithSameArchitecturalStyle(@PathVariable int constructionId) {
        return constructionImageService.getImagesOfConstructionsWithSameArchitecturalStyle(constructionId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        constructionImageService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
