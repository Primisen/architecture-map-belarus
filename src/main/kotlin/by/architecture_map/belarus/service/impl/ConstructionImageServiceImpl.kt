package by.architecture_map.belarus.service.impl

import by.architecture_map.belarus.entity.ConstructionImage
import by.architecture_map.belarus.repository.ConstructionImageRepository
import by.architecture_map.belarus.repository.ConstructionRepository
import by.architecture_map.belarus.service.ConstructionImageService
import org.springframework.stereotype.Service

@Service
class ConstructionImageServiceImpl(
        private val constructionImageRepository: ConstructionImageRepository,
        private val constructionRepository: ConstructionRepository
) : ConstructionImageService {

    override fun create(image: ConstructionImage): ConstructionImage {
        return constructionImageRepository.save(image)
    }

    override fun getRandomAndUniqueImages(gotImagesId: String?): MutableList<ConstructionImage> {
        return constructionImageRepository.getRandomAndUniqueImages(parseStringToArrayOfInt(gotImagesId))
    }

    override fun getByConstructionArchitecturalStyleId(architecturalStyleId: Int): MutableList<ConstructionImage> {
        return constructionImageRepository.findByConstructionArchitecturalStyleId(architecturalStyleId)
    }

    override fun getImagesWithSameArchitecturalStyleByConstructionIdAcrossImagesOfCurrentConstruction(constructionId: Int): MutableList<ConstructionImage> {
        return constructionImageRepository.getImagesWithSameArchitecturalStyleByConstructionIdAcrossImagesOfCurrentConstruction(
                constructionId,
                constructionRepository.findById(constructionId).orElseThrow().architecturalStyle?.id
        )
    }

    override fun deleteById(id: Int): Boolean {

        if (constructionImageRepository.existsById(id)) {
            constructionImageRepository.deleteById(id)
            return true
        }

        return false
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