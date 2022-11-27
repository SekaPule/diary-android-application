package com.example.diaryapp.di

import com.example.diaryapp.presentation.vm.AddTaskViewModel
import com.example.diaryapp.presentation.vm.MainViewModel
import org.koin.dsl.module

val appModule = module {
    single<MainViewModel> {
        MainViewModel(getTaskUseCase = get())
    }

    single<AddTaskViewModel> {
        AddTaskViewModel(addTasksUseCase = get())
    }
}