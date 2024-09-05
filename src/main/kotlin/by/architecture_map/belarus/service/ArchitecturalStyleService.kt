package by.architecture_map.belarus.service

import by.architecture_map.belarus.entity.ArchitecturalStyle

interface ArchitecturalStyleService {

    fun create(architecturalStyle: ArchitecturalStyle): ArchitecturalStyle
    fun findAll(): MutableList<ArchitecturalStyle>
    fun findById(id: Int): ArchitecturalStyle?
    fun updateById(id: Int, updatedArchitecturalStyle: ArchitecturalStyle): ArchitecturalStyle?
    fun patchById(id: Int, updatedArchitecturalStyle: ArchitecturalStyle): ArchitecturalStyle?
    fun deleteById(id: Int): Boolean
}