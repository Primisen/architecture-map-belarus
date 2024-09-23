package by.architecture_map.belarus.service

import by.architecture_map.belarus.dto.UserDTO
import by.architecture_map.belarus.entity.User

interface UserService {

    fun create(userDTO: UserDTO): User
    fun findAll(): List<User>
    fun find(id: Int): User
    fun find(username: String): User?
    fun update(id: Int, updatedUser: User): User
    fun patchUpdate(id: Int, updatedUser: User): User
    fun delete(id: Int)
}