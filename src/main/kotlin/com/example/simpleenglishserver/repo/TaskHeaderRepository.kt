package com.example.simpleenglishserver.repo

import com.example.simpleenglishserver.model.TaskHeader
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface TaskHeaderRepository : CrudRepository<TaskHeader?, Int?> {
    fun findTaskHeadersByTaskTypeAndContentNotNull(type: String): Iterable<TaskHeader?>?

    @Query(value = "select temp.description, content.task_text\n" +
            "from tasks_content content,\n" +
            "(select header.id id, header.description description, header.task_content_id content_id\n" +
            "from \"user\", tasks_headers header\n" +
            "where \"user\".id = ?1 and\n" +
            "header.id = any(\"user\".started_memories)) temp\n" +
            "where content.mem_last_update + next_notice_in < current_timestamp and " +
            "temp.content_id = content.id;", nativeQuery = true)
    fun getUserActiveNotifications(id: Int): Iterable<Pair<String?, String?>?>?
}
