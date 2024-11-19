package by.architecturemap.belarus.service.impl

import by.architecturemap.belarus.entity.Role
import by.architecturemap.belarus.entity.User
import by.architecturemap.belarus.exception.NotFoundException
import by.architecturemap.belarus.repository.jpa.UserRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CustomUserDetailsServiceTest {

    private val userRepository: UserRepository = mockk()
    private val userDetailsService = CustomUserDetailsService(userRepository)

    private val username = "test@gmail.com"
    private val password = "12345678".toCharArray()
    private val enable = true
    private lateinit var userRole: Set<Role>
    private lateinit var user: User

    @BeforeEach
    fun setUp() {
        userRole = setOf(Role(name = "USER"))
        user = User(username = username, password = password, roles = userRole, enable = enable)
    }

    @Test
    fun givenExistingEnabledUser_whenLoadUserByUsername_thenReturnUserDetails() {
        // arrange
        every { userRepository.findUserByUsername(username) } returns user

        // act
        val userDetails = userDetailsService.loadUserByUsername(username)

        // assert
        assertNotNull(userDetails)
        assertEquals(username, userDetails.username)

        // verify
        verify { userRepository.findUserByUsername(username) }
    }

    @Test
    fun givenExistingDisabledUser_whenLoadUserByUsername_thenThrowIllegalStateException() {
        // arrange
        val enable = false
        val user = User(username = username, password = password, roles = userRole, enable = enable)

        every { userRepository.findUserByUsername(username) } returns user

        // act & assert
        assertThrows<IllegalStateException> { userDetailsService.loadUserByUsername(username) }
    }

    @Test
    fun givenNonExistingUser_whenLoadUserByUsername_thenThrowNotFoundException() {
        // arrange
        every { userRepository.findUserByUsername(username) } returns null

        // act & assert
        assertThrows<NotFoundException> { userDetailsService.loadUserByUsername(username) }
    }
}
