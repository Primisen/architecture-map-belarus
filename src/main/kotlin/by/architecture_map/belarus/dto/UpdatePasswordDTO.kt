package by.architecture_map.belarus.dto

data class UpdatePasswordDTO(
    var userId: Int,
    var oldPassword: String,
    var newPassword: String
)
