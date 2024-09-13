package by.architecture_map.belarus.service.impl

import by.architecture_map.belarus.entity.ConstructionImage
<<<<<<< HEAD
import by.architecture_map.belarus.exception.NotFoundException
import by.architecture_map.belarus.repository.ConstructionImageRepository
import by.architecture_map.belarus.service.ConstructionService
=======
import by.architecture_map.belarus.repository.jpa.ConstructionImageRepository
import by.architecture_map.belarus.repository.jpa.ConstructionRepository
>>>>>>> 0d71c02 (It need refactoring)
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
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

    @Test
    fun whenDeleteConstructionImage_thenDeleteConstructionImage() {
        //given
        val id = 1
        every { constructionImageRepository.existsById(id) } returns true
        every { constructionImageRepository.deleteById(id) } just Runs

        //when
        val result = constructionImageService.delete(id)

        //then
        verify(exactly = 1) { constructionImageRepository.existsById(id) }
        verify(exactly = 1) { constructionImageRepository.deleteById(id) }
    }

    @Test
    fun whenDeleteConstructionImageAndImageDoesNotExists_thenThrowNotFoundException() {
        //given
        val id = 1
        every { constructionImageRepository.existsById(id) } returns false
        every { constructionImageRepository.deleteById(id) } just Runs

        //when & then
        assertThrows(NotFoundException::class.java) {
            constructionImageService.delete(id)
        }

        //verify
        verify(exactly = 1) { constructionImageRepository.existsById(id) }
        verify(exactly = 0) { constructionImageRepository.deleteById(id) }
    }
}