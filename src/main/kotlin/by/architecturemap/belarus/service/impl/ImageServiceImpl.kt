package by.architecturemap.belarus.service.impl

import by.architecturemap.belarus.entity.Image
import by.architecturemap.belarus.exception.NotFoundException
import by.architecturemap.belarus.repository.jpa.ImageRepository
import by.architecturemap.belarus.service.ImageService
import org.springframework.stereotype.Service

@Service
class ImageServiceImpl(
    private val imageRepository: ImageRepository
) : ImageService {

    override fun find(id: Int): Image =
        imageRepository.findById(id).orElseThrow { NotFoundException("Image not found with id: $id") }
}
