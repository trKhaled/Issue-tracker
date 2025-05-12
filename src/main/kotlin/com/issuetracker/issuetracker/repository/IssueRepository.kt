package com.issuetracker.issuetracker.repository

import com.issuetracker.issuetracker.model.Issue
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import java.util.*

@Repository
interface IssueRepository: ReactiveCrudRepository<Issue, UUID> {
    fun findAllById(ownerId: UUID): Flux<Issue>

}