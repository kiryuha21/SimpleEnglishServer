package com.example.simpleenglishserver.repo

import com.example.simpleenglishserver.model.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User?, Int?>