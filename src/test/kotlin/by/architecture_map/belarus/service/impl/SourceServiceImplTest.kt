package by.architecture_map.belarus.service.impl

import by.architecture_map.belarus.entity.Source
import by.architecture_map.belarus.repository.SourceRepository
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.util.*

class SourceServiceImplTest {

    private val sourceRepository: SourceRepository = mockk()
    private val sourceService = SourceServiceImpl(sourceRepository)

    @Test
    fun whenCreateSource_thenReturnSavedSource() {
        //given
        val source = Source(id = 1, name = "Source Name", url = "http://example.com", description = "Source Description")
        every { sourceRepository.save(source) } returns source

        //when
        val result = sourceService.create(source)

        //then
        verify(exactly = 1) { sourceRepository.save(source) }
        assertEquals(source, result)
    }

    @Test
    fun whenFindAllSources_thenReturnListOfSources() {
        //given
        val sources = mutableListOf(
                Source(id = 1, name = "Source 1", url = "http://example.com/1", description = "Description 1"),
                Source(id = 2, name = "Source 2", url = "http://example.com/2", description = "Description 2")
        )
        every { sourceRepository.findAll() } returns sources

        //when
        val result = sourceService.findAll()

        //then
        verify(exactly = 1) { sourceRepository.findAll() }
        assertEquals(sources, result)
    }

    @Test
    fun whenUpdateById_thenReturnUpdatedSource() {
        //given
        val id = 1
        val existingSource = Source(id = id, name = "Old Name", url = "http://example.com", description = "Old Description")
        val updatedSource = Source(id = id, name = "Updated Name", url = "http://new-url.com", description = "Updated Description")
        every { sourceRepository.findById(id) } returns Optional.of(existingSource)
        every { sourceRepository.save(existingSource) } returns existingSource.apply {
            name = updatedSource.name
            url = updatedSource.url
            description = updatedSource.description
        }

        //when
        val result = sourceService.updateById(id, updatedSource)

        //then
        verify(exactly = 1) { sourceRepository.findById(id) }
        verify(exactly = 1) { sourceRepository.save(existingSource) }
        assertEquals(existingSource, result)
    }

    @Test
    fun whenPatchById_thenReturnPatchedSource() {
        //given
        val id = 1
        val existingSource = Source(id = id, name = "Old Name", url = "http://example.com", description = "Old Description")
        val updatedSource = Source(id = id, name = "Updated Name", url = null, description = "Updated Description")
        every { sourceRepository.findById(id) } returns Optional.of(existingSource)
        every { sourceRepository.save(existingSource) } returns existingSource.apply {
            name = updatedSource.name
            url = existingSource.url
            description = updatedSource.description
        }

        //when
        val result = sourceService.patchById(id, updatedSource)

        //then
        verify(exactly = 1) { sourceRepository.findById(id) }
        verify(exactly = 1) { sourceRepository.save(existingSource) }
        assertEquals(existingSource, result)
    }

    @Test
    fun whenDeleteById_thenReturnTrueIfExists() {
        //given
        val id = 1
        every { sourceRepository.existsById(id) } returns true
        every { sourceRepository.deleteById(id) } just Runs

        //when
        val result = sourceService.deleteById(id)

        //then
        verify(exactly = 1) { sourceRepository.existsById(id) }
        verify(exactly = 1) { sourceRepository.deleteById(id) }
        assertTrue(result)
    }

    @Test
    fun whenDeleteById_thenReturnFalseIfNotExists() {
        //given
        val id = 1
        every { sourceRepository.existsById(id) } returns false
        every { sourceRepository.deleteById(id) } just Runs

        //when
        val result = sourceService.deleteById(id)

        //then
        verify(exactly = 1) { sourceRepository.existsById(id) }
        verify(exactly = 0) { sourceRepository.deleteById(id) }
        assertFalse(result)
    }
}