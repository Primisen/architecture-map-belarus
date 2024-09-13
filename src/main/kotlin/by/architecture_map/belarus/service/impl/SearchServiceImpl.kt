package by.architecture_map.belarus.service.impl

import by.architecture_map.belarus.entity.Construction
import by.architecture_map.belarus.service.SearchService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.data.elasticsearch.core.SearchHits
import org.springframework.data.elasticsearch.core.query.Criteria
import org.springframework.data.elasticsearch.core.query.CriteriaQuery
import org.springframework.stereotype.Service

@Service
class SearchServiceImpl(
    @Autowired private val elasticsearchOperations: ElasticsearchOperations
) : SearchService {

    override fun constructionSearch(
        architecturalStyleId: String?,
        region: String?,
        district: String?,
        buildingTimeFrom: String?,
        buildingTimeTo: String?
    ): List<Construction> {
        var criteria = Criteria()

        if (architecturalStyleId != null) {
            criteria = criteria.and("architecturalStyle.id").`is`(architecturalStyleId)
        }

        if (region != null) {
            criteria = criteria.and("address.region").`is`(region)
        }

        if (district != null) {
            criteria = criteria.and("address.district").`is`(district)
        }

        val query = CriteriaQuery(criteria)

        val searchHits: SearchHits<Construction> = elasticsearchOperations.search(query, Construction::class.java)

        return searchHits.map { it.content }.toList()
    }
}