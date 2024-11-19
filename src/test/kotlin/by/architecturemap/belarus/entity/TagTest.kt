package by.architecturemap.belarus.entity

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TagTest {

    @Test
    fun testConstructorAndSettersAndGettersForTag() {
        // arrange
        val expectedName = "Name"
        val tag = Tag(name = expectedName)

        // act
        tag.name = expectedName

        // assert
        assertEquals(expectedName, tag.name)
    }
}
