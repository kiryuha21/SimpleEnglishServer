package com.example.simpleenglishserver.model

import javax.persistence.*


@Entity
@Table(name = "User",  uniqueConstraints = [
    UniqueConstraint(name = "uc_user_username", columnNames = ["username"])
])
class User(var username: String, var password: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null

    constructor() : this(username="-", password = "-")
}