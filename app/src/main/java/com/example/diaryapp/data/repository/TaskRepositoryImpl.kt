package com.example.diaryapp.data.repository

import android.content.Context
import com.example.diaryapp.data.dataHandler.TaskJsonHandler
import com.example.diaryapp.domain.models.TaskModel
import com.example.diaryapp.domain.repository.TaskRepository
import com.example.diaryapp.presentation.activities.TASKS_JSON_FILE_NAME
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader

private const val JSON_ARRAY_NAME = "tasks"

class TaskRepositoryImpl(private val context: Context) : TaskRepository {

    override fun getTasksFromAssets(fileName: String): ArrayList<TaskModel> {
        return getTasksFromString(context
            .assets
            .open(TASKS_JSON_FILE_NAME)
            .bufferedReader()
            .use(BufferedReader::readText))
    }

        private fun getTasksFromString(tasksString: String): ArrayList<TaskModel> {
        val tasksList: ArrayList<TaskModel> = ArrayList()
        val jsonDecoder = TaskJsonHandler()

        try {
            val tasksJsonObject = JSONObject(tasksString)
            val tasksArray = tasksJsonObject.getJSONArray(JSON_ARRAY_NAME)

            for (tasksArrayItem in 0..tasksArray.length()) {
                val task = tasksArray.getJSONObject(tasksArrayItem)
                val taskDetails = jsonDecoder.convertFromJson(task)
                tasksList.add(taskDetails)
            }
        } catch (error: JSONException) {
            error.printStackTrace()
        }
        return tasksList
    }
}