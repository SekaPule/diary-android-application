package com.example.diaryapp.domain.models

import java.io.Serializable
import java.time.LocalDateTime

data class TaskModel(
    val id: Int,
    val dateTimeStart: LocalDateTime,
    val dateTimeFinish: LocalDateTime,
    val name: String,
    val description: String
) : Serializable