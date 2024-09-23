package by.architecture_map.belarus.repository.jpa

import by.architecture_map.belarus.entity.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository : JpaRepository<Role, Int> {
    fun findRoleByName(name: String): Role
}