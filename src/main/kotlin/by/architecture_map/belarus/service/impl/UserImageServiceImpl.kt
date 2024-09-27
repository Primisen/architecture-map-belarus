package by.architecture_map.belarus.service.impl

import by.architecture_map.belarus.entity.UserImage
import by.architecture_map.belarus.exception.NotFoundException
import by.architecture_map.belarus.repository.jpa.UserImageRepository
import by.architecture_map.belarus.service.UserImageService
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
