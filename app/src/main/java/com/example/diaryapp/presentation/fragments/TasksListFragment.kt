package com.example.diaryapp.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diaryapp.databinding.FragmentTasksListBinding
import com.example.diaryapp.domain.models.TaskModel
import com.example.diaryapp.presentation.recycler.OnTaskClickListenerImpl
import com.example.diaryapp.presentation.recycler.TaskAdapter
import com.example.diaryapp.presentation.vm.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TasksListFragment(private val activityContext: Context) : Fragment() {
    private lateinit var binding : FragmentTasksListBinding
    private lateinit var observer: Observer<List<TaskModel>>
    private val onTaskClickListener by lazy {
        OnTaskClickListenerImpl(parentFragmentManager,
            activityContext)
    }

    lateinit var itemAdapter: TaskAdapter
    private val vm by sharedViewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentTasksListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (vm.liveCalendarDayPosition.value?.day != null) {
            itemAdapter = TaskAdapter(
                context = activityContext,
                items = if(vm.taskList()!!.value == null) listOf() else vm.taskList()!!.value!!,
                onTaskClickListener = onTaskClickListener
            )
            binding.rvTasksList.adapter = itemAdapter
        }

        observer = Observer {
            itemAdapter = TaskAdapter(context = activityContext,
                items = it ?: listOf(),
                onTaskClickListener = onTaskClickListener)
            binding.rvTasksList.adapter = itemAdapter
        }
        binding.rvTasksList.layoutManager = LinearLayoutManager(activityContext)

       vm.taskList()!!.observe(viewLifecycleOwner, observer)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vm.taskList()!!.removeObserver(observer)
    }

    companion object {

        @JvmStatic
        fun newInstance(context: Context) = TasksListFragment(activityContext = context)
    }
}