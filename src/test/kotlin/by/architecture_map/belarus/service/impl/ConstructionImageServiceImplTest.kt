package by.architecture_map.belarus.service.impl

import by.architecture_map.belarus.entity.ArchitecturalStyle
import by.architecture_map.belarus.entity.Construction
import by.architecture_map.belarus.entity.ConstructionImage
import by.architecture_map.belarus.repository.ConstructionImageRepository
import by.architecture_map.belarus.repository.ConstructionRepository
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.util.*

class ConstructionImageServiceImplTest {

    private val constructionImageRepository: ConstructionImageRepository = mockk()
    private val constructionRepository: ConstructionRepository = mockk()
    private val constructionImageService = ConstructionImageServiceImpl(constructionImageRepository, constructionRepository)

    @Test
    fun whenCreateConstructionImage_thenReturnSavedImage() {
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
    fun whenGetByConstructionArchitecturalStyleId_thenReturnListOfImages() {
        //given
        val styleId = 1
        val images = mutableListOf(
                ConstructionImage(takenTime = "пач. XX ст"),
                ConstructionImage(takenTime = "cяр. XX ст")
        )
        every { constructionImageRepository.findByConstructionArchitecturalStyleId(styleId) } returns images

        //when
        val result = constructionImageService.getByConstructionArchitecturalStyleId(styleId)

        //then
        verify(exactly = 1) { constructionImageRepository.findByConstructionArchitecturalStyleId(styleId) }
        assertEquals(images, result)
    }

    @Test
    fun whenGetImagesWithSameArchitecturalStyleByConstructionIdAcrossImagesOfCurrentConstruction_thenReturnListOfImages() {
        //given
        val constructionId = 1
        val styleId = 1
        val images = mutableListOf(
                ConstructionImage(takenTime = "пач. XX ст"),
                ConstructionImage(takenTime = "cяр. XX ст")
        )
        val construction = Construction(id = 1, architecturalStyle = ArchitecturalStyle(id = styleId))

        every { constructionRepository.findById(constructionId) } returns Optional.of(construction)
        every { constructionImageRepository.getImagesWithSameArchitecturalStyleByConstructionIdAcrossImagesOfCurrentConstruction(constructionId, styleId) } returns images

        //when
        val result = constructionImageService.getImagesWithSameArchitecturalStyleByConstructionIdAcrossImagesOfCurrentConstruction(constructionId)

        //then
        verify(exactly = 1) { constructionRepository.findById(constructionId) }
        verify(exactly = 1) { constructionImageRepository.getImagesWithSameArchitecturalStyleByConstructionIdAcrossImagesOfCurrentConstruction(constructionId, styleId) }
        assertEquals(images, result)
    }

    @Test
    fun whenDeleteById_thenReturnTrueIfExists() {
        //given
        val id = 1
        every { constructionImageRepository.existsById(id) } returns true
        every { constructionImageRepository.deleteById(id) } just Runs

        //when
        val result = constructionImageService.deleteById(id)

        //then
        verify(exactly = 1) { constructionImageRepository.existsById(id) }
        verify(exactly = 1) { constructionImageRepository.deleteById(id) }
        assertTrue(result)
    }

    @Test
    fun whenDeleteById_thenReturnFalseIfNotExists() {
        //given
        val id = 1
        every { constructionImageRepository.existsById(id) } returns false
        every { constructionImageRepository.deleteById(id) } just Runs

        //when
        val result = constructionImageService.deleteById(id)

        //then
        verify(exactly = 1) { constructionImageRepository.existsById(id) }
        verify(exactly = 0) { constructionImageRepository.deleteById(id) }
        assertFalse(result)
    }
}