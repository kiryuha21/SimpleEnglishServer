package com.example.simpleenglishserver.controller

import com.example.simpleenglishserver.JasyptConfig
import com.example.simpleenglishserver.model.User
import com.example.simpleenglishserver.repo.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.util.*


@Controller
class MyController {
    @Autowired
    var repo: UserRepository? = null

    val jasypt = JasyptConfig().getPasswordEncryptor()

    @GetMapping("/")
    @ResponseBody
    fun index(): String {
        return "welcome page"
    }

    @PostMapping("/add")
    @ResponseBody
    fun addUser(@RequestParam username: String, @RequestParam password: String): User? {
        return repo?.save(User(username, jasypt.encrypt(password))) // insert
    }

    @PutMapping("/update")
    @ResponseBody
    fun updateUser(@RequestParam id : Int, @RequestParam username: String, @RequestParam password: String) {
        val user = repo?.findById(id)?.get()
        if (user != null) {
            user.username = username
            user.password = jasypt.encrypt(password)
            repo?.save(user)
        }
    }

    @DeleteMapping("/destroy")
    @ResponseBody
    fun deleteUser(@RequestParam id : Int) : String {
        repo?.deleteById(id)
        return "deleted user with id $id"
    }

    @DeleteMapping("/destroy_all")
    @ResponseBody
    fun deleteAllUsers() : String {
        repo?.deleteAll()
        return "All users were deleted"
    }

    @PostMapping("/get_by_name")
    @ResponseBody
    fun getByUsername(@RequestParam username: String): User? {
        return repo?.findUserByUsername(username)
    }

    @PostMapping("/auth")
    @ResponseBody
    fun authUser(@RequestParam username: String, @RequestParam password: String): String {
        val user = repo?.findUserByUsername(username) ?: return ""
        if (jasypt.decrypt(user.password) == password) {
            return "success"
        }
        return "wrongPassword"
    }

    @PostMapping("/get_by_id")
    @ResponseBody
    fun getById(@RequestParam id: Int): Optional<User?>? {
        return repo?.findById(id)
    }

    @GetMapping("/all")
    @ResponseBody
    fun getAll(): MutableIterable<User?>? {
        return repo?.findAll()
    }
}