package by.architecturemap.belarus.service.impl

import by.architecturemap.belarus.entity.User
import by.architecturemap.belarus.entity.UserImage
import by.architecturemap.belarus.exception.NotFoundException
import by.architecturemap.belarus.repository.jpa.UserImageRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.Optional

class UserImageServiceImplTest {

    private val userImageRepository: UserImageRepository = mockk()
    private val userImageServiceImpl = UserImageServiceImpl(userImageRepository)

    private lateinit var userImage: UserImage

    @BeforeEach
    fun setUp() {
        userImage = UserImage(
            user = User(username = "ff@test.com", password = CharArray(8)),
            approvedByAdmin = false
        )
    }

    @Test
    fun givenImagesList_whenAddImages_thenVerifySaveAllImages() {
        // arrange
        val images = listOf(userImage)
        every { userImageRepository.saveAll(images) } returns images

        // act
        userImageServiceImpl.addImages(images)

        // assert
        verify(exactly = 1) { userImageRepository.saveAll(images) }
    }

    @Test
    fun givenImageId_whenApproveImage_thenReturnApprovedImage() {
        // arrange
        val id = 1
        every { userImageRepository.findById(id) } returns Optional.of(userImage)
        every { userImageRepository.save(userImage) } returns userImage

        // act
        val actual = userImageServiceImpl.approveImage(id)

        // assert
        val expected = userImage.copy(approvedByAdmin = true)
        verify(exactly = 1) { userImageRepository.findById(id) }
        verify(exactly = 1) { userImageRepository.save(userImage) }
        assertEquals(expected, actual)
    }

    @Test
    fun givenImageId_whenApproveImageAndImageNotFound_thenThrowNotFoundException() {
        // arrange
        val id = 1
        every { userImageRepository.findById(id) } returns Optional.empty()

        // act & assert
        assertThrows<NotFoundException> { userImageServiceImpl.approveImage(id) }

        // verify
        verify(exactly = 1) { userImageRepository.findById(id) }
        verify(exactly = 0) { userImageRepository.save(any()) }
    }
}
