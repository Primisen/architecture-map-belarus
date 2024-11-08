package by.architecturemap.belarus.dto

import by.architecturemap.belarus.entity.Image
import by.architecturemap.belarus.entity.Tag
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ArticleDTOTest  {

    @Test
    fun testSettersAndGettersForArticleDTO() {
        // arrange
        val expectedTitle = "T"
        val expectedContent = "C"
        val expectedDemonstrativeImage = Image()
        val expectedShortDescription = "SH"
        val expectedTag = listOf(Tag(name = "Test"))

        val dto = ArticleDTO()

        // act
        dto.title = expectedTitle
        dto.content = expectedContent
        dto.demonstrativeImage = expectedDemonstrativeImage
        dto.shortDescription = expectedShortDescription
        dto.tag = expectedTag

        // assert
        assertEquals(expectedTitle, dto.title)
        assertEquals(expectedContent, dto.content)
        assertEquals(expectedDemonstrativeImage, dto.demonstrativeImage)
        assertEquals(expectedShortDescription, dto.shortDescription)
        assertEquals(expectedTag, dto.tag)
    }
}
