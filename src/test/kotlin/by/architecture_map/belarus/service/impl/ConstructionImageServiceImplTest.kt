package by.architecture_map.belarus.service.impl

import by.architecture_map.belarus.entity.ConstructionImage
import by.architecture_map.belarus.service.ConstructionService
import by.architecture_map.belarus.repository.jpa.ConstructionImageRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class ConstructionImageServiceImplTest {

    private val constructionImageRepository: ConstructionImageRepository = mockk()
    private val constructionService: ConstructionService = mockk()
    private val constructionImageService = ConstructionImageServiceImpl(constructionImageRepository, constructionService)

    @Test
    fun whenCreateConstructionImage_thenSaveImage() {
        //given
        val image = ConstructionImage(takenTime = "пач. XX ст")
        every { constructionImageRepository.save(image) } returns image

        //when
        val result = constructionImageService.create(image)

        //then
        verify(exactly = 1) { constructionImageRepository.save(image) }
        assertEquals(image, result)
    }

    @Test
    fun whenGetRandomAndUniqueImages_thenReturnListOfImages() {
        //given
        val imageIds = "1,2,3"
        val images = mutableListOf(
                ConstructionImage(takenTime = "пач. XX ст"),
                ConstructionImage(takenTime = "cяр. XX ст")
        )
        every { constructionImageRepository.getRandomAndUniqueImages(intArrayOf(1, 2, 3)) } returns images

        //when
        val result = constructionImageService.getRandomAndUniqueImages(imageIds)

        //then
        verify(exactly = 1) { constructionImageRepository.getRandomAndUniqueImages(intArrayOf(1, 2, 3)) }
        assertEquals(images, result)
    }

    @Test
    fun whenFindConstructionImages_thenReturnListOfImages() {
        //given
        val styleId = 1
        val images = mutableListOf(
                ConstructionImage(takenTime = "пач. XX ст"),
                ConstructionImage(takenTime = "cяр. XX ст")
        )
        every { constructionImageRepository.findByConstructionArchitecturalStyleId(styleId) } returns images

        //when
        val result = constructionImageService.find(styleId)

        //then
        verify(exactly = 1) { constructionImageRepository.findByConstructionArchitecturalStyleId(styleId) }
        assertEquals(images, result)
    }
}