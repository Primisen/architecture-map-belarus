package by.architecture.map.controller;

import by.architecture.map.entity.Construction;
import by.architecture.map.entity.Photo;
import by.architecture.map.service.PhotoService;
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
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/photos")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @GetMapping("/")
    public List<Photo> findAllByConstruction(@RequestBody Construction construction) {
        return photoService.findAllByConstruction(construction);
    }

    @PutMapping("/{idOfOldPhoto}")
    public void update(@PathVariable UUID idOfOldPhoto, @RequestBody Photo updatedPhoto) {
        photoService.update(idOfOldPhoto, updatedPhoto);
    }

    @PostMapping("/")
    public void add(@RequestBody Photo photo) {
        photoService.add(photo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        photoService.delete(id);
    }
}
