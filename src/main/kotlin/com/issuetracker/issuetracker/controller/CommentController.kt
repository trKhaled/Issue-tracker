
package com.issuetracker.issuetracker.controller

import com.issuetracker.issuetracker.exception.NotFoundException
import com.issuetracker.issuetracker.model.Comment
import com.issuetracker.issuetracker.model.User
import com.issuetracker.issuetracker.service.CommentService
import org.springframework.data.relational.core.sql.Not

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

@RestController("CommentController")

class CommentController(private val commentService: CommentService) {

    @GetMapping("/comments")
    fun getAllComments(): Flux<Comment> {
        return commentService.getAll()
    }

    @GetMapping("/comment/issue/{issueId}")
    fun getWithIssueId(@PathVariable issueId: UUID): Flux<Comment> {
        return commentService.getWithIssueId(issueId).switchIfEmpty(Mono.error(NotFoundException()))
    }

    @GetMapping("/comment/user/{userId}")
    fun getWithUserId(@PathVariable userId: UUID): Flux<Comment> {
        return commentService.getWithUserId(userId).switchIfEmpty(Mono.error(NotFoundException()))
    }

    @GetMapping("/comment/{id}")
    fun getWithId(@PathVariable id: UUID): Mono<Comment> {
        return commentService.getWithid(id).switchIfEmpty(Mono.error(NotFoundException()))
    }

    @PutMapping("/comment/{id}")
    fun update(@PathVariable id: UUID, @RequestBody comment: Comment): Mono<Comment> {
        return commentService.update(comment).switchIfEmpty(Mono.error(NotFoundException()))
    }


    @DeleteMapping("/comment/{id}")
    fun delete(@PathVariable id: UUID): Mono<Void> {
        return commentService.delete(id)
    }

    @PostMapping("/comment")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody comment: Comment): Mono<UUID> {
        // needs to return id of commentar
        return commentService.create(comment).map { comment.id?.let { comment.id } }
    }

    @PatchMapping("/comment/{id}")
    fun patch(@PathVariable id: UUID, @RequestBody changes: Map<String, String>): Mono<Comment> {
        return commentService.getWithid(id)
                .switchIfEmpty(Mono.error(NotFoundException()))
                .map { comment: Comment ->
                    for ((key, value) in changes) {
                        if (key == "body") {
                            comment.body = value
                        }
                    }
                    comment
                }.flatMap { comment: Comment -> commentService.update(comment) }
    }

}