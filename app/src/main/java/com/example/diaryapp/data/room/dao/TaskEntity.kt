package com.example.diaryapp.data.room.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.diaryapp.data.room.dao.TaskEntity.Companion.TABLE_NAME
import com.example.diaryapp.domain.models.TaskModel
import java.sql.Timestamp
import java.time.Instant
import java.time.ZoneId

@Entity(tableName = TABLE_NAME)
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "date_start")
    val dateTimeStart: Long,

    @ColumnInfo(name = "date_finish")
    val dateTimeFinish: Long,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "description")
    val description: String
) {
    companion object {
        const val TABLE_NAME = "task_list_entities_table"
    }
}

fun TaskEntity.toTaskModel() = TaskModel(
    id = id,
    dateTimeStart = Instant.ofEpochSecond(Timestamp(dateTimeStart).time)
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime(),
    dateTimeFinish = Instant.ofEpochSecond(Timestamp(dateTimeFinish).time)
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime(),
    name = name,
    description = description
)
