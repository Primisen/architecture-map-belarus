package by.architecturemap.belarus.dto

import by.architecturemap.belarus.entity.Address
import by.architecturemap.belarus.entity.Architect
import by.architecturemap.belarus.entity.ArchitecturalStyle
import by.architecturemap.belarus.entity.ConstructionImage

data class ConstructionDTO(
    var name: String? = null,
    var description: String? = null,
    var architecturalStyle: ArchitecturalStyle? = null,
    var address: Address? = null,
    var architects: List<Architect> = listOf(),
    var buildingDate: String? = null,
    var buildingCentury: String? = null,
    var images: List<ConstructionImage> = listOf()
)
