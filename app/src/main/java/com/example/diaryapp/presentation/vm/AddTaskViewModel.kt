package com.example.diaryapp.presentation.vm

import android.app.ActivityManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diaryapp.data.room.dao.TaskEntity
import com.example.diaryapp.domain.models.TaskDateModel
import com.example.diaryapp.domain.models.TaskTimeModel
import com.example.diaryapp.domain.usecases.AddTasksUseCase
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.ZoneId

class AddTaskViewModel(private val addTasksUseCase: AddTasksUseCase) : ViewModel() {


    val mutableLiveTaskName: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun storeTaskName(taskName: String){
        mutableLiveTaskName.value = taskName
    }

    val mutableLiveTaskDescription: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun storeTaskDescription(taskDescription: String){
        mutableLiveTaskName.value = taskDescription
    }

    val mutableLiveTaskDate: MutableLiveData<TaskDateModel> by lazy {
        MutableLiveData<TaskDateModel>()
    }

    val mutableTaskStartTime: MutableLiveData<TaskTimeModel> by lazy {
        MutableLiveData<TaskTimeModel>()
    }
    val mutableTaskFinishTime: MutableLiveData<TaskTimeModel> by lazy {
        MutableLiveData<TaskTimeModel>()
    }

    fun storeTaskDate(day: Int, month: Int, year: Int) {
        mutableLiveTaskDate.value = TaskDateModel(day = day, month = month, year = year)
    }

    fun storeTaskStartTime(hour: Int, minute: Int) {
        mutableTaskStartTime.value = TaskTimeModel(hour = hour, minute = minute)
    }

    fun storeTaskFinishTime(hour: Int, minute: Int) {
        mutableTaskFinishTime.value = TaskTimeModel(hour = hour, minute = minute)
    }


    fun addTask() {

        val dateTimeStart = LocalDateTime.of(
            mutableLiveTaskDate.value!!.year,
            mutableLiveTaskDate.value!!.month,
            mutableLiveTaskDate.value!!.day,
            mutableTaskStartTime.value!!.hour,
            mutableTaskStartTime.value!!.minute
        )

        val dateTimeFinish = LocalDateTime.of(
            mutableLiveTaskDate.value!!.year,
            mutableLiveTaskDate.value!!.month,
            mutableLiveTaskDate.value!!.day,
            mutableTaskFinishTime.value!!.hour,
            mutableTaskFinishTime.value!!.minute
        )

        val taskEntity = TaskEntity(
            name = mutableLiveTaskName.value!!,
            description = mutableLiveTaskDescription.value!!,
            dateTimeStart = dateTimeStart.atZone(ZoneId.systemDefault()).toEpochSecond(),
            dateTimeFinish = dateTimeFinish.atZone(ZoneId.systemDefault()).toEpochSecond()
        )

        viewModelScope.launch {
            addTasksUseCase.execute(taskEntity = taskEntity)
        }
    }
}