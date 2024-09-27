package by.architecturemap.belarus.service.impl

import by.architecturemap.belarus.entity.Address
import by.architecturemap.belarus.entity.ArchitecturalStyle
import by.architecturemap.belarus.entity.Construction
import by.architecturemap.belarus.exception.NotFoundException
import by.architecturemap.belarus.repository.jpa.ConstructionRepository
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import java.util.Optional
import org.springframework.data.elasticsearch.core.ElasticsearchOperations

class ConstructionServiceImplTest {

    private val constructionRepository: ConstructionRepository = mockk()
    private val elasticsearchOperations: ElasticsearchOperations = mockk()
    private val constructionService = ConstructionServiceImpl(constructionRepository, elasticsearchOperations)

    @Test
    fun whenCreateConstruction_thenSaveConstruction() {
        //given
        val construction = Construction(
            name = "Name2",
            address = Address(region = "Test"),
            architecturalStyle = ArchitecturalStyle(name = "Style")
        )
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
            Construction(
                name = "Name1",
                address = Address(region = "Test"),
                architecturalStyle = ArchitecturalStyle(name = "Style")
            )
                .apply { id = 1 },
            Construction(
                name = "Name2",
                address = Address(region = "Test"),
                architecturalStyle = ArchitecturalStyle(name = "Style")
            )
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
        val construction = Construction(
            name = "Name1",
            address = Address(region = "Test"),
            architecturalStyle = ArchitecturalStyle(name = "Style")
        )
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
        val existingConstruction = Construction(
            name = "Name1",
            address = Address(region = "Test"),
            architecturalStyle = ArchitecturalStyle(name = "Style")
        )
            .apply { id = id }
        val updatedConstruction = Construction(
            name = "Name2",
            address = Address(region = "Test2"),
            architecturalStyle = ArchitecturalStyle(name = "Style2")
        )
            .apply { id = id }
        every { constructionRepository.findById(id) } returns Optional.of(existingConstruction)
        every { constructionRepository.save(existingConstruction) } returns existingConstruction.apply {
            name = updatedConstruction.name
            description = updatedConstruction.description
            architecturalStyle = updatedConstruction.architecturalStyle
            address = updatedConstruction.address
            architects = updatedConstruction.architects
            buildingDate = updatedConstruction.buildingDate
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
        val existingConstruction = Construction(
            name = "Name",
            address = Address(region = "Test"),
            architecturalStyle = ArchitecturalStyle(name = "Style")
        )
            .apply { id = id }
        val updatedConstruction = Construction(
            name = "Name 2",
            address = Address(region = "Test 2"),
            architecturalStyle = ArchitecturalStyle(name = "Another style")
        )
            .apply { id = id }
        every { constructionRepository.findById(id) } returns Optional.of(existingConstruction)
        every { constructionRepository.save(existingConstruction) } returns existingConstruction.apply {
            name = updatedConstruction.name
            address = updatedConstruction.address
            architects = updatedConstruction.architects
            architecturalStyle = updatedConstruction.architecturalStyle
            images = updatedConstruction.images
            description = updatedConstruction.description
            buildingDate = updatedConstruction.buildingDate
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
        val construction = mockk<Construction>()
        every { constructionRepository.findById(id) } returns Optional.of(construction)
        every { constructionRepository.deleteById(id) } just Runs

        //when
        val result = constructionService.delete(id)

        //then
        verify(exactly = 1) { constructionRepository.findById(id) }
        verify(exactly = 1) { constructionRepository.deleteById(id) }
    }

    @Test
    fun whenDeleteConstructionAndConstructionDoesNotExists_thenThrowNotFoundException() {
        //given
        val id = 1
        every { constructionRepository.findById(id) } returns Optional.empty()

        //when & then
        assertThrows(NotFoundException::class.java) {
            constructionService.delete(id)
        }

        //verify
        verify(exactly = 1) { constructionRepository.findById(id) }
        verify(exactly = 0) { constructionRepository.deleteById(id) }
    }
}
