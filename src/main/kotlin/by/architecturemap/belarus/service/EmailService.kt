package by.architecturemap.belarus.service

import by.architecturemap.belarus.entity.User

interface EmailService {
    fun sendVerificationTokenToEmail(user: User, token: String)
}
