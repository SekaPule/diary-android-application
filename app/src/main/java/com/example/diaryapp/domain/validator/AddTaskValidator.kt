package com.example.diaryapp.domain.validator

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.example.diaryapp.R
import com.example.diaryapp.databinding.FragmentAddTasksScreenBinding

class AddTaskValidator(private val binding: FragmentAddTasksScreenBinding) {

    fun isValidate(): Boolean =
        validateTaskName() && validateTaskDescription() && validateTaskDate() && validateTaskTimeStart() && validateTaskTimeFinish()

    private fun validateTaskName(): Boolean {
        if (binding.taskName.text.toString().trim().isEmpty()) {
            binding.taskNameInputLayout.error = WARNING_TEXT
            binding.taskName.requestFocus()
            return false
        } else {
            binding.taskNameInputLayout.isErrorEnabled = false
        }
        return true
    }

    private fun validateTaskDescription(): Boolean {
        if (binding.taskDescription.text.toString().trim().isEmpty()) {
            binding.taskDescriptionInputLayout.error = WARNING_TEXT
            binding.taskDescription.requestFocus()
            return false
        } else {
            binding.taskDescriptionInputLayout.isErrorEnabled = false
        }
        return true
    }

    private fun validateTaskDate(): Boolean {
        if (binding.datePickerBtn.text==TASK_DATE_TEXT) {
            binding.datePickerBtnLayout.error = WARNING_TEXT
            binding.datePickerBtn.requestFocus()
            return false
        } else {
            binding.datePickerBtnLayout.isErrorEnabled = false
        }
        return true
    }

    private fun validateTaskTimeStart(): Boolean {
        if (binding.timeStartPickerBtn.text==START_TIME_TEXT) {
            binding.timeStartPickerBtnLayout.error = WARNING_TEXT
            binding.timeStartPickerBtn.requestFocus()
            return false
        } else {
            binding.timeStartPickerBtnLayout.isErrorEnabled = false
        }
        return true
    }

    private fun validateTaskTimeFinish(): Boolean {
        if (binding.timeFinishPickerBtn.text==FINISH_TIME_TEXT) {
            binding.timeFinishPickerBtnLayout.error = WARNING_TEXT
            binding.timeFinishPickerBtn.requestFocus()
            return false
        } else {
            binding.timeFinishPickerBtnLayout.isErrorEnabled = false
        }
        return true
    }

    fun setupListeners() {
        binding.taskName.addTextChangedListener(TextFieldValidation(binding.taskName))
        binding.taskDescription.addTextChangedListener(TextFieldValidation(binding.taskDescription))
        binding.datePickerBtn.addTextChangedListener(TextFieldValidation(binding.datePickerBtn))
        binding.timeStartPickerBtn.addTextChangedListener(TextFieldValidation(binding.timeStartPickerBtn))
        binding.timeFinishPickerBtn.addTextChangedListener(TextFieldValidation(binding.timeFinishPickerBtn))
    }

    inner class TextFieldValidation(private val view: View) : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            when (view.id) {
                R.id.task_name -> {
                    validateTaskName()
                }
                R.id.task_description -> {
                    validateTaskDescription()
                }
                R.id.date_picker_btn -> {
                    validateTaskDate()
                }
                R.id.time_start_picker_btn -> {
                    validateTaskTimeStart()
                }
                R.id.time_finish_picker_btn -> {
                    validateTaskTimeFinish()
                }
            }
        }
    }
    companion object{
        private const val WARNING_TEXT = "Required field"
        private const val TASK_DATE_TEXT = "Task Date"
        private const val FINISH_TIME_TEXT = "Finish Time"
        private const val START_TIME_TEXT = "Start Time"
    }
}