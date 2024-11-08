package by.architecturemap.belarus.service.impl

import by.architecturemap.belarus.dto.RegistrationUserDTO
import by.architecturemap.belarus.entity.EmailVerificationToken
import by.architecturemap.belarus.entity.Role
import by.architecturemap.belarus.entity.User
import by.architecturemap.belarus.repository.jpa.UserRepository
import by.architecturemap.belarus.service.EmailService
import by.architecturemap.belarus.service.RoleService
import by.architecturemap.belarus.service.VerificationTokenService
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.security.crypto.password.PasswordEncoder
import java.lang.IllegalArgumentException

class RegisterServiceImplTest {

    private val userRepository: UserRepository = mockk()
    private val passwordEncoder: PasswordEncoder = mockk()
    private val roleService: RoleService = mockk()
    private val emailService: EmailService = mockk()
    private val verificationTokenService: VerificationTokenService = mockk()

    private val registerServiceImpl = RegisterServiceImpl(
        userRepository = userRepository,
        passwordEncoder = passwordEncoder,
        roleService = roleService,
        emailService = emailService,
        verificationTokenService = verificationTokenService
    )

    private val username = "username@test.com"
    private val password = "12345678"
    private val token = "dhjfgkdh"
    private lateinit var registrationUserDTO: RegistrationUserDTO
    private lateinit var user: User

    @BeforeEach
    fun setUp() {
        registrationUserDTO = RegistrationUserDTO(username, password)
        user = User(username = username, password = password.toCharArray())
    }

    @Test
    fun givenValidRegistrationUserDTO_WhenRegister_ThenSaveUserAndReturnIt() {
        // arrange
        val emailVerificationToken = EmailVerificationToken(token, user)

        every { userRepository.findUserByUsername(username) } returns null
        every { passwordEncoder.encode(registrationUserDTO.password) } returns password
        every { roleService.find("USER") } returns Role(name = "USER")
        every { userRepository.save(any()) } returns user
        every { verificationTokenService.save(any()) } returns emailVerificationToken
        every { emailService.sendVerificationTokenToEmail(any(), any()) } returns Unit

        // act
        val actual = registerServiceImpl.register(registrationUserDTO)

        // assert
        assertEquals(username, actual.username)
    }

    @Test
    fun givenRegistrationUserDTOWithInvalidEmailFormat_WhenRegister_ThenThrowIllegalArgumentException() {
        // arrange
        val username = "username.com"
        val registrationUserDTO = RegistrationUserDTO(username, password)

        every { userRepository.findUserByUsername(username) } returns null

        // act & assert
        assertThrows<IllegalArgumentException> { registerServiceImpl.register(registrationUserDTO) }
    }

    @Test
    fun givenNonUniqueUsernameInRegistrationUserDTO_WhenRegister_ThenThrowIllegalArgumentException() {
        // arrange
        every { userRepository.findUserByUsername(username) } returns user

        // act & assert
        assertThrows<IllegalArgumentException> { registerServiceImpl.register(registrationUserDTO) }
    }

    @Test
    fun givenRegistrationUserDTOWithShortPassword_WhenRegister_ThenThrowIllegalArgumentException() {
        // arrange
        val password = "1234"
        val registrationUserDTO = RegistrationUserDTO(username, password)

        every { userRepository.findUserByUsername(username) } returns null

        // act & assert
        assertThrows<IllegalArgumentException> { registerServiceImpl.register(registrationUserDTO) }
    }

    @Test
    fun givenToken_WhenConfirmEmail_ThenEnableUser() {
        // arrange
        val emailVerificationToken = EmailVerificationToken(token, user)

        every { verificationTokenService.getToken(token) } returns emailVerificationToken
        every { userRepository.save(user) } returns user

        // act
        registerServiceImpl.confirmEmail(token)

        // assert
        assertEquals(true, user.enable)
    }

    @Test
    fun givenUsername_WhenFindUser_ThenReturnUser() {
        // arrange
        every { userRepository.findUserByUsername(username) } returns user

        // act
        val actualUser = registerServiceImpl.find(username)

        // assert
        assertEquals(user, actualUser)
    }
}
