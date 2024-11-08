package by.architecturemap.belarus.service.impl

import by.architecturemap.belarus.dto.ConstructionDTO
import by.architecturemap.belarus.dto.ConstructionSearchingDTO
import by.architecturemap.belarus.entity.Construction
import by.architecturemap.belarus.exception.NotFoundException
import by.architecturemap.belarus.service.ConstructionService
import by.architecturemap.belarus.repository.jpa.ConstructionRepository
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.data.elasticsearch.core.SearchHits
import org.springframework.data.elasticsearch.core.query.Criteria
import org.springframework.data.elasticsearch.core.query.CriteriaQuery
import org.springframework.stereotype.Service

private const val CURRENT_CENTURY = 21

@Service
class ConstructionServiceImpl(
    private val constructionRepository: ConstructionRepository,
    private val elasticsearchOperations: ElasticsearchOperations
) : ConstructionService {

    override fun create(construction: Construction) = constructionRepository.save(construction)
    override fun findAll() = constructionRepository.findAll()

    override fun find(id: Int): Construction = constructionRepository.findById(id)
        .orElseThrow { throw NotFoundException("Construction not found with id: $id") }

    override fun find(constructionSearchingDTO: ConstructionSearchingDTO): List<Construction> =
        constructionSearchingDTO
            .run {
                var criteria = Criteria()

                if (!architecturalStyleId.isNullOrBlank())
                    criteria = criteria.and("architecturalStyle.id").`is`(architecturalStyleId)
                if (!region.isNullOrBlank())
                    criteria = criteria.and("address.region").`is`(region)
                if (!district.isNullOrBlank())
                    criteria = criteria.and("address.district").`is`(district)
                if (!buildingCenturyFrom.isNullOrBlank()) {
                    criteria = criteria.and("buildingCentury")
                        .greaterThanEqual(buildingCenturyFrom)
                }
                if (!buildingCenturyTo.isNullOrBlank()) {
                    criteria = criteria.and("buildingCentury")
                        .lessThanEqual(buildingCenturyTo)
                }

                val query = CriteriaQuery(criteria)

                val searchHits: SearchHits<Construction> =
                    elasticsearchOperations.search(query, Construction::class.java)

                searchHits.searchHits.map { it.content }.toList()
            }

    override fun update(id: Int, updatedConstruction: Construction): Construction =
        applyUpdates(id) {
            name = updatedConstruction.name
            description = updatedConstruction.description
            architecturalStyle = updatedConstruction.architecturalStyle
            address = updatedConstruction.address
            architects = updatedConstruction.architects
            buildingDate = updatedConstruction.buildingDate
            buildingCentury = updatedConstruction.buildingCentury
            images = updatedConstruction.images
        }

    override fun patchUpdate(id: Int, updatedConstruction: ConstructionDTO): Construction =
        applyUpdates(id) {
            if (!updatedConstruction.name.isNullOrBlank())
                name = updatedConstruction.name!!
            if (updatedConstruction.address != null)
                address = updatedConstruction.address!!
            if (updatedConstruction.architects.isNotEmpty())
                architects = updatedConstruction.architects
            if (updatedConstruction.architecturalStyle != null)
                architecturalStyle = updatedConstruction.architecturalStyle!!
            if (updatedConstruction.images.isNotEmpty())
                images = updatedConstruction.images
            if (!updatedConstruction.description.isNullOrBlank())
                description = updatedConstruction.description
            if (!updatedConstruction.buildingDate.isNullOrBlank())
                buildingDate = updatedConstruction.buildingDate
            if (updatedConstruction.buildingCentury != null &&
                updatedConstruction.buildingCentury!! in 1..CURRENT_CENTURY)
                buildingCentury = updatedConstruction.buildingCentury
        }

    override fun delete(id: Int) {
        find(id).also { constructionRepository.deleteById(id) }
    }

    private fun applyUpdates(id: Int, update: Construction.() -> Unit): Construction =
        find(id)
            .apply(update)
            .let { constructionRepository.save(it) }
}
