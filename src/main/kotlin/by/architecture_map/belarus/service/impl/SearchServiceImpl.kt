package by.architecture_map.belarus.service.impl

import by.architecture_map.belarus.entity.Article
import by.architecture_map.belarus.entity.Construction
import by.architecture_map.belarus.service.SearchService
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.data.elasticsearch.core.SearchHits
import org.springframework.data.elasticsearch.core.query.Criteria
import org.springframework.data.elasticsearch.core.query.CriteriaQuery
import org.springframework.stereotype.Service

@Service
class SearchServiceImpl(
    private val elasticsearchOperations: ElasticsearchOperations
) : SearchService {

    override fun searchConstruction(
        architecturalStyleId: String?,
        region: String?,
        district: String?,
        buildingCenturyFrom: String?,
        buildingCenturyTo: String?
    ): List<Construction> {
        var criteria = Criteria()

        if (!architecturalStyleId.isNullOrEmpty()) {
            criteria = criteria.and("architecturalStyle.id").`is`(architecturalStyleId)
        }

        if (!region.isNullOrEmpty()) {
            criteria = criteria.and("address.region").`is`(region)
        }

        if (!district.isNullOrEmpty()) {
            criteria = criteria.and("address.district").`is`(district)
        }

        if (!buildingCenturyFrom.isNullOrEmpty()) {
            criteria = criteria.and("buildingCentury")
                .greaterThanEqual(buildingCenturyFrom)
        }

        if (!buildingCenturyTo.isNullOrEmpty()) {
            criteria = criteria.and("buildingCentury")
                .lessThanEqual(buildingCenturyTo)

        val query = CriteriaQuery(criteria)

        val searchHits: SearchHits<Construction> = elasticsearchOperations.search(query, Construction::class.java)

        return searchHits.map { it.content }.toList()
    }

    override fun searchArticle(request: String): List<Article> {
        var criteria = Criteria()

        if (request.isNotEmpty()) {

            criteria = criteria
                .or("title").contains(request)
                .or("content").contains(request)
                .or("shortDescription").contains(request)
                .or("tag.name").contains(request)
        }

        val query = CriteriaQuery(criteria)

        val searchHits: SearchHits<Article> = elasticsearchOperations.search(query, Article::class.java)

        return searchHits.map { it.content }.toList()
    }
}
