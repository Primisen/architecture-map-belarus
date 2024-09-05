package by.architecture_map.belarus.service

import by.architecture_map.belarus.entity.Architect

interface ArchitectService {

    fun create(architect: Architect): Architect
    fun findById(id: Int): Architect?
    fun findAll(): MutableList<Architect>
}