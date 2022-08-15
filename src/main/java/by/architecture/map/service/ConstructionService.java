package by.architecture.map.service;

import by.architecture.map.dto.ConstructionDto;
import by.architecture.map.entity.Construction;

import java.util.List;

public interface ConstructionService {

    List<Construction> findAll();

    void add(ConstructionDto construction);

    void update(Integer idOfOldConstruction, ConstructionDto updatedConstruction);

    void delete(Integer id);

}
