package com.example.diaryapp.presentation.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.diaryapp.R
import com.example.diaryapp.databinding.ActivityMainBinding
import com.example.diaryapp.presentation.screens.TasksScreenFragment

const val TASKS_JSON_FILE_NAME = "tasks.json"


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportFragmentManager.commit {
            replace(R.id.screen_fragment_holder, TasksScreenFragment.newInstance(applicationContext))
            setReorderingAllowed(true)
        }
    }
}
