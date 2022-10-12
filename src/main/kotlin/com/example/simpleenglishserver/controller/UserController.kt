package com.example.simpleenglishserver.controller

import com.example.simpleenglishserver.model.User
import com.example.simpleenglishserver.repo.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody


@Controller
class MyController {
    @Autowired
    var repo: UserRepository? = null

    @GetMapping("/")
    fun index(): String {
        return "index"
    }

    @PostMapping("/add")
    @ResponseBody
    fun addUser(@RequestParam username: String?, @RequestParam password: String?): String {
        val user = User(username, password)
        repo?.save(user) // insert
        return "Ok!"
    }

    @GetMapping("/all")
    @ResponseBody
    fun getAll(): MutableIterable<User?>? {
        return repo?.findAll()
    }
}