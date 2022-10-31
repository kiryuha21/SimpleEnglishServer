package com.example.simpleenglishserver.controller

import com.example.simpleenglishserver.JasyptConfig
import com.example.simpleenglishserver.data.Constants
import com.example.simpleenglishserver.model.User
import com.example.simpleenglishserver.repo.UserRepository
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
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
    fun updateUser(@RequestParam stringUser: String) : String {
        val parsedUser = Json.decodeFromString<User>(stringUser)

        val user = repo?.findUserByUsername(parsedUser.username) ?: return Constants.searchFailure

        user.name = parsedUser.name
        user.username = parsedUser.username
        user.password = jasypt.encrypt(parsedUser.password)
        repo?.save(user)

        return Constants.success
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

    @PostMapping("/find_by_username")
    @ResponseBody
    fun getByUsername(@RequestParam username: String): User? {
        return repo?.findUserByUsername(username)
    }

    @PostMapping("/auth")
    @ResponseBody
    fun authUser(@RequestParam username: String, @RequestParam password: String): String {
        val user = repo?.findUserByUsername(username) ?: return Constants.searchFailure
        if (jasypt.decrypt(user.password) == password) {
            return Constants.success
        }
        return Constants.wrongPassword
    }

    @PostMapping("/find_by_id")
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