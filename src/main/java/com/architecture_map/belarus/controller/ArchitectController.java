package com.architecture_map.belarus.controller;

import com.architecture_map.belarus.dto.ArchitectDto;
import com.architecture_map.belarus.entity.Architect;
import com.architecture_map.belarus.exception.NotFoundException;
import com.architecture_map.belarus.service.ArchitectService;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/architects")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:7200", "http://localhost:4200"})
public class ArchitectController {

    private final ArchitectService architectService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody ArchitectDto architectDto) {
        Architect savedArchitect = architectService.create(architectDto);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", savedArchitect.getId().toString());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Architect findById(@PathVariable Integer id) {
        return architectService.findById(id).orElseThrow(NotFoundException::new);
    }

    @GetMapping("/")
    public List<Architect> findAll(){
        return architectService.findAll();
    }
}
