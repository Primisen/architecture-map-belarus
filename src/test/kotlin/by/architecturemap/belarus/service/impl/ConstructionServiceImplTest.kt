package by.architecturemap.belarus.service.impl

import by.architecturemap.belarus.dto.ConstructionDTO
import by.architecturemap.belarus.dto.ConstructionSearchingDTO
import by.architecturemap.belarus.entity.Address
import by.architecturemap.belarus.entity.Architect
import by.architecturemap.belarus.entity.ArchitecturalStyle
import by.architecturemap.belarus.entity.Construction
import by.architecturemap.belarus.entity.ConstructionImage
import by.architecturemap.belarus.exception.NotFoundException
import by.architecturemap.belarus.repository.jpa.ConstructionRepository
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import java.util.Optional
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.data.elasticsearch.core.SearchHits
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import org.springframework.data.elasticsearch.core.SearchHit
import org.springframework.data.elasticsearch.core.query.CriteriaQuery

class ConstructionServiceImplTest {

    private val constructionRepository: ConstructionRepository = mockk()
    private val elasticsearchOperations: ElasticsearchOperations = mockk()
    private val constructionService = ConstructionServiceImpl(constructionRepository, elasticsearchOperations)

    private lateinit var construction: Construction
    private lateinit var updatedConstruction: Construction
    private lateinit var constructionImage: ConstructionImage

    @BeforeEach
    fun setUp() {
        construction = Construction(
            name = "Name1",
            address = Address(region = "Test"),
            architecturalStyle = ArchitecturalStyle(name = "Style")
        )

        updatedConstruction = Construction(
            name = "Name2",
            address = Address(region = "Test2"),
            architecturalStyle = ArchitecturalStyle(name = "Style2")
        )

        constructionImage = ConstructionImage(
            construction = construction,
            takenTime = "пач. XX ст"
        )
    }

    @Test
    fun givenConstruction_whenCreate_thenReturnSavedConstruction() {
        // arrange
        val expected = construction
        every { constructionRepository.save(construction) } returns construction

        // act
        val actual = constructionService.create(construction)

        // assert
        verify(exactly = 1) { constructionRepository.save(construction) }
        assertEquals(expected, actual)
    }

    @Test
    fun whenFindAll_thenReturnListOfConstruction() {
        // arrange
        val constructions = listOf(construction, updatedConstruction)
        every { constructionRepository.findAll() } returns constructions

        // act
        val actual = constructionService.findAll()

        // assert
        verify(exactly = 1) { constructionRepository.findAll() }
        assertEquals(constructions, actual)
    }

    @Test
    fun givenConstructionId_whenFind_thenReturnConstruction() {
        // arrange
        val id = 1
        val expected = construction
        every { constructionRepository.findById(id) } returns Optional.of(construction)

        // act
        val actual = constructionService.find(id)

        // assert
        verify(exactly = 1) { constructionRepository.findById(id) }
        assertEquals(expected, actual)
    }

    @Test
    fun givenConstructionId_whenUpdate_thenReturnUpdatedConstruction() {
        // arrange
        val id = 1
        val expected = updatedConstruction
        every { constructionRepository.findById(id) } returns Optional.of(construction)
        every { constructionRepository.save(construction) } returns construction

        // act
        val actual = constructionService.update(id, updatedConstruction)

        // assert
        verify(exactly = 1) { constructionRepository.findById(id) }
        verify(exactly = 1) { constructionRepository.save(construction) }
        assertEquals(expected, actual)
    }

    @Test
    fun givenConstructionDTO_whenPatchUpdate_thenReturnPatchedConstruction() {
        // arrange
        val id = 1
        val updatedDTO = ConstructionDTO(
            name = "Name 2",
            address = Address(region = "Test 2"),
            architecturalStyle = ArchitecturalStyle(name = "Another style"),
            description = "New description",
            buildingDate = "Пач XI ст",
            buildingCentury = 11,
            images = listOf(constructionImage),
            architects = listOf(Architect(name = "Architect", surname = "Surname", yearsOfLife = "XX ст"))
        )

        every { constructionRepository.findById(id) } returns Optional.of(construction)
        every { constructionRepository.save(construction) } returns construction

        // act
        val actual = constructionService.patchUpdate(id, updatedDTO)

        // assert
        val expected = Construction(
            name = "Name 2",
            address = Address(region = "Test 2"),
            architecturalStyle = ArchitecturalStyle(name = "Another style"),
            description = "New description",
            buildingDate = "Пач XI ст",
            buildingCentury = 11,
            images = listOf(constructionImage),
            architects = listOf(Architect(name = "Architect", surname = "Surname", yearsOfLife = "XX ст"))
        )

        assertEquals(expected, actual)
    }

    @Test
    fun givenConstructionDTOWithNullFields_whenPatchUpdate_thenReturnOriginalConstruction() {
        // arrange
        val id = 1
        val updatedDTO = ConstructionDTO(
            name = null,
            address = null,
            architecturalStyle = null,
            description = null,
            buildingDate = null,
            buildingCentury = null
        )

        every { constructionRepository.findById(id) } returns Optional.of(construction)
        every { constructionRepository.save(construction) } returns construction

        // act
        val actual = constructionService.patchUpdate(id, updatedDTO)

        // assert
        val expected = construction
        verify(exactly = 1) { constructionRepository.findById(id) }
        verify(exactly = 1) { constructionRepository.save(construction) }
        assertEquals(expected, actual)
    }

    @Test
    fun givenConstructionDTOWithBlankOrEmptyFields_whenPatchUpdate_thenReturnOriginalConstruction() {
        // arrange
        val id = 1
        val updatedDTO = ConstructionDTO(
            name = "",
            address = null,
            architecturalStyle = null,
            description = "",
            buildingDate = "",
            buildingCentury = 0,
            images = emptyList(),
            architects = emptyList()
        )

        every { constructionRepository.findById(id) } returns Optional.of(construction)
        every { constructionRepository.save(construction) } returns construction

        // act
        val actual = constructionService.patchUpdate(id, updatedDTO)

        // assert
        val expected = construction
        verify(exactly = 1) { constructionRepository.findById(id) }
        verify(exactly = 1) { constructionRepository.save(construction) }
        assertEquals(expected, actual)
    }

    @Test
    fun givenConstructionDTOWithHighBuildingCentury_whenPatchUpdate_thenReturnOriginalConstruction() {
        // arrange
        val id = 1
        val updatedDTO = ConstructionDTO(
            buildingCentury = 222
        )

        every { constructionRepository.findById(id) } returns Optional.of(construction)
        every { constructionRepository.save(construction) } returns construction

        // act
        val actual = constructionService.patchUpdate(id, updatedDTO)

        // assert
        val expected = construction
        verify(exactly = 1) { constructionRepository.findById(id) }
        verify(exactly = 1) { constructionRepository.save(construction) }
        assertEquals(expected, actual)
    }

    @Test
    fun givenConstructionId_whenDelete_thenVerifyDeletion() {
        // arrange
        val id = 1
        every { constructionRepository.findById(id) } returns Optional.of(construction)
        every { constructionRepository.deleteById(id) } just Runs

        // act
        constructionService.delete(id)

        // assert
        verify(exactly = 1) { constructionRepository.findById(id) }
        verify(exactly = 1) { constructionRepository.deleteById(id) }
    }

    @Test
    fun givenInvalidConstructionId_whenDelete_thenThrowNotFoundException() {
        // arrange
        val id = 1
        every { constructionRepository.findById(id) } returns Optional.empty()

        // act & assert
        assertThrows<NotFoundException> { constructionService.delete(id) }

        // verify
        verify(exactly = 1) { constructionRepository.findById(id) }
        verify(exactly = 0) { constructionRepository.deleteById(id) }
    }

    @Test
    fun givenSearchDTO_whenFind_thenReturnSearchResults() {
        // arrange
        val constructionSearchingDTO = ConstructionSearchingDTO(
            architecturalStyleId = "123",
            region = "Віцебская вобласць",
            district = "Мёрскі раён",
            buildingCenturyFrom = "13",
            buildingCenturyTo = "16"
        )
        val expectedConstruction = construction

        val mockSearchHits: SearchHits<Construction> = mockk()
        val mockSearchHit: SearchHit<Construction> = mockk()

        every { mockSearchHit.content } returns expectedConstruction
        every { mockSearchHits.searchHits } returns listOf(mockSearchHit)
        every { elasticsearchOperations.search(any<CriteriaQuery>(), Construction::class.java) } returns mockSearchHits

        // act
        val actual = constructionService.find(constructionSearchingDTO)

        // assert
        assertNotNull(actual)
        assertEquals(expectedConstruction, actual[0])
    }

    @Test
    fun givenEmptyFieldsInSearchDTO_whenFind_thenReturnEmptyList() {
        // arrange
        val constructionSearchingDTO = ConstructionSearchingDTO(
            architecturalStyleId = "",
            region = "",
            district = "",
            buildingCenturyFrom = "",
            buildingCenturyTo = ""
        )

        val mockSearchHits: SearchHits<Construction> = mockk()

        every { mockSearchHits.searchHits } returns emptyList()
        every { elasticsearchOperations.search(any<CriteriaQuery>(), Construction::class.java) } returns mockSearchHits

        // act
        val actual = constructionService.find(constructionSearchingDTO)

        // assert
        assertNotNull(actual)
        assertTrue(actual.isEmpty())
    }

    @Test
    fun givenNullFieldsInSearchDTO_whenFind_thenReturnEmptyList() {
        // arrange
        val constructionSearchingDTO = ConstructionSearchingDTO(
            architecturalStyleId = null,
            region = null,
            district = null,
            buildingCenturyFrom = null,
            buildingCenturyTo = null
        )

        val mockSearchHits: SearchHits<Construction> = mockk()

        every { mockSearchHits.searchHits } returns emptyList()
        every { elasticsearchOperations.search(any<CriteriaQuery>(), Construction::class.java) } returns mockSearchHits

        // act
        val actual = constructionService.find(constructionSearchingDTO)

        // assert
        assertNotNull(actual)
        assertTrue(actual.isEmpty())
    }
}
