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
        architecturalStyleRepository.findById(id)
            .orElseThrow { NotFoundException("Architectural style not found with id: $id") }
            .apply {
                name = updatedArchitecturalStyle.name
                attributes = updatedArchitecturalStyle.attributes
                description = updatedArchitecturalStyle.description
                shortDescription = updatedArchitecturalStyle.shortDescription
                demonstrativeImage = updatedArchitecturalStyle.demonstrativeImage
                yearsActive = updatedArchitecturalStyle.yearsActive
            }
            .let {
                architecturalStyleRepository.save(it)
            }

    override fun patchUpdate(id: Int, updatedArchitecturalStyle: ArchitecturalStyle): ArchitecturalStyle =

        architecturalStyleRepository.findById(id)
            .orElseThrow { NotFoundException("Architectural style not found with id: $id") }
            .apply {
                if (!updatedArchitecturalStyle.name.isNullOrEmpty())
                    name = updatedArchitecturalStyle.name
                if (!updatedArchitecturalStyle.attributes.isNullOrEmpty())
                    attributes = updatedArchitecturalStyle.attributes
                if (!updatedArchitecturalStyle.description.isNullOrEmpty())
                    description = updatedArchitecturalStyle.description
                if (!updatedArchitecturalStyle.shortDescription.isNullOrEmpty())
                    shortDescription = updatedArchitecturalStyle.shortDescription
                if (updatedArchitecturalStyle.demonstrativeImage != null)
                    demonstrativeImage =
                        updatedArchitecturalStyle.demonstrativeImage?.id?.let { imageService.find(it) }
                if (!updatedArchitecturalStyle.yearsActive.isNullOrEmpty())
                    yearsActive = updatedArchitecturalStyle.yearsActive
            }
            .let {
                architecturalStyleRepository.save(it)
            }

    override fun delete(id: Int) {
        if (architecturalStyleRepository.existsById(id)) {
            architecturalStyleRepository.deleteById(id)
        } else {
            throw NotFoundException("Architectural style not found with id: $id")
        }
    }
}
