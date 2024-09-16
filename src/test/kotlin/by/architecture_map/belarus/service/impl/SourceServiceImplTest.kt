package by.architecture_map.belarus.service.impl

import by.architecture_map.belarus.entity.Source
import by.architecture_map.belarus.exception.NotFoundException
import by.architecture_map.belarus.repository.jpa.SourceRepository
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
    fun whenCreateSource_thenSaveSource() {
        //given
        val source = Source(name = "Source Name", url = "http://example.com", description = "Source Description")
            .apply { id = 1 }
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
            Source(name = "Source 1", url = "http://example.com/1", description = "Description 1")
                .apply { id = 1 },
            Source(name = "Source 2", url = "http://example.com/2", description = "Description 2")
                .apply { id = 2 }
        )
        every { sourceRepository.findAll() } returns sources

        //when
        val result = sourceService.findAll()

        //then
        verify(exactly = 1) { sourceRepository.findAll() }
        assertEquals(sources, result)
    }

    @Test
    fun whenUpdate_thenUpdateSource() {
        //given
        var id = 1
        val existingSource =
            Source(name = "Old Name", url = "http://example.com", description = "Old Description")
                .apply { id = id }
        val updatedSource =
            Source(name = "Updated Name", url = "http://new-url.com", description = "Updated Description")
                .apply { id = id }
        every { sourceRepository.findById(id) } returns Optional.of(existingSource)
        every { sourceRepository.save(existingSource) } returns existingSource.apply {
            name = updatedSource.name
            url = updatedSource.url
            description = updatedSource.description
        }

        //when
        val result = sourceService.update(id, updatedSource)

        //then
        verify(exactly = 1) { sourceRepository.findById(id) }
        verify(exactly = 1) { sourceRepository.save(existingSource) }
        assertEquals(existingSource, result)
    }

    @Test
    fun whenPatch_thenPatchSource() {
        //given
        var id = 1
        val existingSource =
            Source(name = "Old Name", url = "http://example.com", description = "Old Description")
                .apply { id = id }
        val updatedSource = Source(name = "Updated Name", url = null, description = "Updated Description")
            .apply { id = id }
        every { sourceRepository.findById(id) } returns Optional.of(existingSource)
        every { sourceRepository.save(existingSource) } returns existingSource.apply {
            name = updatedSource.name
            url = existingSource.url
            description = updatedSource.description
        }

        //when
        val result = sourceService.patchUpdate(id, updatedSource)

        //then
        verify(exactly = 1) { sourceRepository.findById(id) }
        verify(exactly = 1) { sourceRepository.save(existingSource) }
        assertEquals(existingSource, result)
    }

    @Test
    fun whenDelete_thenDeleteSource() {
        //given
        val id = 1
        every { sourceRepository.existsById(id) } returns true
        every { sourceRepository.deleteById(id) } just Runs

        //when
        sourceService.delete(id)

        //then
        verify(exactly = 1) { sourceRepository.existsById(id) }
        verify(exactly = 1) { sourceRepository.deleteById(id) }
    }

    @Test
    fun whenDeleteSourceAndSourceDoesNotExists_thenThrowNotFoundException() {
        //given
        val id = 1
        every { sourceRepository.existsById(id) } returns false
        every { sourceRepository.deleteById(id) } just Runs

        //when & then
        assertThrows(NotFoundException::class.java) {
            sourceService.delete(id)
        }

        //verify
        verify(exactly = 1) { sourceRepository.existsById(id) }
        verify(exactly = 0) { sourceRepository.deleteById(id) }
    }
}
