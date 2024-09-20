package by.architecture_map.belarus.repository.jpa

import by.architecture_map.belarus.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Int> {
}