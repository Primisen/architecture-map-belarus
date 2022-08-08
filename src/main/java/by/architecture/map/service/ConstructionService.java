package by.architecture.map.service;

import by.architecture.map.dto.ConstructionDto;
import by.architecture.map.entity.Construction;

import java.util.List;
import java.util.UUID;

public interface ConstructionService {

    List<Construction> findAll();

    void add(ConstructionDto construction);

    void update(UUID idOfOldConstruction, ConstructionDto updatedConstruction);

    void delete(UUID id);

}
