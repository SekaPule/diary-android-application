package com.example.diaryapp.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.diaryapp.domain.models.DateTaskModel
import com.example.diaryapp.domain.models.TaskModel
import com.example.diaryapp.domain.usecases.GetTasksUseCase
import com.example.diaryapp.presentation.activities.TASKS_JSON_FILE_NAME

open class MainViewModel(private val getTaskUseCase: GetTasksUseCase) : ViewModel() {

    private val mutableLiveTaskList: MutableLiveData<ArrayList<TaskModel>> by lazy {
        MutableLiveData<ArrayList<TaskModel>>()
    }
    val liveTaskList: LiveData<ArrayList<TaskModel>> = mutableLiveTaskList

    private val mutableLiveCalendarDayPosition: MutableLiveData<DateTaskModel> by lazy {
        MutableLiveData<DateTaskModel>()
    }
    val liveCalendarDayPosition: LiveData<DateTaskModel> = mutableLiveCalendarDayPosition

    fun storeCalendarDayPosition(year:Int, month:Int ,day : Int ){
        mutableLiveCalendarDayPosition.value = DateTaskModel(year = year, month = month, day = day)
    }

    fun getTasks() {
        mutableLiveTaskList.value = getTaskUseCase.execute(
            fileName = TASKS_JSON_FILE_NAME,
            dateTask = liveCalendarDayPosition.value!!
        )
    }
}