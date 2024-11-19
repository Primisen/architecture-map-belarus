package by.architecturemap.belarus.service.impl

import by.architecturemap.belarus.dto.ArchitecturalStyleDTO
import by.architecturemap.belarus.entity.ArchitecturalAttribute
import by.architecturemap.belarus.entity.ArchitecturalStyle
import by.architecturemap.belarus.entity.Image
import by.architecturemap.belarus.exception.NotFoundException
import by.architecturemap.belarus.repository.jpa.ArchitecturalStyleRepository
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.Optional

class ArchitecturalStyleServiceImplTest {

    private val architecturalStyleRepository: ArchitecturalStyleRepository = mockk()
    private val architecturalStyleService = ArchitecturalStyleServiceImpl(architecturalStyleRepository)

    private val id = 1
    private lateinit var architecturalStyle: ArchitecturalStyle
    private lateinit var updatedArchitecturalStyle: ArchitecturalStyle

    @BeforeEach
    fun setUp() {
        architecturalStyle = ArchitecturalStyle(name = "Готыка")
        updatedArchitecturalStyle = ArchitecturalStyle(name = "Неаготыка")
    }

    @Test
    fun givenArchitecturalStyle_whenCreateArchitecturalStyle_thenReturnSavedStyle() {
        //arrange
        val expected = architecturalStyle
        every { architecturalStyleRepository.save(architecturalStyle) } returns architecturalStyle

        //act
        val actual = architecturalStyleService.create(architecturalStyle)

        //assert
        assertEquals(expected, actual)
    }

    @Test
    fun whenFindAllArchitecturalStyles_thenReturnListOfStyles() {
        //arrange
        val expected = listOf(architecturalStyle, architecturalStyle)
        every { architecturalStyleRepository.findAll() } returns expected

        //act
        val actual = architecturalStyleService.findAll()

        //assert
        assertEquals(expected, actual)
    }

    @Test
    fun givenIfOfExistedArchitecturalStyle_whenFindStyleById_thenReturnStyle() {
        //arrange
        val expected = architecturalStyle
        every { architecturalStyleRepository.findById(id) } returns Optional.of(architecturalStyle)

        //act
        val actual = architecturalStyleService.find(id)

        //assert
        assertEquals(expected, actual)
    }

    @Test
    fun givenIdOfNotExistedArchitecturalStyle_whenFindArchitecturalStyleById_thenThrowNotFoundException() {
        //arrange
        every { architecturalStyleRepository.findById(id) } returns Optional.empty()

        //act & assert
        assertThrows<NotFoundException> { architecturalStyleService.find(id) }
        verify { architecturalStyleRepository.findById(id) }
    }

    @Test
    fun givenUpdatedArchitecturalStyle_whenUpdateArchitecturalStyle_thenReturnUpdatedAndSavedStyle() {
        //arrange
        val expected = architecturalStyle.apply { name = updatedArchitecturalStyle.name }
        every { architecturalStyleRepository.findById(id) } returns Optional.of(architecturalStyle)
        every { architecturalStyleRepository.save(architecturalStyle) } returns architecturalStyle

        //act
        val actual = architecturalStyleService.update(id, updatedArchitecturalStyle)

        //assert
        assertEquals(expected, actual)
    }

    @Test
    fun givenUpdatedArchitecturalStyle_whenUpdateStyleAndStyleDoesNotExist_thenThrowNotFoundException() {
        //arrange
        every { architecturalStyleRepository.findById(id) } returns Optional.empty()

        //act & assert
        assertThrows<NotFoundException> { architecturalStyleService.update(id, updatedArchitecturalStyle) }
        verify { architecturalStyleRepository.findById(id) }
    }

    @Test
    fun whenPatchArchitecturalStyle_thenReturnUpdatedAndSavedStyle() {
        //arrange
        val patchStyle = ArchitecturalStyleDTO(
            name = "Віленскае барока",
            description = "New Description",
            attributes = listOf(ArchitecturalAttribute(name = "Пілястра")),
            shortDescription = "Прыгожа",
            yearsActive = "18 ст",
            demonstrativeImage = Image(url = "https://")
        )
        val expected = architecturalStyle.apply {
            name = patchStyle.name!!
            description = patchStyle.description
            attributes = patchStyle.attributes
            shortDescription = patchStyle.shortDescription
            yearsActive = patchStyle.yearsActive
            demonstrativeImage = patchStyle.demonstrativeImage
        }

        every { architecturalStyleRepository.findById(id) } returns Optional.of(architecturalStyle)
        every { architecturalStyleRepository.save(architecturalStyle) } returns architecturalStyle

        //act
        val actual = architecturalStyleService.patchUpdate(id, patchStyle)

        //assert
        verify { architecturalStyleRepository.findById(id) }
        verify { architecturalStyleRepository.save(architecturalStyle) }

        assertEquals(expected, actual)
    }

    @Test
    fun givenUpdatedArchitecturalStyleDTOWithAllNullableFields_thenReturnStyleWithoutAnyChanges() {
        //arrange
        val expected = architecturalStyle
        val patchStyle = ArchitecturalStyleDTO(
            name = null,
            description = null,
            shortDescription = null,
            yearsActive = null,
            demonstrativeImage = null
        )

        every { architecturalStyleRepository.findById(id) } returns Optional.of(architecturalStyle)
        every { architecturalStyleRepository.save(architecturalStyle) } returns architecturalStyle

        //act
        val actual = architecturalStyleService.patchUpdate(id, patchStyle)

        //assert
        verify(exactly = 1) { architecturalStyleRepository.findById(id) }
        verify(exactly = 1) { architecturalStyleRepository.save(architecturalStyle) }

        assertEquals(expected, actual)
    }

    @Test
    fun givenUpdatedArchitecturalStyleDTOWithAllBlankFields_thenReturnStyleWithoutAnyChanges() {
        //arrange
        val expected = architecturalStyle
        val patchStyle = ArchitecturalStyleDTO(
            name = "",
            description = "",
            attributes = emptyList(),
            shortDescription = "",
            yearsActive = "",
        )

        every { architecturalStyleRepository.findById(id) } returns Optional.of(architecturalStyle)
        every { architecturalStyleRepository.save(architecturalStyle) } returns architecturalStyle

        //act
        val actual = architecturalStyleService.patchUpdate(id, patchStyle)

        //assert
        verify(exactly = 1) { architecturalStyleRepository.findById(id) }
        verify(exactly = 1) { architecturalStyleRepository.save(architecturalStyle) }

        assertEquals(expected, actual)
    }

    @Test
    fun whenDeleteArchitecturalStyleById_thenCheckThatDeleteFunctionWasCall() {
        //arrange
        every { architecturalStyleRepository.findById(id) } returns Optional.of(architecturalStyle)
        every { architecturalStyleRepository.deleteById(id) } just Runs

        //act
        architecturalStyleService.delete(id)

        //assert
        verify(exactly = 1) { architecturalStyleRepository.findById(id) }
        verify(exactly = 1) { architecturalStyleRepository.deleteById(id) }
    }

    @Test
    fun whenDeleteArchitecturalStyleAndStyleDoesNotExist_thenThrowNotFoundException() {
        //arrange
        every { architecturalStyleRepository.findById(id) } returns Optional.empty()

        //act & assert
        assertThrows<NotFoundException> { architecturalStyleService.delete(id) }
        verify(exactly = 1) { architecturalStyleRepository.findById(id) }
        verify(exactly = 0) { architecturalStyleRepository.deleteById(id) }
    }
}
