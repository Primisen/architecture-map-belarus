package by.architecturemap.belarus.dto

data class ConstructionSearchingDTO(
    var architecturalStyleId: String? = null,
    var region: String? = null,
    var district: String? = null,
    var buildingCenturyFrom: String? = null,
    var buildingCenturyTo: String? = null
)
