package com.example.diaryapp.presentation.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.diaryapp.databinding.ItemTaskLayoutBinding
import com.example.diaryapp.domain.models.TaskModel

class TaskAdapter(
    private val context: Context,
    private val items: ArrayList<TaskModel>,
    private val onTaskClickListener: OnTaskClickListener,
) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    class TaskViewHolder(val binding: ItemTaskLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemTaskLayoutBinding.inflate(LayoutInflater.from(context),
                parent,
                false
            ))
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {


        val item = items[position]

        holder.binding.taskStartTime.text = item.dateStart.hour.toString()
        holder.binding.taskFinishTime.text = item.dateFinish.hour.toString()
        holder.binding.taskName.text = item.name

        holder.itemView.setOnClickListener {
            onTaskClickListener.onTaskItemClicked(item)
        }

    }
    override fun getItemCount() = items.size
}