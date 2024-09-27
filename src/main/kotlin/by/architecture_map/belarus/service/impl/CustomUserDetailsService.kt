package by.architecture_map.belarus.service.impl

import by.architecture_map.belarus.exception.NotFoundException
import by.architecture_map.belarus.repository.jpa.UserRepository
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
