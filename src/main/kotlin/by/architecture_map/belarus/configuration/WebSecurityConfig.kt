package by.architecture_map.belarus.configuration

import by.architecture_map.belarus.service.impl.CustomDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
//open class WebSecurityConfig() {
open class WebSecurityConfig(private val customUserDetailsService: CustomDetailsService) {

    @Bean
    open fun securityFilterChain(http: HttpSecurity): SecurityFilterChain =
        http
            .csrf { it.disable() }
            .authorizeHttpRequests { requests ->
                requests
                    .requestMatchers(
                        "/users/**"
                    ).authenticated()
                    .anyRequest().permitAll()

            }
            .formLogin { form ->
                form
//                    .loginPage("/login")
                    .permitAll()
            }
            .logout { logout -> logout.permitAll() }
            .build()

//    @Bean
//    open fun authenticationManager(configuration: AuthenticationConfiguration): AuthenticationManager =
//        configuration.authenticationManager

//    @Bean
//    open fun authenticationManager(auth: AuthenticationManagerBuilder): AuthenticationManager {
//        auth.authenticationProvider(daoAuthenticationProvider())
//        return auth.build()
//    }

    @Bean
    open fun daoAuthenticationProvider(): DaoAuthenticationProvider {
        val provider = DaoAuthenticationProvider()
        provider.setUserDetailsService(customUserDetailsService)  // Your UserDetailsService implementation
        provider.setPasswordEncoder(passwordEncoder())  // Your password encoder
        return provider
    }

    @Bean
    open fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder();

    @Bean
    open fun userDetailsService(): UserDetailsService = customUserDetailsService

}
