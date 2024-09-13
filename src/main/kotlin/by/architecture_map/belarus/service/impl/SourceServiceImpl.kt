package by.architecture_map.belarus.service.impl

import by.architecture_map.belarus.entity.Source
import by.architecture_map.belarus.exception.NotFoundException
import by.architecture_map.belarus.repository.jpa.SourceRepository
import by.architecture_map.belarus.service.SourceService
import org.springframework.stereotype.Service

@Service
class SourceServiceImpl(
    private val sourceRepository: SourceRepository
) : SourceService {

    override fun create(source: Source) = sourceRepository.save(source)

    override fun findAll() = sourceRepository.findAll()

    override fun find(id: Int): Source = sourceRepository.findById(id)
        .orElseThrow { throw NotFoundException("Source not found with id: $id") }

    override fun update(id: Int, updatedSource: Source): Source =
        applyUpdates(id) {
            name = updatedSource.name
            url = updatedSource.url
            description = updatedSource.description
        }

    override fun patchUpdate(id: Int, updatedSource: Source): Source =
        applyUpdates(id) {
            if (!updatedSource.name.isNullOrEmpty())
                name = updatedSource.name
            if (updatedSource.url.isNullOrEmpty())
                url = updatedSource.url
            if (!updatedSource.description.isNullOrEmpty())
                description = updatedSource.description
        }

    override fun delete(id: Int) {
        find(id).also { sourceRepository.deleteById(id) }
    }

    private fun applyUpdates(id: Int, update: Source.() -> Unit): Source =
        find(id)
            .apply(update)
            .let { sourceRepository.save(it) }
}
