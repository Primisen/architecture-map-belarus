package by.architecture_map.belarus.repository.jpa

import by.architecture_map.belarus.entity.UserImage
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserImageRepository : JpaRepository<UserImage, Int> {
}
