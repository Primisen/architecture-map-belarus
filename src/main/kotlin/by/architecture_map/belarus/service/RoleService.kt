package by.architecture_map.belarus.service

import by.architecture_map.belarus.entity.Role

interface RoleService {
    fun find(name: String): Role
}
