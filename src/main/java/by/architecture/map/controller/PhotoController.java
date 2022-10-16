package by.architecture.map.controller;

import by.architecture.map.dto.PhotoDto;
import by.architecture.map.dto.RandomPhotoDto;
import by.architecture.map.exception.ConstructionException;
import by.architecture.map.exception.PhotoException;
import by.architecture.map.exception.SourceException;
import by.architecture.map.service.PhotoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:7200"})
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @Operation(summary = "Adding a new photo")
    @PostMapping("/")
    public void add(@RequestBody PhotoDto photo) throws PhotoException {
        photoService.add(photo);
    }

    @Operation(summary = "Getting list of random photos")
    @GetMapping("/")
    public List<RandomPhotoDto> getRandomPhotos() {
        return photoService.getRandomPhotos();
    }

    @Operation(summary = "Change an existing photo")
    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody PhotoDto photoUpdates) throws PhotoException, ConstructionException, SourceException {
        photoService.update(id, photoUpdates);
    }

    @Operation(summary = "Delete photo by id")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) throws PhotoException {
        photoService.delete(id);
    }
}
