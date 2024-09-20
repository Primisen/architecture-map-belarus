package by.architecture_map.belarus.service

import by.architecture_map.belarus.entity.Construction

interface SearchService {

    fun constructionSearch(
        architecturalStyleId: String?,
        region: String?,
        district: String?,
        buildingCenturyFrom: String?,
        buildingCenturyTo: String?
    ): List<Construction>
}
