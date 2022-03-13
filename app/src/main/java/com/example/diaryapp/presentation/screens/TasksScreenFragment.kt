package com.example.diaryapp.presentation.screens

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.diaryapp.R
import com.example.diaryapp.databinding.FragmentTasksScreenBinding
import com.example.diaryapp.presentation.fragments.NoTasksFragment
import com.example.diaryapp.presentation.fragments.TasksListFragment
import com.example.diaryapp.presentation.vm.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.util.*

private const val ONE_MONTH_DIFFERENCE = 1

class TasksScreenFragment(private val activityContext: Context) : Fragment() {
    private lateinit var binding: FragmentTasksScreenBinding
    private val vm by sharedViewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentTasksScreenBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val calendar = Calendar.getInstance()

        if (vm.liveCalendarDayPosition.value?.day != null) {
            calendar.set(
                vm.liveCalendarDayPosition.value!!.year,
                vm.liveCalendarDayPosition.value!!.month - ONE_MONTH_DIFFERENCE,
                vm.liveCalendarDayPosition.value!!.day
            )
            binding.calendarView.date = calendar.timeInMillis
        }

        binding.calendarView.setOnDateChangeListener { _, year, month, day ->
            vm.storeCalendarDayPosition(year = year,
                month = month + ONE_MONTH_DIFFERENCE,
                day = day)
            vm.getTasks()

            if (vm.liveTaskList.value?.isEmpty() == true) {
                parentFragmentManager.commit {
                    replace(R.id.list_fragment_container, NoTasksFragment.newInstance())
                    setReorderingAllowed(true)
                }
            } else {
                parentFragmentManager.commit {
                    replace(R.id.list_fragment_container,
                        TasksListFragment.newInstance(context = activityContext))
                    setReorderingAllowed(true)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (vm.liveTaskList.value?.isEmpty() == true) {
            parentFragmentManager.commit {
                replace(R.id.list_fragment_container, NoTasksFragment.newInstance())
                setReorderingAllowed(true)
            }
        } else {
            parentFragmentManager.commit {
                replace(R.id.list_fragment_container,
                    TasksListFragment.newInstance(context = activityContext))
                setReorderingAllowed(true)
            }
        }
    }
    companion object {
        @JvmStatic
        fun newInstance(context: Context) = TasksScreenFragment(activityContext = context)
    }
}