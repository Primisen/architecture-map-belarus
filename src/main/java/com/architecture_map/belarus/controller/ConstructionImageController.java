package com.architecture_map.belarus.controller;

import com.architecture_map.belarus.dto.ConstructionImageDto;
import com.architecture_map.belarus.entity.image.ConstructionImage;
import com.architecture_map.belarus.service.ConstructionImageService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/construction-images")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:7200", "*"})
public class ConstructionImageController {

    private final ConstructionImageService constructionImageService;

    @Operation(summary = "Save image of construction")
    @PostMapping("/")
    public ResponseEntity<String> create(@RequestBody ConstructionImageDto constructionImage){
        ConstructionImage savedConstructionImage = constructionImageService.create(constructionImage);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", savedConstructionImage.getId().toString());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @Operation(summary = "Getting list of random images of construction for home page")
    @GetMapping("/")
    public Set<ConstructionImage> getRandomConstructionImages(@RequestParam(required = false) String usedId) {
        return constructionImageService.getRandomAndUniqueImages(usedId);
    }

    @Operation(summary = "Get constructions images by architectural style")
    @GetMapping("/architectural-styles/{architecturalStyleId}")
    public Set<ConstructionImage> getByConstructionArchitecturalStyleId(@PathVariable Integer architecturalStyleId){
        return constructionImageService.getByConstructionArchitecturalStyleId(architecturalStyleId);
    }

    @Operation(summary = "Get images with the same architectural style by constructionId across images of current construction")
    @GetMapping("/similar/{constructionId}")
    public Set<ConstructionImage> getImagesWithSameArchitecturalStyleByConstructionIdAcrossImagesOfCurrentConstruction(@PathVariable Integer constructionId){
        return constructionImageService.getImagesWithSameArchitecturalStyleByConstructionIdAcrossImagesOfCurrentConstruction(constructionId);
    }

}
