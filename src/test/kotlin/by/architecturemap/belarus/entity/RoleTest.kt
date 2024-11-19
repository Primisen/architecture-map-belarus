package by.architecturemap.belarus.entity

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RoleTest {

    @Test
    fun testConstructorAndGettersAndSettersForRole() {
        // arrange
        val expectedName = "Name"
        val role = Role(name = expectedName)

        // act
        role.name = expectedName

        // assert
        assertEquals(expectedName, role.name)
    }
}
