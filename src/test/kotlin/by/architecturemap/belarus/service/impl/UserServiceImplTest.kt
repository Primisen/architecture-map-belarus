package by.architecturemap.belarus.service.impl

import by.architecturemap.belarus.dto.UpdatePasswordDTO
import by.architecturemap.belarus.dto.UserDTO
import by.architecturemap.belarus.entity.User
import by.architecturemap.belarus.exception.NotFoundException
import by.architecturemap.belarus.repository.jpa.UserRepository
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows
import org.springframework.security.crypto.password.PasswordEncoder
import java.lang.IllegalArgumentException
import java.util.Optional

class UserServiceImplTest {

    private val userRepository: UserRepository = mockk()
    private val passwordEncoder: PasswordEncoder = mockk()
    private val userServiceImpl = UserServiceImpl(userRepository, passwordEncoder)

    private lateinit var user: User
    private lateinit var updatedUser: User
    private lateinit var userDTO: UserDTO

    @BeforeEach
    fun setUp() {
        user = User(username = "test@test.com", password = CharArray(8), enable = true)
        updatedUser = User(username = "updated@test.com", password = CharArray(8) { i -> 'B' + i }, enable = true)
        userDTO = UserDTO(username = "test@test.com", enable = true)
    }

    @Test
    fun givenUsers_whenFindAll_thenReturnListOfUserDTO() {
        // arrange
        val users = listOf(user)
        val expected = listOf(
            UserDTO(username = user.username, enable = user.enable)
        )
        every { userRepository.findAll() } returns users

        // act
        val actual = userServiceImpl.findAll()

        // assert
        assertEquals(expected, actual)
        verify(exactly = 1) { userRepository.findAll() }
    }

    @Test
    fun givenUserId_whenFind_thenReturnUser() {
        // arrange
        val id = 1
        val expected = user
        every { userRepository.findById(id) } returns Optional.of(user)

        // act
        val actual = userServiceImpl.find(id)

        // assert
        assertEquals(expected, actual)
        verify(exactly = 1) { userRepository.findById(id) }
    }

    @Test
    fun givenUpdatePasswordDTO_whenUpdatePassword_thenSaveUpdatedUser() {

        //arrange
        val id = 1
        val oldPassword = "12345678"
        val newPassword = "87654321"
        val user = User(username = "user@gmial.com", password = oldPassword.toCharArray())
        val updatePasswordDTO = UpdatePasswordDTO(userId = id, oldPassword = oldPassword, newPassword = newPassword)
        every { userRepository.findById(id) } returns Optional.of(user)
        every { passwordEncoder.encode(oldPassword) } returns oldPassword
        every { passwordEncoder.encode(newPassword) } returns newPassword
        every { userRepository.save(user) } returns user

        //act
        val actual = userServiceImpl.updatePassword(updatePasswordDTO)

        //assert
        verify(exactly = 1) { userRepository.save(user) }
    }

    @Test
    fun givenInvalidUserId_whenFind_thenThrowNotFoundException() {
        // arrange
        val id = 1
        every { userRepository.findById(id) } returns Optional.empty()

        // act & assert
        assertThrows<NotFoundException> { userServiceImpl.find(id) }
        verify(exactly = 1) { userRepository.findById(id) }
    }

    @Test
    fun givenUsername_whenFind_thenReturnUser() {
        // arrange
        val username = "test@test.com"
        val expected = user
        every { userRepository.findUserByUsername(username) } returns user

        // act
        val actual = userServiceImpl.find(username)

        // assert
        assertEquals(expected, actual)
        verify(exactly = 1) { userRepository.findUserByUsername(username) }
    }

    @Test
    fun givenUpdatedUser_whenUpdateUser_thenReturnUpdatedUser() {

        //given
        val id = 1
        val username = "user@exampple.com"
        val user = User(username = username, password = CharArray(8) { i -> 'A' + i }, enable = true)
        val updatedUser = UserDTO(username = username, name = "Igar", images = listOf())
        val expected = UserDTO(
            username = updatedUser.username,
            name = updatedUser.name,
            enable = user.enable,
            images = updatedUser.images
        )
        every { userRepository.findById(id) } returns Optional.of(user)
        every { userRepository.save(user) } returns user

        //when
        val actual = userServiceImpl.update(id, updatedUser)

        //then
        assertEquals(expected, actual)
    }

    @Test
    fun givenInvalidPasswordLength_whenUpdatePassword_thenThrowIllegalArgumentException() {
        // arrange
        val id = 1
        val oldPassword = "12345678"
        val newPassword = "1234" // Invalid length
        val updatePasswordDTO = UpdatePasswordDTO(userId = id, oldPassword = oldPassword, newPassword = newPassword)

        every { userRepository.findById(id) } returns Optional.of(user)
        every { passwordEncoder.encode(oldPassword) } returns oldPassword
        every { passwordEncoder.encode(newPassword) } returns newPassword

        // act & assert
        assertThrows<IllegalArgumentException> { userServiceImpl.updatePassword(updatePasswordDTO) }
        verify(exactly = 1) { userRepository.findById(id) }
    }

    @Test
    fun givenIncorrectOldPassword_whenUpdatePassword_thenThrowIllegalArgumentException() {
        // arrange
        val id = 1
        val incorrectOldPassword = "wrongpassword"
        val newPassword = "87654321"
        val updatePasswordDTO = UpdatePasswordDTO(
            userId = id,
            oldPassword = incorrectOldPassword,
            newPassword = newPassword
        )

        every { userRepository.findById(id) } returns Optional.of(user)
        every { passwordEncoder.encode(user.password.toString()) } returns user.password.toString()
        every { passwordEncoder.encode(incorrectOldPassword) } returns incorrectOldPassword
        every { passwordEncoder.encode(newPassword) } returns newPassword
        every { userRepository.save(user) } returns user

        // act & assert
        assertThrows<IllegalArgumentException> { userServiceImpl.updatePassword(updatePasswordDTO) }
        verify(exactly = 1) { userRepository.findById(id) }
    }

    @Test
    fun givenUserId_whenDeleteUser_thenVerifyDeletion() {
        // arrange
        val id = 1
        every { userRepository.deleteById(id) } just Runs

        // act
        userServiceImpl.delete(id)

        // assert
        verify(exactly = 1) { userRepository.deleteById(id) }
    }
}
