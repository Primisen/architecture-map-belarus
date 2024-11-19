package by.architecturemap.belarus.service

import by.architecturemap.belarus.dto.RegistrationUserDTO
import by.architecturemap.belarus.dto.UserDTO
import by.architecturemap.belarus.entity.User

interface RegisterService {

    fun register(registrationUserDTO: RegistrationUserDTO): UserDTO
    fun confirmEmail(token: String)
    fun find(username: String): User?
}
