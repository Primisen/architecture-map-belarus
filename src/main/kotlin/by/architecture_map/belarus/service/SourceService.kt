package by.architecture_map.belarus.service

import by.architecture_map.belarus.entity.Source

interface SourceService {

    fun create(source: Source): Source
    fun findAll(): MutableList<Source>
    fun updateById(id: Int, updatedSource: Source): Source?
    fun patchById(id: Int, updatedSource: Source): Source?
    fun deleteById(id: Int): Boolean
}