package com.example.simpleenglishserver.model

import com.vladmihalcea.hibernate.type.array.IntArrayType
import kotlinx.serialization.Serializable
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import javax.persistence.*

@Serializable
@Entity
@Table(name = "User",  uniqueConstraints = [
    UniqueConstraint(name = "uc_user_username", columnNames = ["username"])
])
@TypeDef(name = "int-array", typeClass = IntArrayType::class)
class User(@Column var username: String,
           @Column var password: String,
           @Column var XP: Int,
           @Column(columnDefinition = "integer[]") @Type(type="int-array") var completedTasks: IntArray = intArrayOf()) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null

    @Column(nullable = true)
    var name: String? = null

    constructor() : this(username="-", password = "-", XP = 0)
}