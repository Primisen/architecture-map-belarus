package by.architecture_map.belarus.service.impl

import by.architecture_map.belarus.entity.ArchitecturalStyle
import by.architecture_map.belarus.repository.ArchitecturalStyleRepository
import by.architecture_map.belarus.repository.ImageRepository
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.data.repository.findByIdOrNull
import java.util.*

class ArchitecturalStyleServiceImplTest {

    private val architecturalStyleRepository: ArchitecturalStyleRepository = mockk()
    private val imageRepository: ImageRepository = mockk()
    private val architecturalStyleService = ArchitecturalStyleServiceImpl(architecturalStyleRepository, imageRepository)

    @Test
    fun whenSaveArchitecturalStyle_thenReturnSavedStyle() {
        //given
        val architecturalStyle = ArchitecturalStyle(id = 1, name = "Gothic")
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
                ArchitecturalStyle(id = 1, name = "Gothic"),
                ArchitecturalStyle(id = 2, name = "Baroque")
        )
        every { architecturalStyleRepository.findAll() } returns styles

        //when
        val result = architecturalStyleService.findAll()

        //then
        verify(exactly = 1) { architecturalStyleRepository.findAll() }
        assertEquals(styles, result)
    }

    @Test
    fun whenFindArchitecturalStyleById_thenReturnStyle() {
        //given
        val id = 1
        val architecturalStyle = ArchitecturalStyle(id = id, name = "Gothic")
        every { architecturalStyleRepository.findByIdOrNull(id) } returns architecturalStyle

        //when
        val result = architecturalStyleService.findById(id)

        //then
        verify(exactly = 1) { architecturalStyleRepository.findByIdOrNull(id) }
        assertEquals(architecturalStyle, result)
    }

    @Test
    fun whenFindArchitecturalStyleByIdAndStyleDoesNotExist_thenReturnNull() {
        //given
        val id = 1
        every { architecturalStyleRepository.findByIdOrNull(id) } returns null

        //when
        val result = architecturalStyleService.findById(id)

        //then
        verify(exactly = 1) { architecturalStyleRepository.findByIdOrNull(id) }
        assertNull(result)
    }

    @Test
    fun whenUpdateArchitecturalStyleById_thenReturnUpdatedStyle() {
        //given
        val id = 1
        val existingStyle = ArchitecturalStyle(id = id, name = "Gothic")
        val updatedStyle = ArchitecturalStyle(id = id, name = "Updated Gothic")

        every { architecturalStyleRepository.findById(id) } returns Optional.of(existingStyle)
        every { architecturalStyleRepository.save(existingStyle) } returns existingStyle.apply {
            name = updatedStyle.name
        }

        //when
        val result = architecturalStyleService.updateById(id, updatedStyle)

        //then
        verify(exactly = 1) { architecturalStyleRepository.findById(id) }
        verify(exactly = 1) { architecturalStyleRepository.save(existingStyle) }
        assertEquals(updatedStyle.name, result?.name)
    }

    @Test
    fun whenUpdateArchitecturalStyleByIdAndStyleDoesNotExist_thenReturnNull() {
        //given
        val id = 1
        val updatedStyle = ArchitecturalStyle(id = id, name = "Updated Gothic")

        every { architecturalStyleRepository.findById(id) } returns Optional.empty()

        //when
        val result = architecturalStyleService.updateById(id, updatedStyle)

        //then
        verify(exactly = 1) { architecturalStyleRepository.findById(id) }
        assertNull(result)
    }

    @Test
    fun whenPatchArchitecturalStyleById_thenReturnPatchedStyle() {
        //given
        val id = 1
        val existingStyle = ArchitecturalStyle(id = id, name = "Gothic", description = "Old Description")
        val patchStyle = ArchitecturalStyle(id = id, description = "New Description")

        every { architecturalStyleRepository.findById(id) } returns Optional.of(existingStyle)
        every { architecturalStyleRepository.save(existingStyle) } returns existingStyle.apply {
            name = patchStyle.name
        }

        //when
        val result = architecturalStyleService.patchById(id, patchStyle)

        //then
        verify(exactly = 1) { architecturalStyleRepository.findById(id) }
        verify(exactly = 1) { architecturalStyleRepository.save(existingStyle) }
        assertEquals(patchStyle.name, result?.name)
    }

    @Test
    fun whenDeleteArchitecturalStyleById_thenReturnTrueIfExists() {
        //given
        val id = 1
        every { architecturalStyleRepository.existsById(id) } returns true
        every { architecturalStyleRepository.deleteById(id) } just Runs

        //when
        val result = architecturalStyleService.deleteById(id)

        //then
        verify(exactly = 1) { architecturalStyleRepository.existsById(id) }
        verify(exactly = 1) { architecturalStyleRepository.deleteById(id) }
        assertTrue(result)
    }

    @Test
    fun whenDeleteArchitecturalStyleByIdAndStyleDoesNotExist_thenReturnFalseIfNotExists() {
        //given
        val id = 1
        every { architecturalStyleRepository.existsById(id) } returns false
        every { architecturalStyleRepository.deleteById(id) } just Runs

        //when
        val result = architecturalStyleService.deleteById(id)

        //then
        verify(exactly = 1) { architecturalStyleRepository.existsById(id) }
        verify(exactly = 0) { architecturalStyleRepository.deleteById(id) }
        assertFalse(result)
    }
}