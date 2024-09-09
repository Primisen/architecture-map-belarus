package by.architecture_map.belarus.service.impl

import by.architecture_map.belarus.entity.Architect
import by.architecture_map.belarus.repository.ArchitectRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class ArchitectServiceImplTest {

    private val architectRepository: ArchitectRepository = mockk();
    private val architectService = ArchitectServiceImpl(architectRepository);

    @Test
    fun whenSaveArchitect_thenSaveArchitect() {
        //given
        val architect = Architect(name = "John Doe")
        every { architectRepository.save(architect) } returns architect

        //when
        val result = architectService.create(architect)

        //then
        verify(exactly = 1) { architectRepository.save(architect) }
        assertEquals(architect, result)
    }

    @Test
    fun whenFindAll_thenReturnListOfArchitects() {
        //given
        val architects = mutableListOf(
            Architect(name = "John Doe"),
            Architect(name = "Jane Doe")
        )
        every { architectRepository.findAll() } returns architects

        //when
        val result = architectService.findAll()

        //then
        verify(exactly = 1) { architectRepository.findAll() }
        assertEquals(architects, result)
    }
}
