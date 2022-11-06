package com.example.simpleenglishserver.controller

import com.example.simpleenglishserver.data.Constants
import com.example.simpleenglishserver.model.TaskContent
import com.example.simpleenglishserver.repo.TaskContentRepository
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
class TaskContentController {
    @Autowired
    var repo: TaskContentRepository? = null

    @PostMapping("/add_task_content")
    @ResponseBody
    fun addTaskContent(@RequestParam text: String,
                       @RequestParam variants: Array<Array<String?>?>?,
                       @RequestParam correctAnswers: Array<String?>?,
                       @RequestParam questions: Array<String?>?,
                       @RequestParam musicURL: String?): TaskContent? {
        return repo?.save(TaskContent(text, variants, correctAnswers, questions, musicURL))
    }

    @DeleteMapping("/remove_task_content_by_id")
    @ResponseBody
    fun removeTaskContentById(@RequestParam id: Int): String {
        repo?.deleteById(id)
        return "deleted task content with id $id"
    }

    @PutMapping("/update_task_content_by_id")
    @ResponseBody
    fun updateTaskContentById(@RequestParam id: Int, @RequestParam stringTask: String): String {
        val task = repo?.findById(id)?.get() ?: return Constants.searchFailure

        val parsedTask = Json.decodeFromString<TaskContent>(stringTask)
        if (!parsedTask.taskText.isNullOrEmpty()) {
            task.taskText = parsedTask.taskText
        }
        if (!parsedTask.taskVariants.isNullOrEmpty()) {
            task.taskVariants = parsedTask.taskVariants
        }
        if (!parsedTask.correctVariants.isNullOrEmpty()) {
            task.correctVariants = parsedTask.correctVariants
        }
        if (!parsedTask.questions.isNullOrEmpty()) {
            task.questions = parsedTask.questions
        }
        if (!parsedTask.musicURL.isNullOrEmpty()) {
            task.musicURL = parsedTask.musicURL
        }
        repo?.save(task)

        return Constants.success
    }

    @GetMapping("/get_all_task_contents")
    @ResponseBody
    fun getAllTaskContents(): Iterable<TaskContent?>? {
        return repo?.findAll()
    }
}