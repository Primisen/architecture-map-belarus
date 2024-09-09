package by.architecture_map.belarus.service.impl

import by.architecture_map.belarus.entity.Source
import by.architecture_map.belarus.exception.NotFoundException
import by.architecture_map.belarus.repository.SourceRepository
import by.architecture_map.belarus.service.SourceService
import org.springframework.stereotype.Service

@Service
class SourceServiceImpl(
    private val sourceRepository: SourceRepository
) : SourceService {

    override fun create(source: Source) = sourceRepository.save(source)

    override fun findAll() = sourceRepository.findAll()

    override fun update(id: Int, updatedSource: Source): Source =

        sourceRepository.findById(id)
            .orElseThrow { NotFoundException("Source not found with id: $id") }
            .apply {
                name = updatedSource.name
                url = updatedSource.url
                description = updatedSource.description
            }
            .let { sourceRepository.save(it) }


    override fun patchUpdate(id: Int, updatedSource: Source): Source =

        sourceRepository.findById(id)
            .orElseThrow { NotFoundException("Source not found with id: $id") }
            .apply {
                if (!updatedSource.name.isNullOrEmpty())
                    name = updatedSource.name
                if (updatedSource.url.isNullOrEmpty())
                    url = updatedSource.url
                if (!updatedSource.description.isNullOrEmpty())
                    description = updatedSource.description
            }
            .let {
                sourceRepository.save(it)
            }


    override fun delete(id: Int) {

        if (sourceRepository.existsById(id)) {
            sourceRepository.deleteById(id)
        } else {
            throw NotFoundException("Source not found with id: $id")
        }
    }
}
