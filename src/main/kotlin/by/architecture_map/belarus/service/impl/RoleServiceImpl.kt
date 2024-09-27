package by.architecture_map.belarus.service.impl

import by.architecture_map.belarus.entity.Role
import by.architecture_map.belarus.repository.jpa.RoleRepository
import by.architecture_map.belarus.service.RoleService
import org.springframework.stereotype.Service

@Service
class RoleServiceImpl(private val roleRepository: RoleRepository) : RoleService {
    override fun find(name: String): Role = roleRepository.findRoleByName(name)
}
