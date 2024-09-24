package by.architecture_map.belarus.dto

data class ConstructionDTO(
    var architecturalStyleId: String? = null,
    var region: String? = null,
    var district: String? = null,
    var buildingCenturyFrom: String? = null,
    var buildingCenturyTo: String? = null
)
