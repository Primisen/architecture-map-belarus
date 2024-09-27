package by.architecturemap.belarus.repository.jpa

import by.architecturemap.belarus.entity.Source
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SourceRepository : JpaRepository<Source, Int>
