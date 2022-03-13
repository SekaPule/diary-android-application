package com.example.diaryapp.domain.models

data class TaskJsonModel(
    var id: Int,
    var dateStart: Int,
    var dateFinish: Int,
    var name: String,
    var description: String
)