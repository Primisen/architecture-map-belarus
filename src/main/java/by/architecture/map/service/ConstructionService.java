package by.architecture.map.service;

import by.architecture.map.entity.Construction;

import java.util.List;
import java.util.UUID;

public interface ConstructionService {

    List<Construction> findAll();

    void add(Construction construction);

    void update(UUID idOfOldConstruction, Construction updatedConstruction);

    void delete(UUID id);

}
