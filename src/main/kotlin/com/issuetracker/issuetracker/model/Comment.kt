package com.issuetracker.issuetracker.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table("comments")
data class Comment(
        @Id
        var id: UUID?,
        var body: String,
        var userId: UUID?,
        var issueId : UUID?
)