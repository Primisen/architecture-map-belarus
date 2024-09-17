package by.architecture_map.belarus.service

import by.architecture_map.belarus.entity.Source

interface SourceService {

    fun create(source: Source): Source
    fun find(id: Int): Source
    fun findAll(): List<Source>
    fun update(id: Int, updatedSource: Source): Source
    fun patchUpdate(id: Int, updatedSource: Source): Source
    fun delete(id: Int)
}
