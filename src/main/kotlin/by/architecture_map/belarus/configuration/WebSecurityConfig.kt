package by.architecture_map.belarus.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
open class WebSecurityConfig {

    @Bean
    open fun securityFilterChain(http: HttpSecurity): SecurityFilterChain =
        http
            .authorizeHttpRequests { requests ->
                requests
                    .requestMatchers(
                        "/",
                        "/home",
                        "/architectural-styles",
                        "/architects",
                        "/sources",
                        "/contacts",
                        "/constructions/**",
                        "/articles"
                    ).permitAll()
                    .anyRequest().authenticated()
            }
            .formLogin { form ->
                form
                    .loginPage("/login")
                    .permitAll()
            }
            .logout { logout -> logout.permitAll() }
            .build()

    @Bean
    open fun authenticationManager(configuration: AuthenticationConfiguration): AuthenticationManager =
        configuration.authenticationManager



//    @Bean
//    open fun userDetailsService(): UserDetailsService {
//        val john: UserDetails = User.builder()
//            .username("john")
//            .password(passwordEncoder().encode("john"))
//            .roles("USER")
//            .build()
//
//        val sam: UserDetails = User.builder()
//            .username("sam")
//            .password(passwordEncoder().encode("sam"))
//            .roles("ADMIN")
//            .build()
//
//        return InMemoryUserDetailsManager(john, sam)
//    }

    @Bean
    open fun  passwordEncoder(): PasswordEncoder{
        return  BCryptPasswordEncoder();
    }


//    @Bean
//    open fun createPasswordEncoder(): PasswordEncoder =
//        PasswordEncoderFactories.createDelegatingPasswordEncoder()

}