package by.architecture_map.belarus.service

import by.architecture_map.belarus.entity.Construction

interface ConstructionService {

    fun create(construction: Construction): Construction
    fun findAll(): List<Construction>
    fun find(id: Int): Construction
    fun update(id: Int, updatedConstruction: Construction): Construction
    fun patchUpdate(id: Int, updatedConstruction: Construction): Construction
    fun delete(id: Int)
}
