package by.architecture_map.belarus.service.impl

import by.architecture_map.belarus.dto.RegistrationUserDTO
import by.architecture_map.belarus.dto.UpdatePasswordDTO
import by.architecture_map.belarus.dto.UserDTO
import by.architecture_map.belarus.entity.User
import by.architecture_map.belarus.entity.EmailVerificationToken
import by.architecture_map.belarus.exception.NotFoundException
import by.architecture_map.belarus.repository.jpa.UserRepository
import by.architecture_map.belarus.service.EmailService
import by.architecture_map.belarus.service.RoleService
import by.architecture_map.belarus.service.UserService
import by.architecture_map.belarus.service.VerificationTokenService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val roleService: RoleService,
    private val emailService: EmailService,
    private val verificationTokenService: VerificationTokenService
) : UserService {

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

    override fun findAll(): List<UserDTO> =
        userRepository.findAll()
            .map { it.toDTO() }

    override fun find(id: Int): User =
        userRepository.findById(id)
            .orElseThrow { throw NotFoundException("User not found with id: $id") }

    override fun find(username: String): User? =
        userRepository.findUserByUsername(username)

    override fun update(id: Int, updatedUser: UserDTO): UserDTO =
        applyUpdates(id) {
            name = updatedUser.name
            surname = updatedUser.surname
            aboutThemself = updatedUser.aboutThemself
        }.toDTO()

    override fun updatePassword(updatePasswordDTO: UpdatePasswordDTO) {
        find(updatePasswordDTO.userId)
            .also {
                checkThatPasswordIsCorrect(it, updatePasswordDTO.oldPassword)
                checkThatPasswordHasCorrectLength(updatePasswordDTO.newPassword)
                it.password = passwordEncoder.encode(updatePasswordDTO.newPassword).toCharArray()
                userRepository.save(it)
            }
    }

    override fun patchUpdate(id: Int, updatedUser: UserDTO): UserDTO =
        applyUpdates(id) {
            if (!updatedUser.name.isNullOrEmpty())
                name = updatedUser.name
            if (!updatedUser.surname.isNullOrEmpty())
                surname = updatedUser.surname
            if (!updatedUser.aboutThemself.isNullOrEmpty())
                aboutThemself = updatedUser.aboutThemself
        }.toDTO()

    override fun delete(id: Int) {
        find(id).also { userRepository.deleteById(id) }
    }

    private fun checkThatUsernameIsUnique(username: String) {
        require(find(username) == null) { "User with email=$username already exists" }
    }

    private fun checkThatPasswordHasCorrectLength(password: String) {
        val passwordMinLength = 8
        require(password.length >= passwordMinLength) { "Password length ${password.length} is  less then minimum $passwordMinLength" }
    }

    private fun checkThatEmailHasCorrectFormat(email: String) {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$".toRegex()
        require(email.matches(emailRegex)) { "Invalid email format: $email. For example: name@example.com" }
    }

    private fun checkThatPasswordIsCorrect(user: User, password: String) {
        check(user.password.toString() == passwordEncoder.encode(password)) { "Password is incorrect!" }
    }

    private fun applyUpdates(id: Int, update: User.() -> Unit): User =
        find(id)
            .apply(update)
            .let { userRepository.save(it) }

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
