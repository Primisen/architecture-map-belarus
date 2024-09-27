package by.architecturemap.belarus.service

import by.architecturemap.belarus.entity.EmailVerificationToken

interface VerificationTokenService {
    fun save(token: EmailVerificationToken): EmailVerificationToken
    fun find(token: String): EmailVerificationToken?
    fun checkThatTokenExistsAndNotExpired(token: String): EmailVerificationToken
}
