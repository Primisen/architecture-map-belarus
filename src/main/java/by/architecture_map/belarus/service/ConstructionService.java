package by.architecture_map.belarus.service;

import by.architecture_map.belarus.dto.ConstructionDTO;
import by.architecture_map.belarus.entity.Construction;

import java.util.List;

public interface ConstructionService {

    Construction create(Construction construction);

    List<Construction> findAll();

    Construction find(int id);

    /**
     * Using for finding Constructions in Elasticsearch
     */
    List<Construction> find(ConstructionDTO constructionDTO);

    Construction update(int id, Construction updatedConstruction);

    Construction patchUpdate(int id, Construction updatedConstruction);

    void delete(int id);

}
