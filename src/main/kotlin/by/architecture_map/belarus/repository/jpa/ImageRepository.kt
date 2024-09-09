package by.architecture_map.belarus.repository.jpa

import by.architecture_map.belarus.entity.Image
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ImageRepository : JpaRepository<Image, Int>