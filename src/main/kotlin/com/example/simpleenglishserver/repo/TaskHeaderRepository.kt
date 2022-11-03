package com.example.simpleenglishserver.repo

import com.example.simpleenglishserver.model.TaskHeader
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface TaskHeaderRepository : CrudRepository<TaskHeader?, Int?> {
    fun findTaskHeadersByTaskTypeAndContentNotNull(type: String): Iterable<TaskHeader?>?
}
