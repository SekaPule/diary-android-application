package com.example.diaryapp.domain.repository

import androidx.lifecycle.LiveData
import com.example.diaryapp.data.room.dao.TaskEntity

interface TaskRepository {

    fun loadTasksByDate(dateTimestamp: Long): LiveData<List<TaskEntity>>

    suspend fun insertTask(taskEntity: TaskEntity)
}
