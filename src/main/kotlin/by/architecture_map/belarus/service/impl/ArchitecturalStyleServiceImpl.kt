package by.architecture_map.belarus.service.impl

import by.architecture_map.belarus.entity.ArchitecturalStyle
import by.architecture_map.belarus.exception.NotFoundException
import by.architecture_map.belarus.service.ArchitecturalStyleService
import by.architecture_map.belarus.service.ImageService
import by.architecture_map.belarus.repository.jpa.ArchitecturalStyleRepository
import org.springframework.stereotype.Service

@Service
class ArchitecturalStyleServiceImpl(
    private val architecturalStyleRepository: ArchitecturalStyleRepository,
    private val imageService: ImageService
) : ArchitecturalStyleService {

    override fun create(architecturalStyle: ArchitecturalStyle) = architecturalStyleRepository.save(architecturalStyle)

    override fun findAll() = architecturalStyleRepository.findAll()

    override fun find(id: Int): ArchitecturalStyle = architecturalStyleRepository.findById(id)
        .orElseThrow { throw NotFoundException("Architectural style not found with id: $id") }

    override fun update(id: Int, updatedArchitecturalStyle: ArchitecturalStyle): ArchitecturalStyle =
        applyUpdates(id) {
            name = updatedArchitecturalStyle.name
            attributes = updatedArchitecturalStyle.attributes
            description = updatedArchitecturalStyle.description
            shortDescription = updatedArchitecturalStyle.shortDescription
            demonstrativeImage = updatedArchitecturalStyle.demonstrativeImage
            yearsActive = updatedArchitecturalStyle.yearsActive
        }

    override fun patchUpdate(id: Int, updatedArchitecturalStyle: ArchitecturalStyle): ArchitecturalStyle =

        applyUpdates(id) {
            if (!updatedArchitecturalStyle.name.isNullOrEmpty())
                name = updatedArchitecturalStyle.name
            if (!updatedArchitecturalStyle.attributes.isNullOrEmpty())
                attributes = updatedArchitecturalStyle.attributes
            if (!updatedArchitecturalStyle.description.isNullOrEmpty())
                description = updatedArchitecturalStyle.description
            if (!updatedArchitecturalStyle.shortDescription.isNullOrEmpty())
                shortDescription = updatedArchitecturalStyle.shortDescription
            if (updatedArchitecturalStyle.demonstrativeImage != null)
                demonstrativeImage = updatedArchitecturalStyle.demonstrativeImage?.id?.let { imageService.find(it) }
            if (!updatedArchitecturalStyle.yearsActive.isNullOrEmpty())
                yearsActive = updatedArchitecturalStyle.yearsActive
        }

    override fun delete(id: Int) {
        find(id).also { architecturalStyleRepository.deleteById(id) }
    }

    private fun applyUpdates(id: Int, update: ArchitecturalStyle.() -> Unit): ArchitecturalStyle =
        find(id)
            .apply(update)
            .let { architecturalStyleRepository.save(it) }
}
