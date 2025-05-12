package com.issuetracker.issuetracker.service

import com.issuetracker.issuetracker.exception.ForbiddenException
import com.issuetracker.issuetracker.model.User
import com.issuetracker.issuetracker.repository.UserRepository
import com.issuetracker.issuetracker.security.AuthenticatedUser
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

@Service
class UserService(
        private val userRepository: UserRepository,
        private val passwordEncoder: BCryptPasswordEncoder
) {
    fun create(user: User): Mono<User> {
        return userRepository.save(user)
    }

    fun register(user: User): Mono<User> {
        user.password = passwordEncoder.encode(user.password)
        return userRepository.findByUsername(user.username).flatMap {Mono.error<User>(ForbiddenException()) }
                .switchIfEmpty(Mono.defer { userRepository.save(user) })
    }

    fun getWithId(id: UUID): Mono<User> {
        return userRepository.findById(id)
    }

    fun getAll(): Flux<User> {
        return userRepository.findAll()
    }

    fun delete(id: UUID): Mono<Void> {
        return ReactiveSecurityContextHolder.getContext()
                .map { securityContext ->
                    securityContext.authentication
                }
                .cast(AuthenticatedUser::class.java)
                .filter { authenticatedUser ->
                    authenticatedUser.name == id.toString() || authenticatedUser.authorities.iterator().equals("admin")
                }
                .switchIfEmpty(Mono.error(ForbiddenException()))
                .flatMap {
                    userRepository.deleteById(id)
                }
    }

    fun update(user: User): Mono<User> {
        return userRepository.save(user)
    }
}