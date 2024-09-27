package by.architecture_map.belarus.service

import by.architecture_map.belarus.dto.RegistrationUserDTO
import by.architecture_map.belarus.dto.UpdatePasswordDTO
import by.architecture_map.belarus.dto.UserDTO
import by.architecture_map.belarus.entity.User

interface UserService {

    fun register(registrationUserDTO: RegistrationUserDTO): UserDTO
    fun confirmEmail(token: String)
    fun findAll(): List<UserDTO>
    fun find(id: Int): User
    fun find(username: String): User?
    fun update(id: Int, updatedUser: UserDTO): UserDTO
    fun updatePassword(updatePasswordDTO: UpdatePasswordDTO)
    fun patchUpdate(id: Int, updatedUser: UserDTO): UserDTO
    fun delete(id: Int)
}
