package com.example.simpleenglishserver.controller

import com.example.simpleenglishserver.data.Constants
import com.example.simpleenglishserver.model.TaskHeader
import com.example.simpleenglishserver.repo.TaskHeaderRepository
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class TaskHeaderController {
    @Autowired
    var repo: TaskHeaderRepository? = null

    @PostMapping("/add_task_header")
    @ResponseBody
    fun addTaskHeader(@RequestParam stringTaskHeader: String): TaskHeader? {
        val taskHeader = Json.decodeFromString<TaskHeader>(stringTaskHeader)
        return repo?.save(taskHeader)
    }

    @DeleteMapping("/remove_task_header_by_id")
    @ResponseBody
    fun removeTaskHeaderById(@RequestParam id: Int): String {
        repo?.deleteById(id)
        return "deleted task header with id $id"
    }

    @PutMapping("/update_task_header_by_id")
    @ResponseBody
    fun updateTaskHeaderById(@RequestParam id: Int, @RequestParam stringTask: String): String {
        val task = repo?.findById(id)?.get() ?: return Constants.searchFailure

        val parsedTask = Json.decodeFromString<TaskHeader>(stringTask)
        if (!parsedTask.taskType.isNullOrEmpty()) {
            task.taskType = parsedTask.taskType
        }
        if (parsedTask.pointsXP != null) {
            task.pointsXP = parsedTask.pointsXP
        }
        if (!parsedTask.description.isNullOrEmpty()) {
            task.description = parsedTask.description
        }
        repo?.save(task)

        return Constants.success
    }

    @PostMapping("/find_task_headers_by_type")
    @ResponseBody
    fun findTypedTaskHeaders(@RequestParam type: String): Iterable<TaskHeader?>? {
        return repo?.findTaskHeadersByTaskTypeAndContentNotNull(type)
    }

    @DeleteMapping("/delete_task_header_by_id")
    @ResponseBody
    fun deleteTaskHeaderById(@RequestParam id: Int): String {
        repo?.deleteById(id)
        return "Deleted task header with id $id"
    }

    @GetMapping("/get_all_task_headers")
    @ResponseBody
    fun getAllTaskHeaders(): Iterable<TaskHeader?>? {
        return repo?.findAll()
    }
}