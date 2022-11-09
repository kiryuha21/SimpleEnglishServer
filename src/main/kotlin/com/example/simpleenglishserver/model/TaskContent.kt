package com.example.simpleenglishserver.model

import com.vladmihalcea.hibernate.type.array.StringArrayType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import org.hibernate.annotations.TypeDef
import org.hibernate.annotations.Type
import java.sql.Timestamp
import javax.persistence.*

object TimestampSerializer : KSerializer<Timestamp> {
    override val descriptor = PrimitiveSerialDescriptor("Timestamp", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Timestamp {
        return Timestamp.valueOf(decoder.decodeString())
    }

    override fun serialize(encoder: Encoder, value: Timestamp) {
        encoder.encodeString(value.toString())
    }
}

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
                  var correctVariants: Array<String?>?,
                  @Type(type = "string-array")
                  @Column(
                      columnDefinition = "text[]",
                      name = "questions"
                  )
                  var questions: Array<String?>?,
                  @Column(columnDefinition = "text")
                  var musicURL: String?,
                  @Column(columnDefinition = "timestamp")
                  @Serializable(with = TimestampSerializer::class)
                  var memLastUpdate: Timestamp?,
                  @Column(columnDefinition = "interval")
                  var nextNoticeIn: String?) {
    constructor() : this(taskText="",
                         taskVariants=arrayOf(arrayOf<String?>()),
                         correctVariants = arrayOf<String?>(),
                         questions = arrayOf<String?>(),
                         musicURL = "",
                         memLastUpdate = null,
                         nextNoticeIn = null)

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null
}