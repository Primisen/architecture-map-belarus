package by.architecturemap.belarus.repository.jpa

import by.architecturemap.belarus.entity.Construction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ConstructionRepository : JpaRepository<Construction, Int>
