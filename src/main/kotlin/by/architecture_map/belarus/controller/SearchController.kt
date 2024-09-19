package by.architecture_map.belarus.controller

import by.architecture_map.belarus.entity.Article
import by.architecture_map.belarus.entity.Construction
import by.architecture_map.belarus.service.SearchService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/search")
@CrossOrigin(origins = ["http://localhost:4200", "http://localhost:7200", "*"])
class SearchController(
    private val searchService: SearchService
) {

    @GetMapping("/constructions")
    fun constructionSearch(
        @RequestParam(required = false) architecturalStyleId: String?,
        @RequestParam(required = false) region: String?,
        @RequestParam(required = false) district: String?,
        @RequestParam(required = false) buildingCenturyFrom: String?,
        @RequestParam(required = false) buildingCenturyTo: String?
    ): List<Construction> =
        searchService.searchConstruction(architecturalStyleId, region, district, buildingCenturyFrom, buildingCenturyTo)

    @GetMapping("/articles")
    fun articleSearch(request: String): List<Article> = searchService.searchArticle(request)
}
