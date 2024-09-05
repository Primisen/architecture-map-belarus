package by.architecture_map.belarus.service.impl

import by.architecture_map.belarus.entity.ArchitecturalStyle
import by.architecture_map.belarus.repository.ArchitecturalStyleRepository
import by.architecture_map.belarus.repository.ImageRepository
import by.architecture_map.belarus.service.ArchitecturalStyleService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ArchitecturalStyleServiceImpl(
        private val architecturalStyleRepository: ArchitecturalStyleRepository,
        private val imageRepository: ImageRepository
) : ArchitecturalStyleService {

    override fun create(architecturalStyle: ArchitecturalStyle): ArchitecturalStyle {
        return architecturalStyleRepository.save(architecturalStyle)
    }

    override fun findAll(): MutableList<ArchitecturalStyle> {
        return architecturalStyleRepository.findAll()
    }

    override fun findById(id: Int): ArchitecturalStyle? {
        return architecturalStyleRepository.findByIdOrNull(id)
    }

    override fun updateById(id: Int, updatedArchitecturalStyle: ArchitecturalStyle): ArchitecturalStyle? {

        var savedArchitectureStyle: ArchitecturalStyle? = null

        architecturalStyleRepository.findById(id).ifPresent { foundArchitecturalStyle ->
            foundArchitecturalStyle.name = updatedArchitecturalStyle.name
            foundArchitecturalStyle.attributes = updatedArchitecturalStyle.attributes
            foundArchitecturalStyle.description = updatedArchitecturalStyle.description
            foundArchitecturalStyle.shortDescription = updatedArchitecturalStyle.shortDescription
            foundArchitecturalStyle.demonstrativeImage = updatedArchitecturalStyle.demonstrativeImage
            foundArchitecturalStyle.yearsActive = updatedArchitecturalStyle.yearsActive
            savedArchitectureStyle = architecturalStyleRepository.save(foundArchitecturalStyle)
        }

        return savedArchitectureStyle
    }

    override fun patchById(id: Int, updatedArchitecturalStyle: ArchitecturalStyle): ArchitecturalStyle? {

        var savedArchitectureStyle: ArchitecturalStyle? = null

        architecturalStyleRepository.findById(id).ifPresent { foundArchitecturalStyle ->
            if (!updatedArchitecturalStyle.name.isNullOrEmpty())
                foundArchitecturalStyle.name = updatedArchitecturalStyle.name
            if (!updatedArchitecturalStyle.attributes.isNullOrEmpty())
                foundArchitecturalStyle.attributes = updatedArchitecturalStyle.attributes
            if (!updatedArchitecturalStyle.description.isNullOrEmpty())
                foundArchitecturalStyle.description = updatedArchitecturalStyle.description
            if (!updatedArchitecturalStyle.shortDescription.isNullOrEmpty())
                foundArchitecturalStyle.shortDescription = updatedArchitecturalStyle.shortDescription
            if (updatedArchitecturalStyle.description != null)
                foundArchitecturalStyle.demonstrativeImage = updatedArchitecturalStyle.demonstrativeImage?.id?.let { imageRepository.getReferenceById(it) }
            if (!updatedArchitecturalStyle.yearsActive.isNullOrEmpty())
                foundArchitecturalStyle.yearsActive = updatedArchitecturalStyle.yearsActive

            savedArchitectureStyle = architecturalStyleRepository.save(foundArchitecturalStyle)
        }

        return savedArchitectureStyle
    }

    override fun deleteById(id: Int): Boolean {
        if (architecturalStyleRepository.existsById(id)) {
            architecturalStyleRepository.deleteById(id)
            return true
        }
        return false
    }
}