package by.architecturemap.belarus.service.impl

import by.architecturemap.belarus.dto.ArchitecturalStyleDTO
import by.architecturemap.belarus.entity.ArchitecturalStyle
import by.architecturemap.belarus.exception.NotFoundException
import by.architecturemap.belarus.service.ArchitecturalStyleService
import by.architecturemap.belarus.service.ImageService
import by.architecturemap.belarus.repository.jpa.ArchitecturalStyleRepository
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

    override fun patchUpdate(id: Int, updatedArchitecturalStyle: ArchitecturalStyleDTO): ArchitecturalStyle =

        applyUpdates(id) {
            if (!updatedArchitecturalStyle.name.isNullOrBlank())
                name = updatedArchitecturalStyle.name!!
            if (updatedArchitecturalStyle.attributes.isNotEmpty())
                attributes = updatedArchitecturalStyle.attributes
            if (!updatedArchitecturalStyle.description.isNullOrBlank())
                description = updatedArchitecturalStyle.description
            if (!updatedArchitecturalStyle.shortDescription.isNullOrBlank())
                shortDescription = updatedArchitecturalStyle.shortDescription
            if (updatedArchitecturalStyle.demonstrativeImage != null)
                demonstrativeImage = updatedArchitecturalStyle.demonstrativeImage?.id?.let { imageService.find(it) }
            if (!updatedArchitecturalStyle.yearsActive.isNullOrBlank())
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
