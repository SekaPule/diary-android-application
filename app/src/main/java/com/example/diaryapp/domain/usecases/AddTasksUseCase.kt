package com.example.diaryapp.domain.usecases

import com.example.diaryapp.data.room.dao.TaskEntity
import com.example.diaryapp.domain.repository.TaskRepository

class AddTasksUseCase(private val taskRepository: TaskRepository) {

    suspend fun execute(taskEntity: TaskEntity) {
        taskRepository.insertTask(taskEntity = taskEntity)
    }
}