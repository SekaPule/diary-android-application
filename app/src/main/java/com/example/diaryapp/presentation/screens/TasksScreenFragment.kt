package com.example.diaryapp.presentation.screens

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import com.example.diaryapp.R
import com.example.diaryapp.databinding.FragmentTasksScreenBinding
import com.example.diaryapp.domain.models.TaskModel
import com.example.diaryapp.presentation.fragments.NoTasksFragment
import com.example.diaryapp.presentation.fragments.TasksListFragment
import com.example.diaryapp.presentation.vm.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.util.*

const val ONE_MONTH_DIFFERENCE = 1

class TasksScreenFragment(private val activityContext: Context) : Fragment() {
    private lateinit var binding: FragmentTasksScreenBinding
    private val vm by sharedViewModel<MainViewModel>()
    private lateinit var observer: Observer<List<TaskModel>>

    private val calendar: Calendar = Calendar.getInstance()
    private val year = calendar.get(Calendar.YEAR)
    private val month = calendar.get(Calendar.MONTH)
    private val day = calendar.get(Calendar.DAY_OF_MONTH)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentTasksScreenBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observer = Observer {
            if (it.isEmpty()) {
                parentFragmentManager.commit {
                    replace(R.id.list_fragment_container, NoTasksFragment.newInstance())
                    setReorderingAllowed(true)
                }
            } else {
                parentFragmentManager.commit {
                    replace(
                        R.id.list_fragment_container,
                        TasksListFragment.newInstance(context = activityContext)
                    )
                    setReorderingAllowed(true)
                }
            }
        }

        if (vm.liveCalendarDayPosition.value?.day != null) {
            calendar.set(
                vm.liveCalendarDayPosition.value!!.year,
                vm.liveCalendarDayPosition.value!!.month - ONE_MONTH_DIFFERENCE,
                vm.liveCalendarDayPosition.value!!.day
            )
            binding.calendarView.date = calendar.timeInMillis
        } else {
            vm.storeCalendarDayPosition(
                day = day,
                month = month + ONE_MONTH_DIFFERENCE,
                year = year
            )
        }

        vm.loadTasks()
        vm.taskList()!!.observe(viewLifecycleOwner, observer)

        binding.calendarView.setOnDateChangeListener { _, mYear, mMonth, mDay ->
            vm.storeCalendarDayPosition(
                year = mYear,
                month = mMonth + ONE_MONTH_DIFFERENCE,
                day = mDay
            )
            vm.loadTasks()

            vm.taskList()!!.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                if (it.isEmpty()) {
                    parentFragmentManager.commit {
                        replace(R.id.list_fragment_container, NoTasksFragment.newInstance())
                        setReorderingAllowed(true)
                    }
                } else {
                    parentFragmentManager.commit {
                        replace(
                            R.id.list_fragment_container,
                            TasksListFragment.newInstance(context = activityContext)
                        )
                        setReorderingAllowed(true)
                    }
                }
            })
        }

        binding.addTaskButton.setOnClickListener {
            parentFragmentManager.commit {
                replace(R.id.screen_fragment_holder, AddTasksScreenFragment.newInstance())
                setReorderingAllowed(true)
                addToBackStack(null)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vm.taskList()!!.removeObserver(observer)
    }

    companion object {
        @JvmStatic
        fun newInstance(context: Context) = TasksScreenFragment(activityContext = context)
    }
}