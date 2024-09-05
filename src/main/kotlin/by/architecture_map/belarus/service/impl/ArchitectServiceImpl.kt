package by.architecture_map.belarus.service.impl

import by.architecture_map.belarus.entity.Architect
import by.architecture_map.belarus.repository.ArchitectRepository
import by.architecture_map.belarus.service.ArchitectService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ArchitectServiceImpl(
        private val architectRepository: ArchitectRepository
) : ArchitectService {

    override fun create(architect: Architect): Architect {
        return architectRepository.save(architect)
    }

    override fun findById(id: Int): Architect? {
        return architectRepository.findByIdOrNull(id)
    }

    override fun findAll(): MutableList<Architect> {
        return architectRepository.findAll()
    }
}