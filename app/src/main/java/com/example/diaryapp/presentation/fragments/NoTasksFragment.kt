package com.example.diaryapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.diaryapp.R
import com.example.diaryapp.databinding.FragmentNoTasksBinding

class NoTasksFragment : Fragment() {
    private lateinit var binding: FragmentNoTasksBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentNoTasksBinding.inflate(inflater)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = NoTasksFragment()
    }
}