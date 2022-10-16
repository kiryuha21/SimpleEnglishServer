package com.example.simpleenglishserver.model

import javax.persistence.*


@Entity
class User(@Column(unique=true)var username: String, var password: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null

    constructor() : this(username="-", password = "-")
}