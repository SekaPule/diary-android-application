package com.example.diaryapp.di

import com.example.diaryapp.presentation.fragments.TasksListFragment
import com.example.diaryapp.presentation.screens.AddTasksScreenFragment
import com.example.diaryapp.presentation.vm.MainViewModel
import org.koin.dsl.module

val appModule = module {
    single<MainViewModel> {
        MainViewModel(getTaskUseCase = get())
    }

    single<TasksListFragment>{
        TasksListFragment(activityContext = get())
    }
}