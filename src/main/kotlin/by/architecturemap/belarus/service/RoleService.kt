package by.architecturemap.belarus.service

import by.architecturemap.belarus.entity.Role

interface RoleService {
    fun find(name: String): Role
}
