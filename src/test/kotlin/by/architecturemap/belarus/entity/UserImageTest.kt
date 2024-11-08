package by.architecturemap.belarus.entity

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class UserImageTest {

    @Test
    fun testConstructorAndSettersAndGettersForUserImage() {
        // arrange
        val expectedUser = User(username = "name@gmail.com", password = "12345678".toCharArray())
        val userImage = UserImage(user = expectedUser)

        // act
        userImage.user = expectedUser

        // assert
        assertEquals(expectedUser, userImage.user)
    }
}
