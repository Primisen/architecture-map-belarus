package by.architecture_map.belarus.service.impl

import by.architecture_map.belarus.entity.User
import by.architecture_map.belarus.repository.jpa.UserRepository
import by.architecture_map.belarus.service.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService {

    override fun create(user: User): User {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<User> {
        TODO("Not yet implemented")
    }

    override fun find(id: Int): User {
        TODO("Not yet implemented")
    }

    override fun update(id: Int, updatedUser: User): User {
        TODO("Not yet implemented")
    }

    override fun patchUpdate(id: Int, updatedUser: User): User {
        TODO("Not yet implemented")
    }

    override fun delete(id: Int) {
        TODO("Not yet implemented")
    }
}