package by.architecturemap.belarus.dto

import by.architecturemap.belarus.entity.ArchitecturalAttribute
import by.architecturemap.belarus.entity.Image
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ArchitecturalStyleDTOTest {

    @Test
    fun testSettersAndGettersForArchitecturalStyleDTO() {
        // arrange
        val expectedName = "Name"
        val expectedDescription = "D"
        val expectedDemonstrativeImage = Image()
        val expectedShortDescription = "SH"
        val expectedYearsActive = "12 ст"
        val expectedAttributes = listOf(ArchitecturalAttribute(name = "Test"))

        val dto = ArchitecturalStyleDTO()

        // act
        dto.name = expectedName
        dto.description = expectedDescription
        dto.demonstrativeImage = expectedDemonstrativeImage
        dto.shortDescription = expectedShortDescription
        dto.yearsActive = expectedYearsActive
        dto.attributes = expectedAttributes

        // assert
        assertEquals(expectedName, dto.name)
        assertEquals(expectedDescription, dto.description)
        assertEquals(expectedDemonstrativeImage, dto.demonstrativeImage)
        assertEquals(expectedShortDescription, dto.shortDescription)
        assertEquals(expectedYearsActive, dto.yearsActive)
        assertEquals(expectedAttributes, dto.attributes)
    }
}
