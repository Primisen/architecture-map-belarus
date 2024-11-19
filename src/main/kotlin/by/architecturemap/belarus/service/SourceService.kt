package by.architecturemap.belarus.service

import by.architecturemap.belarus.dto.SourceDTO
import by.architecturemap.belarus.entity.Source

interface SourceService {

    fun create(source: Source): Source
    fun find(id: Int): Source
    fun findAll(): List<Source>
    fun update(id: Int, updatedSource: Source): Source
    fun patchUpdate(id: Int, updatedSource: SourceDTO): Source
    fun delete(id: Int)
}
