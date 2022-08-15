package by.architecture.map.controller;

import by.architecture.map.dto.PhotoDto;
import by.architecture.map.entity.Photo;
import by.architecture.map.service.PhotoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/photos")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @Operation(summary = "Finding all photos of construction")
    @GetMapping("/construction/{constructionId}")
    public List<Photo> findAllByConstruction(@PathVariable Integer constructionId) {
        return photoService.findAllByConstruction(constructionId);
    }

    @Operation(summary = "Change an existing photo")
    @PutMapping("/{idOfOldPhoto}")
    public void update(@PathVariable Integer idOfOldPhoto, @RequestBody PhotoDto updatedPhoto) {
        photoService.update(idOfOldPhoto, updatedPhoto);
    }

    @Operation(summary = "Adding a new photo")
    @PostMapping("/")
    public void add(@RequestBody PhotoDto photo) {
        photoService.add(photo);
    }

    @Operation(summary = "Delete photo by id")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        photoService.delete(id);
    }
}
