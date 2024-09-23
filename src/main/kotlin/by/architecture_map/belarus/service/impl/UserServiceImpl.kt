package by.architecture_map.belarus.service.impl

import by.architecture_map.belarus.dto.UserDTO
import by.architecture_map.belarus.entity.User
import by.architecture_map.belarus.exception.NotFoundException
import by.architecture_map.belarus.repository.jpa.UserRepository
import by.architecture_map.belarus.service.RoleService
import by.architecture_map.belarus.service.UserService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val roleService: RoleService
) : UserService {

    override fun create(userDTO: UserDTO): User =
        userDTO.username
            .let { checkIfUserExists(it) }
            .run {
                val encodedPassword = passwordEncoder.encode(userDTO.password)
                User(
                    username = userDTO.username,
                    password = encodedPassword.toCharArray(),
                    roles = setOf(roleService.find("USER"))
                )
            }
            .let { userRepository.save(it) }

    override fun findAll(): List<User> =
        userRepository.findAll()

    override fun find(id: Int): User =
        userRepository.findById(id)
            .orElseThrow { throw NotFoundException("User not found with id: $id") }

    override fun find(username: String): User? =
        userRepository.findUserByUsername(username)

    override fun update(id: Int, updatedUser: User): User =
        applyUpdates(id) {
            name = updatedUser.name
            surname = updatedUser.surname
            aboutThemself = updatedUser.aboutThemself
        }

    override fun patchUpdate(id: Int, updatedUser: User): User =
        applyUpdates(id) {
            if (!updatedUser.name.isNullOrEmpty())
                name = updatedUser.name
            if (!updatedUser.surname.isNullOrEmpty())
                surname = updatedUser.surname
            if (!updatedUser.aboutThemself.isNullOrEmpty())
                aboutThemself = updatedUser.aboutThemself
        }

    override fun delete(id: Int) {
        find(id).also { userRepository.deleteById(id) }
    }

    private fun checkIfUserExists(username: String) {
        if (find(username) != null) {
            throw IllegalArgumentException("User with username $username already exists")
        }
    }

    private fun applyUpdates(id: Int, update: User.() -> Unit): User =
        find(id)
            .apply(update)
            .let { userRepository.save(it) }
}
