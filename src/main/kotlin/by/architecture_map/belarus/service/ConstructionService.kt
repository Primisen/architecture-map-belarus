package by.architecture_map.belarus.service

import by.architecture_map.belarus.entity.Construction

interface ConstructionService {

    fun create(construction: Construction): Construction
    fun findAll(): MutableList<Construction>
    fun findById(id: Int): Construction?
    fun updateById(id: Int, updatedConstruction: Construction): Construction?
    fun patchById(id: Int, updatedConstruction: Construction): Construction?
    fun deleteById(id: Int): Boolean
}