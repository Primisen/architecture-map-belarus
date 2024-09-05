package by.architecture_map.belarus.service.impl

import by.architecture_map.belarus.entity.Construction
import by.architecture_map.belarus.repository.ConstructionRepository
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.data.repository.findByIdOrNull
import java.util.*

class ConstructionServiceImplTest {

    private val constructionRepository: ConstructionRepository = mockk()
    private val constructionService = ConstructionServiceImpl(constructionRepository)

    @Test
    fun whenCreateConstruction_thenReturnSavedConstruction() {
        //given
        val construction = Construction(id = 1, description = "Really beautiful")
        every { constructionRepository.save(construction) } returns construction

        //when
        val result = constructionService.create(construction)

        //then
        verify(exactly = 1) { constructionRepository.save(construction) }
        assertEquals(construction, result)
    }

    @Test
    fun whenFindAllConstruction_thenReturnListOfConstruction() {
        //given
        val constructions = mutableListOf(
                Construction(id = 1, description = "Really beautiful"),
                Construction(id = 2, description = "Beautiful")
        )
        every { constructionRepository.findAll() } returns constructions

        //when
        val result = constructionService.findAll()

        //then
        verify(exactly = 1) { constructionRepository.findAll() }
        assertEquals(constructions, result)
    }

    @Test
    fun whenFindById_thenReturnConstruction() {
        //given
        val construction = Construction(id = 1, description = "Really beautiful")
        every { constructionRepository.findByIdOrNull(1) } returns construction

        //when
        val result = constructionService.findById(1)

        //then
        verify(exactly = 1) { constructionRepository.findByIdOrNull(1) }
        assertEquals(construction, result)
    }

    @Test
    fun whenUpdateById_thenReturnUpdatedConstruction() {
        //given
        val id = 1
        val existingConstruction = Construction(id = id)
        val updatedConstruction = Construction(id = id, name = "Updated Name")
        every { constructionRepository.findById(id) } returns Optional.of(existingConstruction)
        every { constructionRepository.save(existingConstruction) } returns existingConstruction.apply {
            name = updatedConstruction.name
            description = updatedConstruction.description
            architecturalStyle = updatedConstruction.architecturalStyle
            address = updatedConstruction.address
            architects = updatedConstruction.architects
            buildingTime = updatedConstruction.buildingTime
            images = updatedConstruction.images
        }

        //when
        val result = constructionService.updateById(id, updatedConstruction)

        //then
        verify(exactly = 1) { constructionRepository.findById(id) }
        verify(exactly = 1) { constructionRepository.save(existingConstruction) }
        assertEquals(existingConstruction, result)
    }

    @Test
    fun whenPatchById_thenReturnPatchedConstruction() {
        //given
        val id = 1
        val existingConstruction = Construction(id = id)
        val updatedConstruction = Construction(id = id, name = "Updated Name")
        every { constructionRepository.findById(id) } returns Optional.of(existingConstruction)
        every { constructionRepository.save(existingConstruction) } returns existingConstruction.apply {
            name = updatedConstruction.name
            address = updatedConstruction.address
            architects = updatedConstruction.architects
            architecturalStyle = updatedConstruction.architecturalStyle
            images = updatedConstruction.images
            description = updatedConstruction.description
            buildingTime = updatedConstruction.buildingTime
        }

        //when
        val result = constructionService.patchById(id, updatedConstruction)

        //then
        verify(exactly = 1) { constructionRepository.findById(id) }
        verify(exactly = 1) { constructionRepository.save(existingConstruction) }
        assertEquals(existingConstruction, result)
    }

    @Test
    fun whenDeleteById_thenReturnTrueIfExists() {
        //given
        val id = 1
        every { constructionRepository.existsById(id) } returns true
        every { constructionRepository.deleteById(id) } just Runs

        //when
        val result = constructionService.deleteById(id)

        //then
        verify(exactly = 1) { constructionRepository.existsById(id) }
        verify(exactly = 1) { constructionRepository.deleteById(id) }
        assertTrue(result)
    }

    @Test
    fun whenDeleteById_thenReturnFalseIfNotExists() {
        //given
        val id = 1
        every { constructionRepository.existsById(id) } returns false
        every { constructionRepository.deleteById(id) } just Runs

        //when
        val result = constructionService.deleteById(id)

        //then
        verify(exactly = 1) { constructionRepository.existsById(id) }
        verify(exactly = 0) { constructionRepository.deleteById(id) }
        assertFalse(result)
    }
}