package by.architecturemap.belarus.service.impl

import by.architecturemap.belarus.entity.Architect
import by.architecturemap.belarus.exception.NotFoundException
import by.architecturemap.belarus.repository.jpa.ArchitectRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import java.util.Optional

class ArchitectServiceImplTest {

    private val architectRepository: ArchitectRepository = mockk();
    private val architectService = ArchitectServiceImpl(architectRepository);

    private var id: Int = 1
    private lateinit var architect: Architect

    @BeforeEach
    fun setUp() {
        architect = Architect(name = "John", surname = "Haiduk", yearsOfLife = "XVI ст")
    }

    @Test
    fun givenValidArchitect_whenCreateArchitect_thenReturnSavedArchitect() {
        //arrange
        val expected = architect
        every { architectRepository.save(architect) } returns architect

        //act
        val actual = architectService.create(architect)

        //assert
        assertEquals(expected, actual)
    }

    @Test
    fun whenFindAllArchitects_thenReturnListOfArchitects() {
        //arrange
        val expected = listOf(architect, architect)
        every { architectRepository.findAll() } returns expected

        //act
        val actual = architectService.findAll()

        //assert
        verify { architectRepository.findAll() }
        assertEquals(expected, actual)
    }

    @Test
    fun givenIdOfExistedArchitect_whenFindArchitect_thenReturnArchitect() {
        //arrange
        val expected = architect
        every { architectRepository.findById(id) } returns Optional.of(architect)

        //act
        val actual = architectService.find(id)

        //assert
        assertEquals(expected, actual)
    }

    @Test
    fun givenIdOfNotExistedArchitect_whenFindArchitect_thenThrowNotFoundException() {
        //arrange
        every { architectRepository.findById(id) } returns Optional.empty()

        //act & assert
        assertThrows<NotFoundException> { architectService.find(id) }
    }
}
