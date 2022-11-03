package com.example.simpleenglishserver.repo

import com.example.simpleenglishserver.model.TaskContent
import com.example.simpleenglishserver.model.TaskHeader
import org.springframework.data.repository.CrudRepository

interface TaskContentRepository : CrudRepository<TaskContent?, Int?>