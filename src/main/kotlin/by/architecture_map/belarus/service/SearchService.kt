package by.architecture_map.belarus.service

import by.architecture_map.belarus.entity.Construction

interface SearchService {

    fun constructionSearch(
        architecturalStyleId: String?,
        region: String?,
        district: String?,
        buildingTimeFrom: String?,
        buildingTimeTo: String?
    ): List<Construction>
}
