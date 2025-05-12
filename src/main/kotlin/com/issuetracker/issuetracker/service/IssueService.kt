package com.issuetracker.issuetracker.service

import com.issuetracker.issuetracker.exception.ForbiddenException
import com.issuetracker.issuetracker.model.Issue
import com.issuetracker.issuetracker.repository.IssueRepository
import com.issuetracker.issuetracker.security.AuthenticatedUser
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

@Service
class IssueService(private val issueRepository: IssueRepository) {

    fun create(issue: Issue): Mono<Issue> {
        return ReactiveSecurityContextHolder.getContext()
                .map { securityContext ->
                    securityContext.authentication
                }
                .cast(AuthenticatedUser::class.java)
                .filter { authenticatedUser ->
                    authenticatedUser.name == issue.ownerId.toString()
                }
                .switchIfEmpty(Mono.error(ForbiddenException()))
                .flatMap {
                    issueRepository.save(issue)
                }

    }

    fun getWithid(id: UUID): Mono<Issue> {
        return issueRepository.findById(id)
    }

    fun getAll(): Flux<Issue> {
        return issueRepository.findAll()
    }

    fun delete(id: UUID): Mono<Void> {
        return issueRepository.deleteById(id)
    }

    fun update(issue: Issue): Mono<Issue> {
        return issueRepository.save(issue)
    }

    fun getAllFromUserWithId(ownerId: UUID): Flux<Issue> {
        return issueRepository.findAllById(ownerId)
    }
}