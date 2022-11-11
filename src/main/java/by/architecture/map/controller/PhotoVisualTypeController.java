package by.architecture.map.controller;

import by.architecture.map.dto.PhotoVisualTypeDto;
import by.architecture.map.service.PhotoVisualTypeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/visual-type")
@CrossOrigin(origins = "http://localhost:7200")
public class PhotoVisualTypeController {

    @Autowired
    private PhotoVisualTypeService photoVisualTypeService;

    @Operation(summary = "Adding a new visual type")
    @PutMapping("/")
    public void add(@RequestBody PhotoVisualTypeDto photoVisualTypeDto) {

        photoVisualTypeService.add(photoVisualTypeDto);
    }

}
