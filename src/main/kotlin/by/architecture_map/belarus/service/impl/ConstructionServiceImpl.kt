package by.architecture_map.belarus.service.impl

import by.architecture_map.belarus.entity.Construction
import by.architecture_map.belarus.repository.ConstructionRepository
import by.architecture_map.belarus.service.ConstructionService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ConstructionServiceImpl(
        private val constructionRepository: ConstructionRepository
) : ConstructionService {

    override fun create(construction: Construction): Construction {
        return constructionRepository.save(construction)
    }

    override fun findAll(): MutableList<Construction> {
        return constructionRepository.findAll()
    }

    override fun findById(id: Int): Construction? {
        return constructionRepository.findByIdOrNull(id)
    }

    override fun updateById(id: Int, updatedConstruction: Construction): Construction? {

        var savedConstruction: Construction? = null

        constructionRepository.findById(id).ifPresent { foundConstruction ->
            foundConstruction.name = updatedConstruction.name
            foundConstruction.description = updatedConstruction.description
            foundConstruction.architecturalStyle = updatedConstruction.architecturalStyle
            foundConstruction.address = updatedConstruction.address
            foundConstruction.architects = updatedConstruction.architects
            foundConstruction.buildingTime = updatedConstruction.buildingTime
            foundConstruction.images = updatedConstruction.images
            savedConstruction = constructionRepository.save(foundConstruction)
        }

        return savedConstruction
    }

    override fun patchById(id: Int, updatedConstruction: Construction): Construction? {
        var savedConstruction: Construction? = null

        constructionRepository.findById(id).ifPresent { foundConstruction ->
            if (!updatedConstruction.name.isNullOrEmpty())
                foundConstruction.name = updatedConstruction.name
            if (updatedConstruction.address != null)
                foundConstruction.address = updatedConstruction.address
            if (!updatedConstruction.architects.isNullOrEmpty())
                foundConstruction.architects = updatedConstruction.architects
            if (updatedConstruction.architecturalStyle != null)
                foundConstruction.architecturalStyle = updatedConstruction.architecturalStyle
            if (!updatedConstruction.images.isNullOrEmpty())
                foundConstruction.images = updatedConstruction.images
            if (!updatedConstruction.description.isNullOrEmpty())
                foundConstruction.description = updatedConstruction.description
            if (!updatedConstruction.buildingTime.isNullOrEmpty())
                foundConstruction.buildingTime = updatedConstruction.buildingTime

            savedConstruction = constructionRepository.save(foundConstruction)
        }

        return savedConstruction
    }

    override fun deleteById(id: Int): Boolean {
        if (constructionRepository.existsById(id)) {
            constructionRepository.deleteById(id)
            return true
        }

        return false
    }
}