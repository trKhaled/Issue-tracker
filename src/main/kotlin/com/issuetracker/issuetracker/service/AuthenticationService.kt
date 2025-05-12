package com.issuetracker.issuetracker.service

import com.issuetracker.issuetracker.model.User
import com.issuetracker.issuetracker.repository.UserRepository
import com.issuetracker.issuetracker.security.JwtUtil
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class AuthenticationService(
        private val userRepository: UserRepository,
        private val jwtUtil: JwtUtil,
        private val passwordEncoder: BCryptPasswordEncoder
) {

    fun login(username: String, password: String): Mono<String> {
        return userRepository.findByUsername(username)
                .flatMap { user: User ->
                    if (passwordEncoder.matches(password,user.password)) {
                        //if (password == user.password) {
                        Mono.just(jwtUtil.generateToken(user))
                    } else {
                        Mono.empty<String>()
                    }
                }
    }
}
