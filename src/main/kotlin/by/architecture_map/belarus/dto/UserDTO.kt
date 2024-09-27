package by.architecture_map.belarus.dto

import by.architecture_map.belarus.entity.Role
import by.architecture_map.belarus.entity.UserImage

data class UserDTO(
    var username: String,
    var enable: Boolean,
    var name: String? = null,
    var surname: String? = null,
    var aboutThemself: String? = null,
    var roles: Set<Role> = setOf(),
    var images: List<UserImage> = listOf()
)
