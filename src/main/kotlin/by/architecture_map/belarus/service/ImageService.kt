package by.architecture_map.belarus.service

import by.architecture_map.belarus.entity.Image

interface ImageService {

    fun find(id: Int): Image
}
