package by.architecture_map.belarus.service.impl

import by.architecture_map.belarus.entity.Architect
import by.architecture_map.belarus.repository.ArchitectRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.data.repository.findByIdOrNull
import java.util.*

class ArchitectServiceImplTest {

    private val architectRepository: ArchitectRepository = mockk();
    private val architectService = ArchitectServiceImpl(architectRepository);

    @Test
    fun whenSaveArchitect_thenReturnSavedArchitect() {
        //given
        val architect = Architect(id = 1, name = "John Doe")
        every { architectRepository.save(architect) } returns architect

        //when
        val result = architectService.create(architect)

        //then
        verify(exactly = 1) { architectRepository.save(architect) }
        assertEquals(architect, result)
    }

    @Test
    fun whenFindArchitectById_thenReturnArchitect() {
        //given
        val id = 1
        val architect = Architect(id = id, name = "John Doe")
        every { architectRepository.findByIdOrNull(id) } returns architect

        //when
        val result = architectService.findById(id)

        //then
        verify(exactly = 1) { architectRepository.findByIdOrNull(id) }
        assertEquals(architect, result)
    }

    @Test
    fun whenFindArchitectByIdAndArchitectDoesNotExist_thenReturnNull() {
        //given
        val id = 1
        every { architectRepository.findById(id) } returns Optional.empty()

        //when
        val result = architectService.findById(id)

        //then
        verify(exactly = 1) { architectRepository.findById(id) }
        assertNull(result)
    }

    @Test
    fun whenFindAll_thenReturnListOfArchitects() {
        //given
        val architects = mutableListOf(
                Architect(id = 1, name = "John Doe"),
                Architect(id = 2, name = "Jane Doe")
        )
        every { architectRepository.findAll() } returns architects

        //when
        val result = architectService.findAll()

        //then
        verify(exactly = 1) { architectRepository.findAll() }
        assertEquals(architects, result)
    }
}