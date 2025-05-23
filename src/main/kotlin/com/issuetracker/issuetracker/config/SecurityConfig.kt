package com.issuetracker.issuetracker.config

import com.issuetracker.issuetracker.security.AuthenticationManager
import com.issuetracker.issuetracker.security.SecurityContextRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.AuthenticationException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Configuration
@EnableWebFluxSecurity
class SecurityConfig(
        private val securityContextRepository: SecurityContextRepository,
        private val authenticationManager: AuthenticationManager
) {
    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun springWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        val patterns = arrayOf("/auth/login")
        val register = "/user/register"
        return http.cors().disable()
                .exceptionHandling()
                .authenticationEntryPoint { swe: ServerWebExchange, _: AuthenticationException? ->
                    Mono.fromRunnable { swe.response.statusCode = HttpStatus.UNAUTHORIZED }
                }
                .accessDeniedHandler { swe: ServerWebExchange, _: AccessDeniedException? ->
                    Mono.fromRunnable { swe.response.statusCode = HttpStatus.FORBIDDEN }
                }
                .and()
                .csrf().disable()
                .authenticationManager(authenticationManager)
                .securityContextRepository(securityContextRepository)
                .authorizeExchange()
                .pathMatchers(*patterns).permitAll()             // turn off permission
                .pathMatchers(register).hasAuthority("admin")     // turn off permission
               // .pathMatchers("/**").permitAll()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .anyExchange().authenticated()
                .and()
                .build()
    }

}