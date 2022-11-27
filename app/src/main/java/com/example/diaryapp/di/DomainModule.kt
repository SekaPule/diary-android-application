package com.example.diaryapp.di

import com.example.diaryapp.domain.usecases.AddTasksUseCase
import com.example.diaryapp.domain.usecases.GetTasksUseCase
import org.koin.dsl.module

val domainModule = module {
    single<GetTasksUseCase> {
        GetTasksUseCase(taskRepository = get())
    }
    single<AddTasksUseCase> {
        AddTasksUseCase(taskRepository = get())
    }
}
