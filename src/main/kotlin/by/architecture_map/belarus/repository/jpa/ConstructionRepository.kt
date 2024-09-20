package by.architecture_map.belarus.repository.jpa

import by.architecture_map.belarus.entity.Construction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ConstructionRepository : JpaRepository<Construction, Int>