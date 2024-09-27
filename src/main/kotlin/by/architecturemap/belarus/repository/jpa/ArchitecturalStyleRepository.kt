package by.architecturemap.belarus.repository.jpa

import by.architecturemap.belarus.entity.ArchitecturalStyle
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ArchitecturalStyleRepository : JpaRepository<ArchitecturalStyle, Int>
