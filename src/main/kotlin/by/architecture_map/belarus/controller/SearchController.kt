package by.architecture_map.belarus.controller

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

    @GetMapping("/construction")
    fun constructionSearch(
        @RequestParam(required = false) architecturalStyleId: String?,
        @RequestParam(required = false) region: String?,
        @RequestParam(required = false) district: String?,
        @RequestParam(required = false) buildingTimeFrom: String?,
        @RequestParam(required = false) buildingTimeTo: String?
    ): List<Construction> {
        return searchService.constructionSearch(architecturalStyleId, region, district, buildingTimeFrom, buildingTimeTo)
    }
}