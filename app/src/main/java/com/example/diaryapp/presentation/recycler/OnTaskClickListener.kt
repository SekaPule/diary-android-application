package com.example.diaryapp.presentation.recycler

import com.example.diaryapp.domain.models.TaskModel

interface OnTaskClickListener {
    fun onTaskItemClicked(item: TaskModel)
}