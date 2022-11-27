package com.example.diaryapp.di

import com.example.diaryapp.data.repository.TaskRepositoryImpl
import com.example.diaryapp.domain.repository.TaskRepository
import org.koin.dsl.module

val dataModule = module {
    single<TaskRepository> {
        TaskRepositoryImpl(taskListDao = get())
    }
}