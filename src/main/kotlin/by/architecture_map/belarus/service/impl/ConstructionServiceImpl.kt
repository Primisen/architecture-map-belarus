package by.architecture_map.belarus.service.impl

import by.architecture_map.belarus.entity.Construction
import by.architecture_map.belarus.exception.NotFoundException
import by.architecture_map.belarus.service.ConstructionService
import by.architecture_map.belarus.repository.jpa.ConstructionRepository
import org.springframework.stereotype.Service

@Service
class ConstructionServiceImpl(
    private val constructionRepository: ConstructionRepository
) : ConstructionService {

    override fun create(construction: Construction) = constructionRepository.save(construction)
    override fun findAll() = constructionRepository.findAll()

    override fun find(id: Int): Construction = constructionRepository.findById(id)
        .orElseThrow { throw NotFoundException("Construction not found with id: $id") }

    override fun update(id: Int, updatedConstruction: Construction): Construction =

        constructionRepository.findById(id)
            .orElseThrow { NotFoundException("Construction not found with id: $id") }
            .apply {
                name = updatedConstruction.name
                description = updatedConstruction.description
                architecturalStyle = updatedConstruction.architecturalStyle
                address = updatedConstruction.address
                architects = updatedConstruction.architects
                buildingDate = updatedConstruction.buildingDate
                buildingCentury = updatedConstruction.buildingCentury
                images = updatedConstruction.images
            }
            .let {
                constructionRepository.save(it)
            }

    override fun patchUpdate(id: Int, updatedConstruction: Construction): Construction =
        constructionRepository.findById(id)
            .orElseThrow { NotFoundException("Connection not found with id: $id") }
            .apply {
                if (!updatedConstruction.name.isNullOrEmpty())
                    name = updatedConstruction.name
                if (updatedConstruction.address != null)
                    address = updatedConstruction.address
                if (!updatedConstruction.architects.isNullOrEmpty())
                    architects = updatedConstruction.architects
                if (updatedConstruction.architecturalStyle != null)
                    architecturalStyle = updatedConstruction.architecturalStyle
                if (!updatedConstruction.images.isNullOrEmpty())
                    images = updatedConstruction.images
                if (!updatedConstruction.description.isNullOrEmpty())
                    description = updatedConstruction.description
                if (!updatedConstruction.buildingDate.isNullOrEmpty())
                    buildingDate = updatedConstruction.buildingDate
                if (updatedConstruction.buildingCentury != null)
                    buildingCentury = updatedConstruction.buildingCentury
            }
            .let {
                constructionRepository.save(it)
            }

    override fun delete(id: Int) {
        find(id).also { constructionRepository.deleteById(id) }
    }

    private fun applyUpdates(id: Int, update: Construction.() -> Unit): Construction =
        find(id)
            .apply(update)
            .let { constructionRepository.save(it) }
}
