package com.example.simpleenglishserver.model

import kotlinx.serialization.Serializable
import javax.persistence.*

@Serializable
@Entity
@Table(name = "TasksContent")
class TaskContent(@Column(columnDefinition="text") var taskText: String?) {
    constructor() : this(taskText="")

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null
}