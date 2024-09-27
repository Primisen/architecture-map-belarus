package by.architecturemap.belarus.service.impl

import by.architecturemap.belarus.entity.Address
import by.architecturemap.belarus.entity.ArchitecturalStyle
import by.architecturemap.belarus.entity.Construction
import by.architecturemap.belarus.entity.ConstructionImage
import by.architecturemap.belarus.service.ConstructionService
import by.architecturemap.belarus.repository.jpa.ConstructionImageRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class ConstructionImageServiceImplTest {

    private val constructionImageRepository: ConstructionImageRepository = mockk()
    private val constructionService: ConstructionService = mockk()
    private val constructionImageService =
        ConstructionImageServiceImpl(constructionImageRepository, constructionService)

    @Test
    fun whenCreateConstructionImage_thenSaveImage() {
        //given
        val image = ConstructionImage(
            construction = Construction(
                name = "Name",
                address = Address(region = "Test"),
                architecturalStyle = ArchitecturalStyle(name = "Style")
            ),
            takenTime = "пач. XX ст"
        )
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
            ConstructionImage(
                construction = Construction(
                    name = "Name1",
                    address = Address(region = "Test"),
                    architecturalStyle = ArchitecturalStyle(name = "Style")
                ),
                takenTime = "пач. XX ст"
            ),
            ConstructionImage(
                construction = Construction(
                    name = "Name2",
                    address = Address(region = "Test"),
                    architecturalStyle = ArchitecturalStyle(name = "Style")
                ),
                takenTime = "пач. XX ст"
            )
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
            ConstructionImage(
                construction = Construction(
                    name = "Name1",
                    address = Address(region = "Test"),
                    architecturalStyle = ArchitecturalStyle(name = "Style")
                ),
                takenTime = "пач. XX ст"
            ),
            ConstructionImage(
                construction = Construction(
                    name = "Name2",
                    address = Address(region = "Test"),
                    architecturalStyle = ArchitecturalStyle(name = "Style")
                ),
                takenTime = "пач. XX ст"
            )
        )
        every { constructionImageRepository.findByConstructionArchitecturalStyleId(styleId) } returns images

        //when
        val result = constructionImageService.find(styleId)

        //then
        verify(exactly = 1) { constructionImageRepository.findByConstructionArchitecturalStyleId(styleId) }
        assertEquals(images, result)
    }
}
