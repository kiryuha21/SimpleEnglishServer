package com.example.simpleenglishserver.model

import kotlinx.serialization.Serializable
import javax.persistence.*


@Serializable
@Entity
@Table(name = "User",  uniqueConstraints = [
    UniqueConstraint(name = "uc_user_username", columnNames = ["username"])
])
class User(@Column var username: String, @Column var password: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null

    @Column(nullable = true)
    var name: String? = null

    constructor(username: String, password: String, name : String) : this(username, password) {
        this.name = name
    }

    constructor() : this(username="-", password = "-")
}