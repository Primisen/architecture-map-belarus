package by.architecturemap.belarus.controller

import by.architecturemap.belarus.entity.UserImage
import by.architecturemap.belarus.service.UserImageService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user-images")
@CrossOrigin(origins = ["http://localhost:7200", "http://localhost:4200", "*"])
class UserImageController(private val userImageService: UserImageService) {

    @PostMapping("/")
    fun addImages(@RequestBody images: List<UserImage>): List<UserImage> =
        userImageService.addImages(images)

    @PutMapping("/{id}")
    fun approveImage(@PathVariable id: Int): UserImage =
        userImageService.approveImage(id)
}
