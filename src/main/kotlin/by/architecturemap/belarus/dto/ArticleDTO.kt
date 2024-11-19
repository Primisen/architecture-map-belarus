package by.architecturemap.belarus.dto

import by.architecturemap.belarus.entity.Image
import by.architecturemap.belarus.entity.Tag

data class ArticleDTO(
    var title: String? = null,
    var content: String? = null,
    var shortDescription: String? = null,
    var demonstrativeImage: Image? = null,
    var tag: List<Tag> = listOf()
)
