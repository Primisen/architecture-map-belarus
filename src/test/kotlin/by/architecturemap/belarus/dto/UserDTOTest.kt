package by.architecturemap.belarus.dto

import by.architecturemap.belarus.entity.Role
import by.architecturemap.belarus.entity.User
import by.architecturemap.belarus.entity.UserImage
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class UserDTOTest {

    @Test
    fun testSettersAndGettersForUserDTO() {
        // arrange
        val dto = UserDTO(username = "test@gmail.com")

        // act
        dto.name = "Name"
        dto.username = "test@gmail.com"
        dto.surname = "Surname"
        dto.aboutThemself = "Themself"
        dto.roles = setOf(Role(name = "USER"))
        dto.enable = true
        dto.images = listOf(UserImage(user = User(username = "test@gmail.com", password = "12345678".toCharArray())))

        // assert
        assertEquals("Name", dto.name)
        assertEquals("test@gmail.com", dto.username)
        assertEquals("Surname", dto.surname)
        assertEquals("Themself", dto.aboutThemself)
        assertEquals(setOf(Role(name = "USER")), dto.roles)
        assertEquals(true, dto.enable)
        assertEquals(
            listOf(UserImage(user = User(username = "test@gmail.com", password = "12345678".toCharArray()))),
            dto.images
        )
    }
}
