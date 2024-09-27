package by.architecturemap.belarus.service

import by.architecturemap.belarus.entity.Architect

interface ArchitectService {

    fun create(architect: Architect): Architect
    fun find(id: Int): Architect
    fun findAll(): List<Architect>
}
