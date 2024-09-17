package by.architecture_map.belarus.service.impl

import by.architecture_map.belarus.entity.ConstructionImage
import by.architecture_map.belarus.exception.NotFoundException
import by.architecture_map.belarus.repository.jpa.ConstructionImageRepository
import by.architecture_map.belarus.service.ConstructionImageService
import by.architecture_map.belarus.service.ConstructionService
import org.springframework.stereotype.Service

@Service
class ConstructionImageServiceImpl(
    private val constructionImageRepository: ConstructionImageRepository,
    private val constructionService: ConstructionService
) : ConstructionImageService {

    override fun create(image: ConstructionImage): ConstructionImage = constructionImageRepository.save(image)

    override fun getRandomAndUniqueImages(usedImagesId: String?) =
        constructionImageRepository.getRandomAndUniqueImages(parseStringToIntArray(usedImagesId))

    override fun find(architecturalStyleId: Int) =
        constructionImageRepository.findByConstructionArchitecturalStyleId(architecturalStyleId)

    override fun getImagesOfConstructionsWithSameArchitecturalStyle(constructionId: Int): List<ConstructionImage> =
        constructionImageRepository.getImagesOfConstructionsWithSameArchitecturalStyleByConstructionId(
            constructionId,
            constructionService.find(constructionId).architecturalStyle?.id
        )

    override fun delete(id: Int) {
        find(id).also { constructionImageRepository.deleteById(id) }
    }
    private fun parseStringToIntArray(numbers: String?): IntArray =
        numbers
            ?.split(",")
            ?.map { it.toInt() }
            ?.toIntArray() ?: intArrayOf(0)
}
