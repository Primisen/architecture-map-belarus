package by.architecturemap.belarus.service

import by.architecturemap.belarus.dto.ArchitecturalStyleDTO
import by.architecturemap.belarus.entity.ArchitecturalStyle

interface ArchitecturalStyleService {

    fun create(architecturalStyle: ArchitecturalStyle): ArchitecturalStyle
    fun findAll(): List<ArchitecturalStyle>
    fun find(id: Int): ArchitecturalStyle
    fun update(id: Int, updatedArchitecturalStyle: ArchitecturalStyle): ArchitecturalStyle
    fun patchUpdate(id: Int, updatedArchitecturalStyle: ArchitecturalStyleDTO): ArchitecturalStyle
    fun delete(id: Int)
}
