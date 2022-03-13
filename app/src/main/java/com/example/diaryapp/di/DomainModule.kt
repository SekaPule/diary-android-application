package com.example.diaryapp.di

import com.example.diaryapp.domain.usecases.GetTasksUseCase
import org.koin.dsl.module

val domainModule = module {
    factory<GetTasksUseCase> {
        GetTasksUseCase(taskRepository = get())
    }
}
