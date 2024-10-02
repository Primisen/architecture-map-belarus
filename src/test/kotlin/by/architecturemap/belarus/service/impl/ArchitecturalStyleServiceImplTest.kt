package by.architecturemap.belarus.service.impl

import by.architecturemap.belarus.dto.ArchitecturalStyleDTO
import by.architecturemap.belarus.entity.ArchitecturalStyle
import by.architecturemap.belarus.exception.NotFoundException
import by.architecturemap.belarus.service.ImageService
import by.architecturemap.belarus.repository.jpa.ArchitecturalStyleRepository
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import java.util.Optional

class ArchitecturalStyleServiceImplTest {

    private val architecturalStyleRepository: ArchitecturalStyleRepository = mockk()
    private val imageService: ImageService = mockk()
    private val architecturalStyleService = ArchitecturalStyleServiceImpl(architecturalStyleRepository, imageService)

    @Test
    fun whenSaveArchitecturalStyle_thenSaveStyle() {
        //given
        val architecturalStyle = ArchitecturalStyle(name = "Gothic")
            .apply { id = 1 }
        every { architecturalStyleRepository.save(architecturalStyle) } returns architecturalStyle

        //when
        val result = architecturalStyleService.create(architecturalStyle)

        //then
        verify(exactly = 1) { architecturalStyleRepository.save(architecturalStyle) }
        assertEquals(architecturalStyle, result)
    }

    @Test
    fun whenFindAllArchitecturalStyles_thenReturnListOfStyles() {
        //given
        val styles = mutableListOf(
            ArchitecturalStyle(name = "Gothic")
                .apply { id = 1 },
            ArchitecturalStyle(name = "Baroque")
                .apply { id = 2 }
        )
        every { architecturalStyleRepository.findAll() } returns styles

        //when
        val result = architecturalStyleService.findAll()

        //then
        verify(exactly = 1) { architecturalStyleRepository.findAll() }
        assertEquals(styles, result)
    }

    @Test
    fun whenFindArchitecturalStyle_thenReturnStyle() {
        //given
        val id = 1
        val architecturalStyle = ArchitecturalStyle(name = "Gothic")
            .apply { this.id = id }
        every { architecturalStyleRepository.findById(id) } returns Optional.of(architecturalStyle)

        //when
        val result = architecturalStyleService.find(id)

        //then
        verify(exactly = 1) { architecturalStyleRepository.findById(id) }
        assertEquals(architecturalStyle, result)
    }

    @Test
    fun whenFindArchitecturalStyleAndStyleDoesNotExist_thenThrowNotFoundException() {
        //given
        val id = 1
        every { architecturalStyleRepository.findById(id) } returns Optional.empty()

        //when & then
        assertThrows(NotFoundException::class.java) {
            architecturalStyleService.find(id)
        }

        //verify
        verify(exactly = 1) { architecturalStyleRepository.findById(id) }
    }

    @Test
    fun whenUpdateArchitecturalStyle_thenUpdateStyle() {
        //given
        var id = 1
        val existingStyle = ArchitecturalStyle(name = "Gothic")
            .apply { id = id }
        val updatedStyle = ArchitecturalStyle(name = "Updated Gothic")
            .apply { id = id }

        every { architecturalStyleRepository.findById(id) } returns Optional.of(existingStyle)
        every { architecturalStyleRepository.save(existingStyle) } returns existingStyle.apply {
            name = updatedStyle.name
        }

        //when
        val result = architecturalStyleService.update(id, updatedStyle)

        //then
        verify(exactly = 1) { architecturalStyleRepository.findById(id) }
        verify(exactly = 1) { architecturalStyleRepository.save(existingStyle) }
        assertEquals(updatedStyle.name, result.name)
    }

    @Test
    fun whenUpdateArchitecturalStyleAndStyleDoesNotExist_thenThrowNotFoundException() {
        //given
        var id = 1
        val updatedStyle = ArchitecturalStyle(name = "Updated Gothic")
            .apply { id = id }

        every { architecturalStyleRepository.findById(id) } returns Optional.empty()

        //when & then
        assertThrows(NotFoundException::class.java) {
            architecturalStyleService.update(id, updatedStyle)
        }

        //verify
        verify(exactly = 1) { architecturalStyleRepository.findById(id) }
    }

    @Test
    fun whenPatchArchitecturalStyle_thenPatchStyle() {
        //given
        var id = 1
        val existingStyle = ArchitecturalStyle(name = "Gothic", description = "Old Description")
            .apply { id = id }
        val patchStyle = ArchitecturalStyleDTO(name = "Gothic", description = "New Description")
            .apply { id = id }

        every { architecturalStyleRepository.findById(id) } returns Optional.of(existingStyle)
        every { architecturalStyleRepository.save(existingStyle) } returns existingStyle.apply {
            name = patchStyle.name!!
        }

        //when
        val result = architecturalStyleService.patchUpdate(id, patchStyle)

        //then
        verify(exactly = 1) { architecturalStyleRepository.findById(id) }
        verify(exactly = 1) { architecturalStyleRepository.save(existingStyle) }
        assertEquals(patchStyle.name, result.name)
    }

    @Test
    fun whenDeleteArchitecturalStyle_thenDeleteIfExists() {
        // given
        val id = 1
        val architecturalStyle = mockk<ArchitecturalStyle>()

        every { architecturalStyleRepository.findById(id) } returns Optional.of(architecturalStyle)
        every { architecturalStyleRepository.deleteById(id) } just Runs

        // when
        architecturalStyleService.delete(id)

        // then
        verify(exactly = 1) { architecturalStyleRepository.findById(id) }
        verify(exactly = 1) { architecturalStyleRepository.deleteById(id) }
    }

    @Test
    fun whenDeleteArchitecturalStyleAndStyleDoesNotExist_thenThrowNotFoundException() {
        //given
        val id = 1
        every { architecturalStyleRepository.findById(id) } returns Optional.empty()

        //when & then
        assertThrows(NotFoundException::class.java) {
            architecturalStyleService.delete(id)
        }

        //verify
        verify(exactly = 1) { architecturalStyleRepository.findById(id) }
        verify(exactly = 0) { architecturalStyleRepository.deleteById(id) }
    }
}
