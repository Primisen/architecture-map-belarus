package by.architecturemap.belarus.service

import by.architecturemap.belarus.entity.UserImage

interface UserImageService {
    fun addImages(images: List<UserImage>): List<UserImage>
    fun approveImage(id: Int): UserImage
}
