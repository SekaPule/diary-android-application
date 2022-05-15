package com.example.diaryapp.presentation.screens

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import com.example.diaryapp.databinding.FragmentAddTasksScreenBinding
import java.util.*

class AddTasksScreenFragment : Fragment() {
    private lateinit var binding: FragmentAddTasksScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAddTasksScreenBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val hour = calendar.get(Calendar.HOUR)
        val minute = calendar.get(Calendar.MINUTE)

        binding.datePickerBtn.setOnClickListener {
            val dpDialog = DatePickerDialog(requireActivity(), { _, mYear, mMonth, mDay ->
                binding.datePickerBtn.text = "$mDay/${mMonth+1}/$mYear"
            }, year, month, day)

            dpDialog.show()
        }

        binding.timePickerBtn.setOnClickListener {
            val tpDialog = TimePickerDialog(requireActivity(), { _, mHour, mMinute ->
                binding.timePickerBtn.text = "$mHour:$mMinute"
            },hour,minute,true)

            tpDialog.show()
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = AddTasksScreenFragment()
    }
}