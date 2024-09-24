package by.architecture_map.belarus.service.impl

import by.architecture_map.belarus.dto.ConstructionDTO
import by.architecture_map.belarus.entity.Construction
import by.architecture_map.belarus.exception.NotFoundException
import by.architecture_map.belarus.service.ConstructionService
import by.architecture_map.belarus.repository.jpa.ConstructionRepository
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.data.elasticsearch.core.SearchHits
import org.springframework.data.elasticsearch.core.query.Criteria
import org.springframework.data.elasticsearch.core.query.CriteriaQuery
import org.springframework.stereotype.Service

@Service
class ConstructionServiceImpl(
    private val constructionRepository: ConstructionRepository,
    private val elasticsearchOperations: ElasticsearchOperations
) : ConstructionService {

    override fun create(construction: Construction) = constructionRepository.save(construction)
    override fun findAll() = constructionRepository.findAll()

    override fun find(id: Int): Construction = constructionRepository.findById(id)
        .orElseThrow { throw NotFoundException("Construction not found with id: $id") }

    override fun find(constructionDTO: ConstructionDTO): List<Construction> =
        constructionDTO
            .run {
                var criteria = Criteria()

                if (!architecturalStyleId.isNullOrEmpty())
                    criteria = criteria.and("architecturalStyle.id").`is`(architecturalStyleId)
                if (!region.isNullOrEmpty())
                    criteria = criteria.and("address.region").`is`(region)
                if (!district.isNullOrEmpty())
                    criteria = criteria.and("address.district").`is`(district)
                if (!buildingCenturyFrom.isNullOrEmpty()) {
                    criteria = criteria.and("buildingCentury")
                        .greaterThanEqual(buildingCenturyFrom)
                }
                if (!buildingCenturyTo.isNullOrEmpty()) {
                    criteria = criteria.and("buildingCentury")
                        .lessThanEqual(buildingCenturyTo)
                }

                val query = CriteriaQuery(criteria)

                val searchHits: SearchHits<Construction> =
                    elasticsearchOperations.search(query, Construction::class.java)

                searchHits.map { it.content }.toList()
            }

    override fun update(id: Int, updatedConstruction: Construction): Construction =
        applyUpdates(id) {
            name = updatedConstruction.name
            description = updatedConstruction.description
            architecturalStyle = updatedConstruction.architecturalStyle
            address = updatedConstruction.address
            architects = updatedConstruction.architects
            buildingDate = updatedConstruction.buildingDate
            images = updatedConstruction.images
        }

    override fun patchUpdate(id: Int, updatedConstruction: Construction): Construction =
        applyUpdates(id) {
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
        }

    override fun delete(id: Int) {
        find(id).also { constructionRepository.deleteById(id) }
    }

    private fun applyUpdates(id: Int, update: Construction.() -> Unit): Construction =
        find(id)
            .apply(update)
            .let { constructionRepository.save(it) }
}
