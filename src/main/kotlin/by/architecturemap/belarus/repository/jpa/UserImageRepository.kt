package by.architecturemap.belarus.repository.jpa

import by.architecturemap.belarus.entity.UserImage
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserImageRepository : JpaRepository<UserImage, Int>
