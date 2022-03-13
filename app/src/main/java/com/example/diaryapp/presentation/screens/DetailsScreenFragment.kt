package com.example.diaryapp.presentation.screens

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.diaryapp.databinding.FragmentDetailsScreenBinding
import com.example.diaryapp.domain.models.TaskModel

class DetailsScreenFragment(private val task: TaskModel, private val activityContext: Context) :
    Fragment() {

    private lateinit var binding: FragmentDetailsScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentDetailsScreenBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.detailsStartTime.text = task.dateStart.hour.toString()
        binding.detailsFinishTime.text = task.dateFinish.hour.toString()
        binding.detailsTaskName.text = task.name
        binding.detailsTaskDescription.text = task.description
        binding.homeButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(item: TaskModel, context: Context) = DetailsScreenFragment(task = item, activityContext = context)
    }
}