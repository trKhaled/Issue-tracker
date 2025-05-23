package com.issuetracker.issuetracker.security

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

class AuthenticatedUser(private val userId: String, private val roles: List<SimpleGrantedAuthority?>) : Authentication {
    companion object {
        private const val serialVersionUID = 5476520147693547695L
    }

    private var authenticated = true
    override fun getAuthorities(): Collection<GrantedAuthority?> {
        return roles
    }

    override fun getCredentials(): Any {
        return userId
    }

    override fun getDetails(): Any? {
        return null
    }

    override fun getPrincipal(): Any {
        return userId
    }

    override fun isAuthenticated(): Boolean {
        return authenticated
    }

    @Throws(IllegalArgumentException::class)
    override fun setAuthenticated(b: Boolean) {
        authenticated = b
    }

    override fun getName(): String {
        return userId
    }

    override fun toString(): String {
        return "AuthenticatedUser(userId='$userId', roles=$roles, authenticated=$authenticated)"
    }


}
