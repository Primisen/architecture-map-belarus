package com.architecture_map.belarus.controller;

import com.architecture_map.belarus.entity.Architect;
import com.architecture_map.belarus.service.ArchitectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/architects")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:7200", "http://localhost:4200"})
public class ArchitectController {

    @Autowired
    private ArchitectService architectService;

    @GetMapping("/{id}")
    public Architect findById(@PathVariable Integer id) {
        return architectService.findById(id);
    }

    @GetMapping("/")
    public List<Architect> findAll(){
        return architectService.findAll();
    }
}
