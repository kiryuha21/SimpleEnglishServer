package com.example.simpleenglishserver.repo

import com.example.simpleenglishserver.model.TaskHeader
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface TaskHeaderRepository : CrudRepository<TaskHeader?, Int?> {
    fun findTaskHeadersByTaskTypeAndContentNotNull(type: String): Iterable<TaskHeader?>?

    fun findTaskHeadersByIdIn(ids: ArrayList<Int>?): Iterable<TaskHeader?>?

    @Query(value = "select header.id " +
            "from \"user\", tasks_headers header, tasks_content content\n" +
            "where \"user\".id = ?1 and\n" +
            "header.id = any(\"user\".started_memories) and " +
            "header.task_content_id = content.id and " +
            "content.mem_last_update + content.next_notice_in::interval > current_timestamp", nativeQuery = true)
    fun getUserActiveNotifications(id: Int): ArrayList<Int>
}
