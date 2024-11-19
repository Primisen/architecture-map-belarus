package by.architecturemap.belarus.service.impl

import by.architecturemap.belarus.exception.NotFoundException
import by.architecturemap.belarus.repository.jpa.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(private val userRepository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails =
        userRepository.findUserByUsername(username)
            ?.let { user ->
                check(user.enable) { "User account is not enabled." }
                User(
                    user.username,
                    user.password.joinToString(""),
                    user.roles.map { SimpleGrantedAuthority(it.name) }
                )
            } ?: throw NotFoundException("User with $username not found")
}
