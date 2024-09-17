package by.architecture_map.belarus.repository

import by.architecture_map.belarus.entity.Architect
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ArchitectRepository : JpaRepository<Architect, Int>