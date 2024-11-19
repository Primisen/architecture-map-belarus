package by.architecturemap.belarus.service.impl

import by.architecturemap.belarus.entity.UserImage
import by.architecturemap.belarus.exception.NotFoundException
import by.architecturemap.belarus.repository.jpa.UserImageRepository
import by.architecturemap.belarus.service.UserImageService
import org.springframework.stereotype.Service

@Service
class UserImageServiceImpl(private val userImageRepository: UserImageRepository) : UserImageService {

    override fun addImages(images: List<UserImage>): List<UserImage> =
        userImageRepository.saveAll(images)

    override fun approveImage(id: Int): UserImage =
        find(id)
            .let {
                it.approvedByAdmin = true
                userImageRepository.save(it)
            }

    private fun find(id: Int): UserImage =
        userImageRepository.findById(id).orElseThrow { throw NotFoundException("User image not found with id: $id") }
}
