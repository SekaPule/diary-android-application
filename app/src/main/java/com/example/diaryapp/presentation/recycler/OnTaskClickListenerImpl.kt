package com.example.diaryapp.presentation.recycler

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.example.diaryapp.R
import com.example.diaryapp.domain.models.TaskModel
import com.example.diaryapp.presentation.screens.DetailsScreenFragment

class OnTaskClickListenerImpl(
    private val fragmentManager: FragmentManager,
    private val context: Context,
) : OnTaskClickListener {
    override fun onTaskItemClicked(item: TaskModel) {
        fragmentManager.commit {
            replace(R.id.screen_fragment_holder,DetailsScreenFragment.newInstance(item = item))
            setReorderingAllowed(true)
            addToBackStack("details")
        }
    }
}
