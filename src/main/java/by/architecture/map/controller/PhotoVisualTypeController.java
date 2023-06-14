package by.architecture.map.controller;

import by.architecture.map.dto.PhotoVisualTypeDto;
import by.architecture.map.entity.PhotoVisualType;
import by.architecture.map.service.PhotoVisualTypeService;
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
@RequestMapping("/visual-types")
@CrossOrigin(origins = {"http://localhost:7200", "http://localhost:4200"})
public class PhotoVisualTypeController {

    @Autowired
    private PhotoVisualTypeService photoVisualTypeService;

    /* нужны два разных метода для админки и для приложения: один, для админки,
    возвращает действительно все существующие
    визуальные типы, другой, для приложения, должен вернуть только такие типы,
    у которых есть фотографии. Т.е если фотографий с визуальным типом нет, то приложение не
    должно ничего знать об этом типе.

    Также для приложения желательно возвращать визуальные типы в рандомном порядке
     */
    @GetMapping("/")
    public List<PhotoVisualType> getAll() {
        return photoVisualTypeService.findAll();
    }

    @Operation(summary = "Adding a new visual type")
    @PostMapping("/")
    public void add(@RequestBody PhotoVisualTypeDto photoVisualTypeDto) {
        photoVisualTypeService.add(photoVisualTypeDto);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody PhotoVisualTypeDto photoVisualTypeDto) {
        photoVisualTypeService.update(id, photoVisualTypeDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        photoVisualTypeService.delete(id);
    }


}
