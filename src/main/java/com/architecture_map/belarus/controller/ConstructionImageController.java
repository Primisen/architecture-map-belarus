package com.architecture_map.belarus.controller;

import com.architecture_map.belarus.entity.image.ConstructionImage;
import com.architecture_map.belarus.service.ConstructionImageService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/construction-images")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:7200"})
public class ConstructionImageController {

    @Autowired
    private ConstructionImageService constructionImageService;

    @Operation(summary = "Getting list of random images of construction for home page")
    @GetMapping("/")
    public List<ConstructionImage> getRandomConstructionImages(@RequestParam(required = false) String usedId) {
        return constructionImageService.getRandomImage(usedId);
    }

    @Operation(summary = "Save image of construction")
    @PostMapping("/")
    public void save(){}

}
