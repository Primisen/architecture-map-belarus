package by.architecturemap.belarus.entity

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ArchitecturalAttributeTest {

    @Test
    fun testSettersAndGettersForArchitecturalAttribute() {
        // arrange
        val expectedName = "Name"
        val expectedDescription = "test"
        val expectedDemonstrativeImage = Image()

        val architecturalAttribute = ArchitecturalAttribute(name = expectedName)

        // act
        architecturalAttribute.name = expectedName
        architecturalAttribute.description = expectedDescription
        architecturalAttribute.demonstrativeImage = expectedDemonstrativeImage

        // assert
        assertEquals(expectedName, architecturalAttribute.name)
        assertEquals(expectedDescription, architecturalAttribute.description)
        assertEquals(expectedDemonstrativeImage, architecturalAttribute.demonstrativeImage)
    }
}
