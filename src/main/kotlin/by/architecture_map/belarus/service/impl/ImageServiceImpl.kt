package by.architecture_map.belarus.service.impl

import by.architecture_map.belarus.entity.Image
import by.architecture_map.belarus.exception.NotFoundException
import by.architecture_map.belarus.repository.ImageRepository
import by.architecture_map.belarus.service.ImageService
import org.springframework.stereotype.Service

@Service
class ImageServiceImpl(
    private val imageRepository: ImageRepository
) : ImageService {

    override fun find(id: Int): Image =
        imageRepository.findById(id).orElseThrow { NotFoundException("Image not found with id: $id") }
}
