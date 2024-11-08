package by.architecturemap.belarus.entity

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ArchitectTest {

    @Test
    fun testSettersAndGettersForArchitect() {
        // arrange
        val expectedName = "Name"
        val expectedMiddleName = "Middle name"
        val expectedSurname = "Surname"
        val expectedYearsOfLife = "test"
        val expectedShortWorkDescription = "test"
        val expectedBiographicalArticle = "test"
        val expectedPortraitImage = Image()
        val expectedConstruction = listOf(
            Construction(
                name = "test",
                architecturalStyle = ArchitecturalStyle(name = "test"),
                address = Address(region = "test")
            )
        )

        val architect = Architect(name = expectedName, surname = expectedSurname, yearsOfLife = expectedYearsOfLife)

        // act
        architect.name = expectedName
        architect.middleName = expectedMiddleName
        architect.surname = expectedSurname
        architect.yearsOfLife = expectedYearsOfLife
        architect.shortWorkDescription = expectedShortWorkDescription
        architect.biographicalArticle = expectedBiographicalArticle
        architect.portraitImage = expectedPortraitImage
        architect.construction = expectedConstruction

        // assert
        assertEquals(expectedName, architect.name)
        assertEquals(expectedMiddleName, architect.middleName)
        assertEquals(expectedSurname, architect.surname)
        assertEquals(expectedYearsOfLife, architect.yearsOfLife)
        assertEquals(expectedShortWorkDescription, architect.shortWorkDescription)
        assertEquals(expectedBiographicalArticle, architect.biographicalArticle)
        assertEquals(expectedPortraitImage, architect.portraitImage)
        assertEquals(expectedConstruction, architect.construction)
    }
}
