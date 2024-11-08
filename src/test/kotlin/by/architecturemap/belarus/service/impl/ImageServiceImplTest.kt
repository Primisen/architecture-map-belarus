package by.architecturemap.belarus.service.impl

import by.architecturemap.belarus.entity.Image
import by.architecturemap.belarus.exception.NotFoundException
import by.architecturemap.belarus.repository.jpa.ImageRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import java.util.Optional

class ImageServiceImplTest {

    private val imageRepository: ImageRepository = mockk()
    private val imageService = ImageServiceImpl(imageRepository)

    private val id = 1

    @Test
    fun givenImageId_whenFindImage_thenReturnImage() {

        //arrange
        val expected = Image(url = "https://test.com")
        every { imageRepository.findById(id) } returns Optional.of(expected)

        //act
        val actual = imageService.find(id)

        //assert
        assertEquals(expected, actual)
    }

    @Test
    fun givenImageId_whenFindImageAndImageNotFound_thenThrowNotFoundException() {

        //arrange
        every { imageRepository.findById(id) } returns Optional.empty()

        //act & assert
        assertThrows<NotFoundException> { imageService.find(id) }
        verify(exactly = 1) { imageRepository.findById(id) }
    }
}
