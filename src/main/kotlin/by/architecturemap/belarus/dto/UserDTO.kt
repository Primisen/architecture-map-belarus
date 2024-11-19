package by.architecturemap.belarus.dto

import by.architecturemap.belarus.entity.Role
import by.architecturemap.belarus.entity.UserImage

data class UserDTO(
    var username: String,
    var enable: Boolean? = true,
    var name: String? = null,
    var surname: String? = null,
    var aboutThemself: String? = null,
    var roles: Set<Role> = setOf(),
    var images: List<UserImage> = listOf()
)
