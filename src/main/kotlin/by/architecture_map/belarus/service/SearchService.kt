package by.architecture_map.belarus.service

import by.architecture_map.belarus.entity.Article
import by.architecture_map.belarus.entity.Construction

interface SearchService {

    fun searchConstruction(
        architecturalStyleId: String?,
        region: String?,
        district: String?,
        buildingCenturyFrom: String?,
        buildingCenturyTo: String?
    ): List<Construction>

    fun searchArticle(request: String): List<Article>
}
