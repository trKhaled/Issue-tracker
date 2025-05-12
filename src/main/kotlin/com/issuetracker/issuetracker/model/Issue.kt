package com.issuetracker.issuetracker.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table("issues")
data class Issue(
        @Id
        var id: UUID?,
        var ownerId: UUID,
        var title: String,
        var deadline: Date?
)