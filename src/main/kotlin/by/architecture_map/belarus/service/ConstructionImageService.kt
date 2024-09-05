package by.architecture_map.belarus.service

import by.architecture_map.belarus.entity.ConstructionImage

interface ConstructionImageService {

    fun create(image: ConstructionImage): ConstructionImage
    fun getRandomAndUniqueImages(gotImagesId: String?): MutableList<ConstructionImage>
    fun getByConstructionArchitecturalStyleId(architecturalStyleId: Int): MutableList<ConstructionImage>

    // If you can propose more descriptive name for this method please share <3
    fun getImagesWithSameArchitecturalStyleByConstructionIdAcrossImagesOfCurrentConstruction(constructionId: Int): MutableList<ConstructionImage>
    fun deleteById(id: Int): Boolean
}