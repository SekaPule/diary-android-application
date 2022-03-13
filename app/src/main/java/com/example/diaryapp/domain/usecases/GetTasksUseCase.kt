package com.example.diaryapp.domain.usecases

import com.example.diaryapp.domain.models.DateTaskModel
import com.example.diaryapp.domain.models.TaskModel
import com.example.diaryapp.domain.repository.TaskRepository


class GetTasksUseCase(private val taskRepository: TaskRepository) {

    fun execute(fileName: String, dateTask: DateTaskModel): ArrayList<TaskModel> {

        val fullTasksArray = taskRepository.getTasksFromAssets(fileName = fileName)
        val selectedByDateTasks: ArrayList<TaskModel> = ArrayList()

        for (i in 0 until fullTasksArray.size) {
            if (fullTasksArray[i].dateStart.year == dateTask.year &&
                fullTasksArray[i].dateStart.monthValue == dateTask.month &&
                fullTasksArray[i].dateStart.dayOfMonth == dateTask.day
            ) {
                selectedByDateTasks.add(fullTasksArray[i])
            }
        }

        return selectedByDateTasks
    }
}