package by.architecturemap.belarus.service.impl

import by.architecturemap.belarus.entity.Role
import by.architecturemap.belarus.repository.jpa.RoleRepository
import by.architecturemap.belarus.service.RoleService
import org.springframework.stereotype.Service

@Service
class RoleServiceImpl(private val roleRepository: RoleRepository) : RoleService {
    override fun find(name: String): Role = roleRepository.findRoleByName(name)
}
