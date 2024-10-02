package by.architecturemap.belarus.dto

import by.architecturemap.belarus.entity.ArchitecturalAttribute
import by.architecturemap.belarus.entity.Image

data class ArchitecturalStyleDTO(
    var name: String? = null,
    var description: String? = null,
    var demonstrativeImage: Image? = null,
    var shortDescription: String? = null,
    var yearsActive: String? = null,
    var attributes: List<ArchitecturalAttribute> = listOf()
)
