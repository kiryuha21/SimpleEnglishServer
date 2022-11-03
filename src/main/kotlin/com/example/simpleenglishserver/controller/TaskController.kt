package com.example.simpleenglishserver.controller

import com.example.simpleenglishserver.data.Constants
import com.example.simpleenglishserver.model.Task
import com.example.simpleenglishserver.repo.TaskRepository
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
class TaskController {
    @Autowired
    var repo: TaskRepository? = null

    @PostMapping("add_task")
    @ResponseBody
    fun addTask(@RequestParam type: String, @RequestParam content: String): Task? {
        return repo?.save(Task(type, content))
    }

    @DeleteMapping("remove_task_by_id")
    @ResponseBody
    fun removeTaskById(@RequestParam id: Int): String {
        repo?.deleteById(id)
        return "deleted task with id $id"
    }

    @PutMapping
    @ResponseBody
    fun updateTaskById(@RequestParam id: Int, @RequestParam stringTask: String): String {
        val task = repo?.findById(id)?.get() ?: return Constants.searchFailure

        val parsedTask = Json.decodeFromString<Task>(stringTask)
        if (parsedTask.taskType.isNotEmpty()) {
            task.taskType = parsedTask.taskType
        }
        if  (parsedTask.taskContent.isNotEmpty()) {
            task.taskContent = parsedTask.taskContent
        }
        repo?.save(task)

        return Constants.success
    }

    @PostMapping("find_tasks_by_type")
    @ResponseBody
    fun getTypedTasks(@RequestParam type: String): Iterable<Task?>? {
        return repo?.findTasksByTaskType(type)
    }

    @GetMapping("/get_all_tasks")
    @ResponseBody
    fun getAllTasks(): Iterable<Task?>? {
        return repo?.findAll()
    }
}