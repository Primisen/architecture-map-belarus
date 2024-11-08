package by.architecturemap.belarus.service.impl

import by.architecturemap.belarus.dto.UpdatePasswordDTO
import by.architecturemap.belarus.dto.UserDTO
import by.architecturemap.belarus.entity.User
import by.architecturemap.belarus.exception.NotFoundException
import by.architecturemap.belarus.repository.jpa.UserRepository
import by.architecturemap.belarus.service.UserService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
open class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) : UserService {

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

    override fun delete(id: Int) {
        userRepository.deleteById(id)
    }

    private fun checkThatPasswordHasCorrectLength(password: String) {
        require(password.length >= PASSWORD_MIN_LENGTH) {
            "Password length ${password.length} is  less then minimum $PASSWORD_MIN_LENGTH"
        }
    }

    private fun checkThatPasswordIsCorrect(user: User, password: String) {
        require(user.password.joinToString("") == passwordEncoder.encode(password)) { "Password is incorrect!" }
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
