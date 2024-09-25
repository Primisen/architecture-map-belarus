package by.architecture_map.belarus.service

import by.architecture_map.belarus.dto.ConstructionDTO
import by.architecture_map.belarus.entity.Construction

interface ConstructionService {

    fun create(construction: Construction): Construction
    fun findAll(): List<Construction>
    fun find(id: Int): Construction

    /**
     * Using for finding Constructions in Elasticsearch
     */
    fun find(constructionDTO: ConstructionDTO): List<Construction>

    fun update(id: Int, updatedConstruction: Construction): Construction
    fun patchUpdate(id: Int, updatedConstruction: Construction): Construction
    fun delete(id: Int)
}
