package com.example.diaryapp.domain.models

import java.io.Serializable

data class TaskDateModel(
    var year: Int,
    var month: Int,
    var day: Int
) : Serializable