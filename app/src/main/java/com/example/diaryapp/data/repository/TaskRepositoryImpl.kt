package com.example.diaryapp.data.repository

import androidx.lifecycle.LiveData
import com.example.diaryapp.data.room.dao.TaskEntity
import com.example.diaryapp.data.room.dao.TaskListDao
import com.example.diaryapp.domain.repository.TaskRepository

class TaskRepositoryImpl(
    private val taskListDao: TaskListDao
) : TaskRepository {

    override fun loadTasksByDate(dateTimestamp: Long): LiveData<List<TaskEntity>> =
        taskListDao.loadTasksByDate(dateTimestamp = dateTimestamp)

    override suspend fun insertTask(taskEntity: TaskEntity) {
        taskListDao.addTask(taskEntity = taskEntity)
    }
}