package by.architecture_map.belarus.service

import by.architecture_map.belarus.entity.UserImage

interface UserImageService {
    fun addImages(images: List<UserImage>): List<UserImage>
    fun approveImage(id: Int): UserImage
}
