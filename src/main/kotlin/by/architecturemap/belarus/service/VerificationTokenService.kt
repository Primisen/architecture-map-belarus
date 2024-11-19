package by.architecturemap.belarus.service

import by.architecturemap.belarus.entity.EmailVerificationToken

interface VerificationTokenService {
    fun save(token: EmailVerificationToken): EmailVerificationToken
    fun getToken(token: String): EmailVerificationToken
}
