package com.example.simpleenglishserver.repo

import com.example.simpleenglishserver.model.Task
import org.springframework.data.repository.CrudRepository

interface TaskRepository : CrudRepository<Task?, Int?> {
    fun findTasksByTaskType(type: String): Iterable<Task?>?
}
