package by.architecture_map.belarus.service

import by.architecture_map.belarus.entity.Architect

interface ArchitectService {

    fun create(architect: Architect): Architect
    fun find(id: Int): Architect
    fun findAll(): List<Architect>
}
