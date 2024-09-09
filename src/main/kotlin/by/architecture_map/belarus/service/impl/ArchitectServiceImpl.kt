package by.architecture_map.belarus.service.impl

import by.architecture_map.belarus.entity.Architect
import by.architecture_map.belarus.exception.NotFoundException
import by.architecture_map.belarus.repository.ArchitectRepository
import by.architecture_map.belarus.service.ArchitectService
import org.springframework.stereotype.Service

@Service
class ArchitectServiceImpl(
    private val architectRepository: ArchitectRepository
) : ArchitectService {

    override fun create(architect: Architect): Architect = architectRepository.save(architect)

    override fun find(id: Int): Architect = architectRepository.findById(id)
        .orElseThrow { throw NotFoundException("Architect not found with id: $id") }


    override fun findAll(): List<Architect> = architectRepository.findAll()
}
