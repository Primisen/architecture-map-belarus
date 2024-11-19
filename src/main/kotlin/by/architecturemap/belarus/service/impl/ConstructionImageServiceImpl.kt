package by.architecturemap.belarus.service.impl

import by.architecturemap.belarus.entity.ConstructionImage
import by.architecturemap.belarus.repository.jpa.ConstructionImageRepository
import by.architecturemap.belarus.service.ConstructionImageService
import by.architecturemap.belarus.service.ConstructionService
import org.springframework.stereotype.Service

@Service
class ConstructionImageServiceImpl(
    private val constructionImageRepository: ConstructionImageRepository,
    private val constructionService: ConstructionService
) : ConstructionImageService {

    override fun create(image: ConstructionImage): ConstructionImage = constructionImageRepository.save(image)

    override fun getRandomAndUniqueImages(usedImagesId: String?) =
        constructionImageRepository.getRandomAndUniqueImages(parseStringToIntArray(usedImagesId))

    override fun find(architecturalStyleId: Int): List<ConstructionImage> =
        constructionImageRepository.findByConstructionArchitecturalStyleId(architecturalStyleId)

    override fun getImagesOfConstructionsWithSameArchitecturalStyle(constructionId: Int): List<ConstructionImage> =
        constructionImageRepository.getImagesOfConstructionsWithSameArchitecturalStyleByConstructionId(
            constructionId,
            constructionService.find(constructionId).architecturalStyle.id
        )

    override fun delete(id: Int) {
        constructionImageRepository.deleteById(id)
    }

    private fun parseStringToIntArray(numbers: String?): IntArray =
        numbers
            ?.split(",")
            ?.map { it.toInt() }
            ?.toIntArray() ?: intArrayOf(0)
}
