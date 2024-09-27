package by.architecturemap.belarus.repository.jpa

import by.architecturemap.belarus.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Int> {
    fun findUserByUsername(username: String): User?
}
