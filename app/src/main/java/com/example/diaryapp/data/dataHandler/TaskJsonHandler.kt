package com.example.diaryapp.data.dataHandler

import com.example.diaryapp.domain.models.TaskModel
import org.json.JSONObject
import java.sql.Timestamp
import java.time.Instant
import java.time.ZoneId

class TaskJsonHandler {
    fun convertFromJson(json: JSONObject): TaskModel {
        return TaskModel(
            json.getInt(JsonKeys.ID.key),
            Instant
                .ofEpochSecond(Timestamp(json.getLong(JsonKeys.DATE_FINISH.key)).time)
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime(),
            Instant
                .ofEpochSecond(Timestamp(json.getLong(JsonKeys.DATE_START.key)).time)
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime(),
            json.getString(JsonKeys.NAME.key),
            json.getString(JsonKeys.DESCRIPTION.key)
        )
    }

    enum class JsonKeys(val key: String) {
        ID(key = "id"),
        DATE_START(key="date_start"),
        DATE_FINISH(key="date_finish"),
        NAME(key="name"),
        DESCRIPTION(key="description"),
    }
}
