package by.architecturemap.belarus.repository.jpa

import by.architecturemap.belarus.entity.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository : JpaRepository<Role, Int> {
    fun findRoleByName(name: String): Role
}
