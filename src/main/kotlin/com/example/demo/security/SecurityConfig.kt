package com.example.demo.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig {
    @Bean
    fun userDetailsService(encoder: PasswordEncoder): UserDetailsService {
        val admin = User.withUsername("admin")
            .password(encoder.encode("admin"))
            .roles("ADMIN", "USER")
            .build()

        val user = User.withUsername("user")
            .password(encoder.encode("user"))
            .roles("USER")
            .build()

        return InMemoryUserDetailsManager(admin, user)
    }

    @Bean
    fun securityFilterChain(httpSecurity: HttpSecurity) : SecurityFilterChain {
        httpSecurity.csrf { it.disable() }
        httpSecurity.formLogin { it.permitAll() }
        httpSecurity.authorizeHttpRequests {
            it.requestMatchers("/hello-all").permitAll()
            it.requestMatchers("/hello-user").hasRole("USER")
            it.requestMatchers("/hello-admin").hasRole("ADMIN")
        }
        return httpSecurity.build()
    }

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()
}