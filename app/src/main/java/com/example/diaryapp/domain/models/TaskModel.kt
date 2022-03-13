package com.example.diaryapp.domain.models

import java.io.Serializable
import java.time.LocalDateTime

data class TaskModel(
    var id: Int,
    var dateStart: LocalDateTime,
    var dateFinish: LocalDateTime,
    var name: String,
    var description: String
) : Serializable