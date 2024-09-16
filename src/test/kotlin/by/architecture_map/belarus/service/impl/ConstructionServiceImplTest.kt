package by.architecture_map.belarus.service.impl

import by.architecture_map.belarus.entity.Construction
import by.architecture_map.belarus.exception.NotFoundException
import by.architecture_map.belarus.repository.jpa.ConstructionRepository
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.util.*

class ConstructionServiceImplTest {

    private val constructionRepository: ConstructionRepository = mockk()
    private val constructionService = ConstructionServiceImpl(constructionRepository)

    @Test
    fun whenCreateConstruction_thenSaveConstruction() {
        //given
        val construction = Construction(description = "Really beautiful")
            .apply { id = 1 }

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
            Construction(description = "Really beautiful")
                .apply { id = 1 },
            Construction(description = "Beautiful")
                .apply { id = 2 }
        )
        every { constructionRepository.findAll() } returns constructions

        //when
        val result = constructionService.findAll()

        //then
        verify(exactly = 1) { constructionRepository.findAll() }
        assertEquals(constructions, result)
    }

    @Test
    fun whenFind_thenReturnConstruction() {
        //given
        var id = 1
        val construction = Construction(description = "Really beautiful")
            .apply { id = id }
        every { constructionRepository.findById(id) } returns Optional.of(construction)

        //when
        val result = constructionService.find(id)

        //then
        verify(exactly = 1) { constructionRepository.findById(id) }
        assertEquals(construction, result)
    }

    @Test
    fun whenUpdate_thenUpdateConstruction() {
        //given
        var id = 1
        val existingConstruction = Construction()
            .apply { id = id }
        val updatedConstruction = Construction(name = "Updated Name")
            .apply { id = id }
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
        val result = constructionService.update(id, updatedConstruction)

        //then
        verify(exactly = 1) { constructionRepository.findById(id) }
        verify(exactly = 1) { constructionRepository.save(existingConstruction) }
        assertEquals(existingConstruction, result)
    }

    @Test
    fun whenPatch_thenPatcheConstruction() {
        //given
        var id = 1
        val existingConstruction = Construction()
            .apply { id = id }
        val updatedConstruction = Construction(name = "Updated Name")
            .apply { id = id }
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
        val result = constructionService.patchUpdate(id, updatedConstruction)

        //then
        verify(exactly = 1) { constructionRepository.findById(id) }
        verify(exactly = 1) { constructionRepository.save(existingConstruction) }
        assertEquals(existingConstruction, result)
    }

    @Test
    fun whenDelete_thenDeleteConstruction() {
        //given
        val id = 1
        every { constructionRepository.existsById(id) } returns true
        every { constructionRepository.deleteById(id) } just Runs

        //when
        val result = constructionService.delete(id)

        //then
        verify(exactly = 1) { constructionRepository.existsById(id) }
        verify(exactly = 1) { constructionRepository.deleteById(id) }
    }

    @Test
    fun whenDeleteConstructionAndConstructionDoesNotExists_thenThrowNotFoundException() {
        //given
        val id = 1
        every { constructionRepository.existsById(id) } returns false
        every { constructionRepository.deleteById(id) } just Runs

        //when & then
        assertThrows(NotFoundException::class.java) {
            constructionService.delete(id)
        }

        //verify
        verify(exactly = 1) { constructionRepository.existsById(id) }
        verify(exactly = 0) { constructionRepository.deleteById(id) }
    }
}
