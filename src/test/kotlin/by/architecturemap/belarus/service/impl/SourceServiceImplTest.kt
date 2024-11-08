package by.architecturemap.belarus.service.impl

import by.architecturemap.belarus.dto.SourceDTO
import by.architecturemap.belarus.entity.Source
import by.architecturemap.belarus.exception.NotFoundException
import by.architecturemap.belarus.repository.jpa.SourceRepository
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import java.util.Optional

class SourceServiceImplTest {

    private val sourceRepository: SourceRepository = mockk()
    private val sourceService = SourceServiceImpl(sourceRepository)

    private val id = 1
    private lateinit var source: Source
    private lateinit var updatedSource: Source

    @BeforeEach
    fun setUp() {
        source = Source(name = "Source Name", url = "http://example.com", description = "Source Description")
        updatedSource = Source(name = "Updated Name", url = "http://new-url.com", description = "Updated Description")
    }

    @Test
    fun givenSource_whenCreate_thenReturnSavedSource() {
        // arrange
        val expected = source
        every { sourceRepository.save(source) } returns source

        // act
        val actual = sourceService.create(source)

        // assert
        verify(exactly = 1) { sourceRepository.save(source) }
        assertEquals(expected, actual)
    }

    @Test
    fun whenFindAll_thenReturnListOfSources() {
        // arrange
        val sources = listOf(source, updatedSource)
        every { sourceRepository.findAll() } returns sources

        // act
        val actual = sourceService.findAll()

        // assert
        verify(exactly = 1) { sourceRepository.findAll() }
        assertEquals(sources, actual)
    }

    @Test
    fun givenSourceId_whenUpdate_thenReturnUpdatedSource() {
        // arrange
        val expected = updatedSource
        every { sourceRepository.findById(id) } returns Optional.of(source)
        every { sourceRepository.save(source) } returns source

        // act
        val actual = sourceService.update(id, updatedSource)

        // assert
        verify(exactly = 1) { sourceRepository.findById(id) }
        verify(exactly = 1) { sourceRepository.save(source) }
        assertEquals(expected, actual)
    }

    @Test
    fun givenSourceDTO_whenPatchUpdate_thenReturnPatchedSource() {
        // arrange
        val sourceDTO = SourceDTO(
            name = "Updated Name",
            url = "http://example2.com",
            description = "Updated Description"
        )

        every { sourceRepository.findById(id) } returns Optional.of(source)
        every { sourceRepository.save(source) } returns source

        // act
        val actual = sourceService.patchUpdate(id, sourceDTO)

        // assert
        val expected = Source(
            name = "Updated Name",
            url = "http://example2.com",
            description = "Updated Description"
        )
        verify(exactly = 1) { sourceRepository.findById(id) }
        verify(exactly = 1) { sourceRepository.save(source) }
        assertEquals(expected, actual)
    }

    @Test
    fun givenBlankSourceDTO_whenPatchUpdate_thenReturnOriginalSource() {
        // arrange
        val sourceDTO = SourceDTO(
            name = "",
            url = "",
            description = ""
        )

        every { sourceRepository.findById(id) } returns Optional.of(source)
        every { sourceRepository.save(source) } returns source

        // act
        val actual = sourceService.patchUpdate(id, sourceDTO)

        // assert
        val expected = source
        verify(exactly = 1) { sourceRepository.findById(id) }
        verify(exactly = 1) { sourceRepository.save(source) }
        assertEquals(expected, actual)
    }

    @Test
    fun givenSourceDTOWithNullFields_whenPatchUpdate_thenReturnOriginalSource() {
        // arrange
        val sourceDTO = SourceDTO(
            name = null,
            url = null,
            description = null
        )

        every { sourceRepository.findById(id) } returns Optional.of(source)
        every { sourceRepository.save(source) } returns source

        // act
        val actual = sourceService.patchUpdate(id, sourceDTO)

        // assert
        val expected = source
        verify(exactly = 1) { sourceRepository.findById(id) }
        verify(exactly = 1) { sourceRepository.save(source) }
        assertEquals(expected, actual)
    }

    @Test
    fun givenSourceId_whenDelete_thenVerifyDeletion() {
        // arrange
        every { sourceRepository.findById(id) } returns Optional.of(source)
        every { sourceRepository.deleteById(id) } just Runs

        // act
        sourceService.delete(id)

        // assert
        verify(exactly = 1) { sourceRepository.findById(id) }
        verify(exactly = 1) { sourceRepository.deleteById(id) }
    }

    @Test
    fun givenInvalidSourceId_whenDelete_thenThrowNotFoundException() {
        // arrange
        every { sourceRepository.findById(id) } returns Optional.empty()

        // act & assert
        assertThrows<NotFoundException> { sourceService.delete(id) }

        // verify
        verify(exactly = 1) { sourceRepository.findById(id) }
        verify(exactly = 0) { sourceRepository.deleteById(id) }
    }
}
