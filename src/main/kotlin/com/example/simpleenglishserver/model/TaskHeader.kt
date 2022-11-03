package com.example.simpleenglishserver.model

import com.example.simpleenglishserver.data.Constants
import kotlinx.serialization.Serializable
import javax.persistence.*


@Serializable
@Entity
@Table(name = "TasksHeaders")
class TaskHeader(@Column var taskType: String?, @Column var pointsXP: Int?, @Column var description: String?) {
    constructor() : this(taskType = Constants.unknown, pointsXP = 0, description = "")

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "task_content_id", referencedColumnName = "id")
    var content: TaskContent? = null
}