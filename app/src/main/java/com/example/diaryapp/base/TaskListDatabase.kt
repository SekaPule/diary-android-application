package com.example.diaryapp.base

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.diaryapp.data.room.dao.TaskEntity
import com.example.diaryapp.data.room.dao.TaskListDao

@Database(entities = [TaskEntity::class], version = 3, exportSchema = true)
abstract class TaskListDatabase : RoomDatabase() {
    abstract fun taskListDao(): TaskListDao
}