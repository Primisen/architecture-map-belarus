package by.architecturemap.belarus.service.impl

import by.architecturemap.belarus.dto.RegistrationUserDTO
import by.architecturemap.belarus.dto.UserDTO
import by.architecturemap.belarus.entity.EmailVerificationToken
import by.architecturemap.belarus.entity.User
import by.architecturemap.belarus.repository.jpa.UserRepository
import by.architecturemap.belarus.service.EmailService
import by.architecturemap.belarus.service.RegisterService
import by.architecturemap.belarus.service.RoleService
import by.architecturemap.belarus.service.VerificationTokenService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

const val PASSWORD_MIN_LENGTH:Int = 8

@Service
open class RegisterServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val roleService: RoleService,
    private val emailService: EmailService,
    private val verificationTokenService: VerificationTokenService
) : RegisterService{

    @Transactional(rollbackFor = [Exception::class])
    override fun register(registrationUserDTO: RegistrationUserDTO): UserDTO =
        registrationUserDTO
            .let {
                checkThatUsernameIsUnique(it.username)
                checkThatEmailHasCorrectFormat(it.username)
                checkThatPasswordHasCorrectLength(it.password)
            }
            .run {
                val encodedPassword = passwordEncoder.encode(registrationUserDTO.password)
                User(
                    username = registrationUserDTO.username,
                    enable = false,
                    password = encodedPassword.toCharArray(),
                    roles = setOf(roleService.find("USER")),
                )
            }
            .let {
                val savedUser = userRepository.save(it)

                EmailVerificationToken(user = savedUser)
                    .also {
                        verificationTokenService.save(it)
                        emailService.sendVerificationEmail(savedUser, it.token)
                    }

                savedUser.toDTO()
            }

    override fun confirmEmail(token: String) {
        token
            .let {
                verificationTokenService.checkThatTokenExistsAndNotExpired(token)
            }
            .run {
                val user = this.user
                user.enable = true
                userRepository.save(user)
            }
    }

    override fun find(username: String): User? =
        userRepository.findUserByUsername(username)

    private fun checkThatUsernameIsUnique(username: String) {
        require(find(username) == null) { "User with email=$username already exists" }
    }

    private fun checkThatPasswordHasCorrectLength(password: String) {
        require(password.length >= PASSWORD_MIN_LENGTH) {
            "Password length ${password.length} is  less then minimum $PASSWORD_MIN_LENGTH"
        }
    }

    private fun checkThatEmailHasCorrectFormat(email: String) {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$".toRegex()
        require(email.matches(emailRegex)) { "Invalid email format: $email. For example: name@example.com" }
    }

    private fun User.toDTO(): UserDTO =
        UserDTO(
            username = this.username,
            enable = this.enable,
            name = this.name,
            surname = this.surname,
            aboutThemself = this.aboutThemself,
            roles = this.roles,
            images = this.images
        )
}
