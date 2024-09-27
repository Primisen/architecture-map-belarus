package by.architecturemap.belarus.service.impl

import by.architecturemap.belarus.entity.Architect
import by.architecturemap.belarus.exception.NotFoundException
import by.architecturemap.belarus.repository.jpa.ArchitectRepository
import by.architecturemap.belarus.service.ArchitectService
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
