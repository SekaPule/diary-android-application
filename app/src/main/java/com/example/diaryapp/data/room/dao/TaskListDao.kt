package com.example.diaryapp.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskListDao {

    @Query("SELECT * FROM ${TaskEntity.TABLE_NAME} WHERE date_start BETWEEN :dateTimestamp-$ONE_MILLI AND :dateTimestamp+$ONE_DAY_MILLI")
    fun loadTasksByDate(dateTimestamp: Long): LiveData<List<TaskEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(taskEntity: TaskEntity)

    @Delete(entity = TaskEntity::class)
    suspend fun deleteTask(taskEntity: TaskEntity)

    companion object {
        const val ONE_MILLI = 1
        const val ONE_DAY_MILLI = 86400
    }
}
