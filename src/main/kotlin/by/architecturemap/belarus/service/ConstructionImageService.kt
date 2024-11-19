package by.architecturemap.belarus.service

import by.architecturemap.belarus.entity.ConstructionImage

interface ConstructionImageService {

    fun create(image: ConstructionImage): ConstructionImage
    fun find(architecturalStyleId: Int): List<ConstructionImage>

    /**
     * @param usedImagesId The construction image ids, which have already been
     * obtained from the database and are keeping in order not to select them again
     */
    fun getRandomAndUniqueImages(usedImagesId: String?): List<ConstructionImage>

    /**
     * The function searches for images in the same architectural style such has construction.
     * At the same time, images of the construction itself are excluded from the answer
     */
    fun getImagesOfConstructionsWithSameArchitecturalStyle(constructionId: Int): List<ConstructionImage>
    fun delete(id: Int)
}
