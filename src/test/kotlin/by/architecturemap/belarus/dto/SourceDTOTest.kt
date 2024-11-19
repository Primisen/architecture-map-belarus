package by.architecturemap.belarus.dto

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SourceDTOTest {

    @Test
    fun testSettersAndGettersForSourceDTO() {
        // arrange
        val dto = SourceDTO()

        // act
        dto.name = "T"
        dto.url = "C"
        dto.description = "SH"

        // assert
        assertEquals("T", dto.name)
        assertEquals("C", dto.url)
        assertEquals("SH", dto.description)
    }
}
