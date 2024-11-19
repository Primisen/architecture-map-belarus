package by.architecturemap.belarus.service

import by.architecturemap.belarus.dto.UpdatePasswordDTO
import by.architecturemap.belarus.dto.UserDTO
import by.architecturemap.belarus.entity.User

interface UserService {

    fun findAll(): List<UserDTO>
    fun find(id: Int): User
    fun find(username: String): User?
    fun update(id: Int, updatedUser: UserDTO): UserDTO
    fun updatePassword(updatePasswordDTO: UpdatePasswordDTO)
    fun delete(id: Int)
}
