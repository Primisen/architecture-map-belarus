package by.architecturemap.belarus.repository.jpa

import by.architecturemap.belarus.entity.Architect
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ArchitectRepository : JpaRepository<Architect, Int>
