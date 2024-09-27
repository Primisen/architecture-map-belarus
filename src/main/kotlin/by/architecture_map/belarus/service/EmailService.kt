package by.architecture_map.belarus.service

import by.architecture_map.belarus.entity.User

interface EmailService {
    fun sendVerificationEmail(user: User, token: String)
}
