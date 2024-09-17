package by.architecture_map.belarus.service

import by.architecture_map.belarus.entity.ArchitecturalStyle

interface ArchitecturalStyleService {

    fun create(architecturalStyle: ArchitecturalStyle): ArchitecturalStyle
    fun findAll(): List<ArchitecturalStyle>
    fun find(id: Int): ArchitecturalStyle
    fun update(id: Int, updatedArchitecturalStyle: ArchitecturalStyle): ArchitecturalStyle
    fun patchUpdate(id: Int, updatedArchitecturalStyle: ArchitecturalStyle): ArchitecturalStyle
    fun delete(id: Int)
}
