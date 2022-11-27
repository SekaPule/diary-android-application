package com.example.diaryapp.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.diaryapp.domain.models.TaskDateModel
import com.example.diaryapp.domain.models.TaskModel
import com.example.diaryapp.domain.usecases.GetTasksUseCase
import java.time.LocalDate
import java.time.ZoneId

open class MainViewModel(
    private val getTaskUseCase: GetTasksUseCase
) : ViewModel() {

    private var _taskList: LiveData<List<TaskModel>>? = null

    fun taskList(): LiveData<List<TaskModel>>? = _taskList

    private val mutableLiveCalendarDayPosition: MutableLiveData<TaskDateModel> by lazy {
        MutableLiveData<TaskDateModel>()
    }
    val liveCalendarDayPosition: LiveData<TaskDateModel> = mutableLiveCalendarDayPosition

    fun storeCalendarDayPosition(year: Int, month: Int, day: Int) {
        mutableLiveCalendarDayPosition.value = TaskDateModel(year = year, month = month, day = day)
    }

    fun loadTasks() {
        val dateTime = LocalDate.of(
            liveCalendarDayPosition.value!!.year,
            liveCalendarDayPosition.value!!.month,
            liveCalendarDayPosition.value!!.day
        )

        _taskList = getTaskUseCase.execute(
            dateTimestamp = dateTime.atStartOfDay(ZoneId.systemDefault()).toEpochSecond()
        )
    }
}