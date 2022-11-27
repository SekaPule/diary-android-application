package com.example.diaryapp.di

import android.app.Application
import androidx.room.Room
import com.example.diaryapp.base.TaskListDatabase
import com.example.diaryapp.data.room.dao.TaskListDao
import org.koin.dsl.module


private fun provideTaskListDatabase(application: Application): TaskListDatabase =
    Room.databaseBuilder(application, TaskListDatabase::class.java, CONST.TABLE_NAME.key)
        .fallbackToDestructiveMigration()
        .build()


private fun provideTaskListDao(database: TaskListDatabase): TaskListDao = database.taskListDao()

val taskListDatabaseModule = module {
    single<TaskListDatabase> {
        provideTaskListDatabase(application = get())
    }
    single<TaskListDao> {
        provideTaskListDao(database = get())
    }
}

enum class CONST(val key: String){
    TABLE_NAME(key = "task_room_database")
}

