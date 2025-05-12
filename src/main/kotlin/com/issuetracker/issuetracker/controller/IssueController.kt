package com.issuetracker.issuetracker.controller

import com.issuetracker.issuetracker.exception.NotFoundException
import com.issuetracker.issuetracker.model.Issue
import com.issuetracker.issuetracker.service.IssueService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

@RestController("IssueController")

class IssueController(private val issueService: IssueService) {

    @GetMapping("/issues")
    fun getAllIssues(): Flux<Issue> {
        return issueService.getAll()
    }

    @GetMapping("/issue/{id}")
    fun getWithId(@PathVariable id: UUID): Mono<Issue> {
        return issueService.getWithid(id).switchIfEmpty(Mono.error(NotFoundException()))
    }

    @GetMapping("/issue/{ownerId}")
    fun getAllIsuesFromOwner(@PathVariable ownerId: UUID): Flux<Issue> {
        return issueService.getAllFromUserWithId(ownerId)
    }

    @PostMapping("/issue")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody issue: Issue): Mono<UUID> {
        // needs to return id of issue
        return issueService.create(issue).map { issue.id?.let { issue.id } }
    }

    @PutMapping("/issue/{id}")
    fun update(@PathVariable id: UUID, @RequestBody issue: Issue): Mono<Issue> {
        return issueService.update(issue).switchIfEmpty(Mono.error(NotFoundException()))
    }

    @PatchMapping("/issue/{id}")
    fun changeTitle(@PathVariable id: UUID, @RequestBody key: String, value: String?, date: Date?): Mono<Issue> {
        return issueService.getWithid(id)
                .switchIfEmpty(Mono.error(NotFoundException()))
                .map { issue: Issue ->
                    if (key == "title") {
                        if (value != null) {
                            issue.title = value
                        }else  if (key == "title") {
                            if (date != null) {
                                issue.deadline = date
                            }
                        }
                    }
                    issue
                }.flatMap { issue: Issue -> issueService.update(issue) }
    }

    @DeleteMapping("/issue/{id}")
    fun delete(@PathVariable id: UUID): Mono<Void> {
        return issueService.delete(id)
    }
}