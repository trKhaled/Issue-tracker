package com.issuetracker.issuetracker.security

import io.jsonwebtoken.Claims
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.util.stream.Collectors

@Component
class AuthenticationManager(private val jwtUtil: JwtUtil) : ReactiveAuthenticationManager {

    override fun authenticate(authentication: Authentication): Mono<Authentication> {
        return Mono.just(authentication)
                .map { authentication.credentials.toString()
                }
                .filter { token: String ->
                    !jwtUtil.isTokenExpired(token)
                }
                .onErrorResume { Mono.empty() }
                .map { token: String ->
                    val userId: String = jwtUtil.getUsernameFromToken(token)?: ""
                    val claims: Claims = jwtUtil.getAllClaimsFromToken(token)
                    val roles: List<String> = claims.get<MutableList<*>>(JwtUtil.CLAIM_KEY, MutableList::class.java) as List<String>
                    val authorities = roles.stream().map { role: String? -> SimpleGrantedAuthority(role) }.collect(Collectors.toList())
                    val auth = AuthenticatedUser(userId, authorities)
                    auth
                }
    }

}