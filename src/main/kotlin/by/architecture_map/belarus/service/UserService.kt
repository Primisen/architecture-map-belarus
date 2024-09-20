package by.architecture_map.belarus.service

import by.architecture_map.belarus.entity.User

interface UserService {

    fun create(user: User): User
    fun findAll(): List<User>
    fun find(id: Int): User
    fun update(id: Int, updatedUser: User): User
    fun patchUpdate(id: Int, updatedUser: User): User
    fun delete(id: Int)
}