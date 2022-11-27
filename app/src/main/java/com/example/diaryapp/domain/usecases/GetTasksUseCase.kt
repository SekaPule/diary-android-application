package com.example.diaryapp.domain.usecases

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.map
import com.example.diaryapp.data.room.dao.TaskEntity
import com.example.diaryapp.data.room.dao.toTaskModel
import com.example.diaryapp.domain.models.TaskModel
import com.example.diaryapp.domain.repository.TaskRepository


class GetTasksUseCase(private val taskRepository: TaskRepository) {

    fun execute(dateTimestamp: Long): LiveData<List<TaskModel>> =
        Transformations.map(taskRepository.loadTasksByDate(dateTimestamp = dateTimestamp)) { entity ->
            entity.map {
                it.toTaskModel()
            }
        }
}