package by.architecturemap.belarus.service

import by.architecturemap.belarus.entity.User

interface EmailService {
    fun sendVerificationEmail(user: User, token: String)
}
