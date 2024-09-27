package by.architecturemap.belarus.service

import by.architecturemap.belarus.entity.Image

interface ImageService {

    fun find(id: Int): Image
}
