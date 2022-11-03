package com.example.simpleenglishserver.model

import com.example.simpleenglishserver.data.Constants
import kotlinx.serialization.Serializable
import javax.persistence.*

@Serializable
@Entity
@Table(name = "Tasks")
class Task(@Column var taskType: String, @Column(columnDefinition = "TEXT") var taskContent: String) {
    constructor() : this(taskType= Constants.unknown, taskContent="-")

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null
}