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

    override fun create(image: ConstructionImage) = constructionImageRepository.save(image)

    override fun getRandomAndUniqueImages(usedImagesId: String?) =
        constructionImageRepository.getRandomAndUniqueImages(parseStringToArrayOfInt(usedImagesId))

    override fun find(architecturalStyleId: Int) =
        constructionImageRepository.findByConstructionArchitecturalStyleId(architecturalStyleId)

    override fun getImagesOfConstructionsWithSameArchitecturalStyle(constructionId: Int): List<ConstructionImage> {
        return constructionImageRepository.getImagesOfConstructionsWithSameArchitecturalStyleByConstructionId(
            constructionId,
            constructionService.find(constructionId).architecturalStyle?.id
        )
    }

    override fun delete(id: Int) {

        if (constructionImageRepository.existsById(id)) {
            constructionImageRepository.deleteById(id)
        } else {
            throw NotFoundException("Construction Image not found with id: $id")
        }
    }

    private fun parseStringToArrayOfInt(string: String?): IntArray {

        var arrayOfInt = intArrayOf(0)

        if (!string.isNullOrEmpty()) {
            val arrayOfStrings = string.split(",")
            arrayOfInt = IntArray(arrayOfStrings.size)

            for (i in arrayOfStrings.indices) {
                arrayOfInt[i] = arrayOfStrings[i].toInt()
            }
        }

        return arrayOfInt
    }
}
