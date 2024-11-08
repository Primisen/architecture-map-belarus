package by.architecturemap.belarus.service.impl

import by.architecturemap.belarus.entity.Address
import by.architecturemap.belarus.entity.ArchitecturalStyle
import by.architecturemap.belarus.entity.Construction
import by.architecturemap.belarus.entity.ConstructionImage
import by.architecturemap.belarus.service.ConstructionService
import by.architecturemap.belarus.repository.jpa.ConstructionImageRepository
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach

class ConstructionImageServiceImplTest {

    private val constructionImageRepository: ConstructionImageRepository = mockk()
    private val constructionService: ConstructionService = mockk()
    private val constructionImageService =
        ConstructionImageServiceImpl(constructionImageRepository, constructionService)

    private lateinit var constructionImage: ConstructionImage
    private lateinit var secondConstructionImage: ConstructionImage

    @BeforeEach
    fun setUp() {
        constructionImage = ConstructionImage(
            construction = Construction(
                name = "Name",
                address = Address(region = "Test"),
                architecturalStyle = ArchitecturalStyle(name = "Style")
            ),
            takenTime = "пач. XX ст"
        )
        secondConstructionImage = ConstructionImage(
            construction = Construction(
                name = "Name2",
                address = Address(region = "Test"),
                architecturalStyle = ArchitecturalStyle(name = "Style")
            ),
            takenTime = "сяр. XX ст"
        )
    }

    @Test
    fun givenConstructionImage_whenCreate_thenReturnSavedImage() {
        // arrange
        val expected = constructionImage
        every { constructionImageRepository.save(constructionImage) } returns constructionImage

        // act
        val actual = constructionImageService.create(constructionImage)

        // assert
        verify(exactly = 1) { constructionImageRepository.save(constructionImage) }
        assertEquals(expected, actual)
    }

    @Test
    fun givenImageIds_whenGetRandomAndUniqueImages_thenReturnListOfImages() {
        // arrange
        val imageIds = "1,2,3"
        val images = listOf(constructionImage, secondConstructionImage)

        every { constructionImageRepository.getRandomAndUniqueImages(intArrayOf(1, 2, 3)) } returns images

        // act
        val actual = constructionImageService.getRandomAndUniqueImages(imageIds)

        // assert
        verify(exactly = 1) { constructionImageRepository.getRandomAndUniqueImages(intArrayOf(1, 2, 3)) }
        assertEquals(images, actual)
    }

    @Test
    fun givenStyleId_whenFind_thenReturnListOfConstructionImages() {
        // arrange
        val styleId = 1
        val images = listOf(constructionImage, secondConstructionImage)

        every { constructionImageRepository.findByConstructionArchitecturalStyleId(styleId) } returns images

        // act
        val actual = constructionImageService.find(styleId)

        // assert
        verify(exactly = 1) { constructionImageRepository.findByConstructionArchitecturalStyleId(styleId) }
        assertEquals(images, actual)
    }

    @Test
    fun givenImageId_whenDelete_thenVerifyDeletion() {
        // arrange
        val id = 1
        every { constructionImageRepository.deleteById(id) } just Runs

        // act
        constructionImageService.delete(id)

        // assert
        verify(exactly = 1) { constructionImageRepository.deleteById(id) }
    }

    @Test
    fun givenConstructionId_whenGetImagesOfConstructionsWithSameArchitecturalStyle_thenReturnImages() {
        // arrange
        val constructionId = 2
        val architecturalStyleId = 2
        val architecturalStyle = mockk<ArchitecturalStyle>()
        val construction = Construction(
            name = "Name",
            address = Address(region = "Test"),
            architecturalStyle = architecturalStyle
        )

        val images = listOf(constructionImage, secondConstructionImage)

        every { constructionService.find(constructionId) } returns construction
        every { architecturalStyle.id } returns architecturalStyleId
        every {
            constructionImageRepository.getImagesOfConstructionsWithSameArchitecturalStyleByConstructionId(
                constructionId,
                architecturalStyleId
            )
        } returns images

        // act
        val actual = constructionImageService.getImagesOfConstructionsWithSameArchitecturalStyle(constructionId)

        // assert
        assertEquals(images, actual)
    }
}
