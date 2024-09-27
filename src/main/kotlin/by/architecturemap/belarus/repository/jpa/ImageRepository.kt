package by.architecturemap.belarus.repository.jpa

import by.architecturemap.belarus.entity.Image
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ImageRepository : JpaRepository<Image, Int>
