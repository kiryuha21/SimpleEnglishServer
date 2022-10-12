package com.example.simpleenglishserver.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id


@Entity
data class User(var username: String, var password: String) {
    constructor() : this(username="user", password = "1234") {
        this.username += "$this.id"
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null
}