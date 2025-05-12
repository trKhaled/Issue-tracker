package com.issuetracker.issuetracker.controller

import com.issuetracker.issuetracker.exception.ForbiddenException
import com.issuetracker.issuetracker.service.AuthenticationService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController("AuthController")
class AuthController(
        private val authenticationService: AuthenticationService
) {
    @GetMapping("/auth/login")
    fun login(
            @RequestParam username: String,
            @RequestParam password: String
    ): Mono<String> {
        return authenticationService.login(username, password).switchIfEmpty(Mono.error(ForbiddenException()))
    }
}
