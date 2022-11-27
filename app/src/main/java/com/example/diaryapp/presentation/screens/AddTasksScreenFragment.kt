package com.example.diaryapp.presentation.screens

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.diaryapp.R
import com.example.diaryapp.databinding.FragmentAddTasksScreenBinding
import com.example.diaryapp.domain.validator.AddTaskValidator
import com.example.diaryapp.presentation.vm.AddTaskViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class AddTasksScreenFragment : Fragment() {

    private val vm by viewModel<AddTaskViewModel>()
    private lateinit var binding: FragmentAddTasksScreenBinding
    private lateinit var validator: AddTaskValidator

    private val calendar: Calendar = Calendar.getInstance()
    private val year = calendar.get(Calendar.YEAR)
    private val month = calendar.get(Calendar.MONTH)
    private val day = calendar.get(Calendar.DAY_OF_MONTH)
    private val hour = calendar.get(Calendar.HOUR)
    private val minute = calendar.get(Calendar.MINUTE)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAddTasksScreenBinding.inflate(inflater)
        validator = AddTaskValidator(binding)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        validator.setupListeners()

        binding.taskName.addTextChangedListener {
            vm.mutableLiveTaskName.value = it.toString().trim()
        }

        binding.taskDescription.addTextChangedListener {
            vm.mutableLiveTaskDescription.value = it.toString().trim()
        }

        binding.datePickerBtn.setOnClickListener {
            val dpDialog = DatePickerDialog(requireActivity(), { _, mYear, mMonth, mDay ->
                binding.datePickerBtn.text = "$mDay/${mMonth + ONE_MONTH_DIFFERENCE}/$mYear"
                vm.storeTaskDate(day = mDay, month = mMonth + ONE_MONTH_DIFFERENCE, year = mYear)
            }, year, month, day)

            dpDialog.show()
        }

        binding.timeStartPickerBtn.setOnClickListener {
            val tpDialog = TimePickerDialog(requireActivity(), { _, mHour, mMinute ->
                binding.timeStartPickerBtn.text = "$mHour:$mMinute"
                vm.storeTaskStartTime(hour = mHour, minute = mMinute)
            }, hour, minute, true)

            tpDialog.show()
        }

        binding.timeFinishPickerBtn.setOnClickListener {
            val tpDialog = TimePickerDialog(requireActivity(), { _, mHour, mMinute ->
                binding.timeFinishPickerBtn.text = "$mHour:$mMinute"
                vm.storeTaskFinishTime(hour = mHour, minute = mMinute)
            }, hour, minute, true)

            tpDialog.show()
        }

        binding.addTaskButton.setOnClickListener {
            if (validator.isValidate()) {
                vm.addTask()
                Toast.makeText(context, R.string.toast_success_message, Toast.LENGTH_SHORT).show()
            }
        }

        binding.homeButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
    companion object {
        @JvmStatic
        fun newInstance() = AddTasksScreenFragment()
    }
}