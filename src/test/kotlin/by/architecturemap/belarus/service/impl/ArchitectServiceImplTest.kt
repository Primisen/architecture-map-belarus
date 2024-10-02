package by.architecturemap.belarus.service.impl

import by.architecturemap.belarus.entity.Architect
import by.architecturemap.belarus.repository.jpa.ArchitectRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class ArchitectServiceImplTest {

    private val architectRepository: ArchitectRepository = mockk();
    private val architectService = ArchitectServiceImpl(architectRepository);

    @Test
    fun whenSaveArchitect_thenSaveArchitect() {
        //given
        val architect = Architect(name = "John Doe", surname = "Haiduk", yearsOfLife = "XVI ст")
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
            Architect(name = "John Doe", surname = "Haiduk", yearsOfLife = "XVI ст"),
            Architect(name = "Jane Doe2", surname = "Shokel", yearsOfLife = "XVI ст")
        )
        every { architectRepository.findAll() } returns architects

        //when
        val result = architectService.findAll()

        //then
        verify(exactly = 1) { architectRepository.findAll() }
        assertEquals(architects, result)
    }
}
