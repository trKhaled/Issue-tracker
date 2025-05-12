package com.issuetracker.issuetracker.controller

import com.issuetracker.issuetracker.exception.NotFoundException
import com.issuetracker.issuetracker.model.User
import com.issuetracker.issuetracker.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

@RestController("UserController")

class UserController(private val userService: UserService) {

    @GetMapping("/users")
    fun getAllUsers(): Flux<User> {
        return userService.getAll()
    }


    @GetMapping("/user/{id}")
    fun getWithId(@PathVariable id: UUID): Mono<User> {
        return userService.getWithId(id).switchIfEmpty(Mono.error(NotFoundException()))
    }

    @PutMapping("/user/{id}")
    fun update(@PathVariable id: UUID, @RequestBody user: User): Mono<User> {
        return userService.update(user).switchIfEmpty(Mono.error(NotFoundException()))
    }

    @PatchMapping("/user/{id}")
    fun patch(@PathVariable id: UUID, @RequestBody changes: Map<String, String>): Mono<User> {
        return userService.getWithId(id)
                .switchIfEmpty(Mono.error(NotFoundException()))
                .map { user: User ->
                    for ((key, value) in changes) {
                        if (key == "username") {
                            user.username = value
                        } else if (key == "password") {
                            user.password = value
                        } else if (key == "role") {
                            user.role = value
                        }
                    }
                    user
                }.flatMap { user: User -> userService.update(user) }
    }

    @DeleteMapping("/user/{id}")
    fun delete(@PathVariable id: UUID): Mono<Void> {
        return userService.delete(id)
    }

    @PostMapping("/user/register")
    fun register(@RequestBody user: User): Mono<UUID>{
        return userService.register(user).map { user.id?.let { user.id } } //check if id null, then return var.id as mono uuid
    }
}