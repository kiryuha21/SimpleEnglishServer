package com.example.simpleenglishserver.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id


@Entity
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null
    var username: String? = null
    var password: String? = null

    constructor(name: String?, password: String?) {
        this.username = name
        this.password = password
    }

    constructor()
}