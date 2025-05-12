package com.issuetracker.issuetracker.service


import com.issuetracker.issuetracker.exception.ForbiddenException
import com.issuetracker.issuetracker.model.Comment

import com.issuetracker.issuetracker.repository.CommentRepository

import com.issuetracker.issuetracker.security.AuthenticatedUser
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

@Service
class CommentService(private val commentRepository: CommentRepository) {

    fun create(comment: Comment): Mono<Comment> {
        return ReactiveSecurityContextHolder.getContext()
                .map { securityContext ->
                    securityContext.authentication
                }
                .cast(AuthenticatedUser::class.java)
                .filter { authenticatedUser ->
                    authenticatedUser.name == comment.userId.toString()
                }
                .switchIfEmpty(Mono.error(ForbiddenException()))
                .flatMap { authenticatedUser ->
                    commentRepository.save(comment)
                }

    }

    fun getWithid(id: UUID): Mono<Comment> {
        return commentRepository.findById(id)
    }

    fun getAll(): Flux<Comment> {
        return commentRepository.findAll()
    }

    fun delete(id: UUID): Mono<Void> {
        return commentRepository.deleteById(id)
    }

    fun update(comment: Comment): Mono<Comment> {
        return commentRepository.save(comment)
    }
  fun getWithIssueId(issueId: UUID): Flux<Comment> {
      return commentRepository.findByIssueId(issueId)
  }
    fun getWithUserId(userId: UUID): Flux<Comment> {
        return commentRepository.findByUserId(userId)
    }
}