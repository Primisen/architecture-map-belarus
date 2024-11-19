package by.architecturemap.belarus.dto

import by.architecturemap.belarus.entity.Address
import by.architecturemap.belarus.entity.Architect
import by.architecturemap.belarus.entity.ArchitecturalStyle
import by.architecturemap.belarus.entity.ConstructionImage
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ConstructionDTOTest {

    @Test
    fun testSettersAndGettersForConstructionDTO() {
        // arrange
        val expectedName = "Name"
        val expectedDescription = "Description"
        val expectedArchitecturalStyle = ArchitecturalStyle(name = "Test")
        val expectedAddress = Address(locality = "Test", region = "Test")
        val expectedArchitects = listOf(Architect(name = "Test", surname = "Test", yearsOfLife = "Test"))
        val expectedBuildingDate = "Test"
        val expectedBuildingCentury : Short = 12
        val expectedImages = listOf(ConstructionImage())

        val dto = ConstructionDTO()

        // act
        dto.name = expectedName
        dto.description = expectedDescription
        dto.architecturalStyle = expectedArchitecturalStyle
        dto.address = expectedAddress
        dto.architects = expectedArchitects
        dto.buildingDate = expectedBuildingDate
        dto.buildingCentury = expectedBuildingCentury
        dto.images = expectedImages

        // assert
        assertEquals(expectedName, dto.name)
        assertEquals(expectedDescription, dto.description)
        assertEquals(expectedArchitecturalStyle, dto.architecturalStyle)
        assertEquals(expectedAddress, dto.address)
        assertEquals(expectedArchitects, dto.architects)
        assertEquals(expectedBuildingDate, dto.buildingDate)
        assertEquals(expectedBuildingCentury, dto.buildingCentury)
        assertEquals(expectedImages, dto.images)
    }
}
