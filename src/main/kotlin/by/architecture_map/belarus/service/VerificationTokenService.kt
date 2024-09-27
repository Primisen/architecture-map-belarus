package by.architecture_map.belarus.service

import by.architecture_map.belarus.entity.EmailVerificationToken

interface VerificationTokenService {
    fun save(token: EmailVerificationToken): EmailVerificationToken
    fun find(token: String): EmailVerificationToken?
    fun checkThatTokenExistsAndNotExpired(token: String): EmailVerificationToken
}
