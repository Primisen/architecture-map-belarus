package by.architecture_map.belarus.service.impl

import by.architecture_map.belarus.entity.Source
import by.architecture_map.belarus.repository.SourceRepository
import by.architecture_map.belarus.service.SourceService
import org.springframework.stereotype.Service

@Service
class SourceServiceImpl(
        private val sourceRepository: SourceRepository
) : SourceService {

    override fun create(source: Source): Source {
        return sourceRepository.save(source)
    }

    override fun findAll(): MutableList<Source> {
        return sourceRepository.findAll()
    }

    override fun updateById(id: Int, updatedSource: Source): Source? {
        var savedSource: Source? = null

        sourceRepository.findById(id).ifPresent { foundSource ->
            foundSource.name = updatedSource.name
            foundSource.url = updatedSource.url
            foundSource.description = updatedSource.description
            savedSource = sourceRepository.save(foundSource)
        }

        return savedSource
    }

    override fun patchById(id: Int, updatedSource: Source): Source? {
        var savedSource: Source? = null

        sourceRepository.findById(id).ifPresent { foundSource ->
            if (!updatedSource.name.isNullOrEmpty())
                foundSource.name = updatedSource.name
            if (updatedSource.url.isNullOrEmpty())
                foundSource.url = updatedSource.url
            if (!updatedSource.description.isNullOrEmpty())
                foundSource.description = updatedSource.description

            savedSource = sourceRepository.save(foundSource)
        }

        return savedSource
    }

    override fun deleteById(id: Int): Boolean {

        if (sourceRepository.existsById(id)) {
            sourceRepository.deleteById(id)
            return true
        }

        return false
    }
}