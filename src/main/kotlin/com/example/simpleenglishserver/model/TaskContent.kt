package com.example.simpleenglishserver.model

import com.vladmihalcea.hibernate.type.array.StringArrayType
import kotlinx.serialization.Serializable
import org.hibernate.annotations.TypeDef
import org.hibernate.annotations.Type
import javax.persistence.*

@Serializable
@Entity
@Table(name = "TasksContent")
@TypeDef(name = "string-array", typeClass = StringArrayType::class)
class TaskContent(@Column(columnDefinition="text") var taskText: String?,
                  @Type(
                      type = "string-array",
                      parameters = [org.hibernate.annotations.Parameter(
                          name = "sql-array-type",
                          value = "task_variant"
                      )]
                  )
                  @Column(
                      name = "task_variants",
                      columnDefinition = "text[][]"
                  )
                  var taskVariants: Array<Array<String?>?>?,
                  @Type(type = "string-array")
                  @Column(
                      columnDefinition = "text[]",
                      name = "correct_variants"
                  )
                  var correctVariants: Array<String?>?) {
    constructor() : this(taskText="", taskVariants=arrayOf(arrayOf<String?>()), correctVariants = arrayOf<String?>())

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null
}