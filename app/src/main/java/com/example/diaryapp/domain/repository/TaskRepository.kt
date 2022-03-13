package com.example.diaryapp.domain.repository

import com.example.diaryapp.domain.models.TaskModel

interface TaskRepository {
    fun getTasksFromAssets(fileName: String): ArrayList<TaskModel>
}