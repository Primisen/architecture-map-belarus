package by.architecture_map.belarus.controller;

import by.architecture_map.belarus.dto.ConstructionDTO;
import by.architecture_map.belarus.entity.Construction;
import by.architecture_map.belarus.service.ConstructionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/constructions")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:7200", "*"})
@RequiredArgsConstructor
public class ConstructionController {

    private final ConstructionService constructionService;

    @PostMapping("/")
    public ResponseEntity<Construction> create(@RequestBody Construction construction) {
        return new ResponseEntity<>(constructionService.create(construction), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public List<Construction> findAll() {
        return constructionService.findAll();
    }

    @GetMapping("/{id}")
    public Construction find(@PathVariable int id) {
        return constructionService.find(id);
    }

    @Operation(summary = "Finding constructions in Elasticsearch")
    @GetMapping
    public List<Construction> find(
            @RequestParam(required = false) String architecturalStyleId,
            @RequestParam(required = false) String region,
            @RequestParam(required = false) String district,
            @RequestParam(required = false) String buildingCenturyFrom,
            @RequestParam(required = false) String buildingCenturyTo) {
        ConstructionDTO dto = new ConstructionDTO();
        dto.setArchitecturalStyleId(architecturalStyleId);
        dto.setRegion(region);
        dto.setDistrict(district);
        dto.setBuildingCenturyFrom(buildingCenturyFrom);
        dto.setBuildingCenturyTo(buildingCenturyTo);
        return constructionService.find(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Construction> update(@PathVariable int id, @RequestBody Construction constructionUpdates) {
        return new ResponseEntity<>(constructionService.update(id, constructionUpdates), HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Construction> patchUpdate(@PathVariable int id, @RequestBody Construction construction) {
        return new ResponseEntity<>(constructionService.patchUpdate(id, construction), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        constructionService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
