package com.example.tasksapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tasksapplication.R
import com.example.tasksapplication.databinding.FragmentTasksBinding
import com.example.tasksapplication.adapters.TaskListAdapter
import com.example.tasksapplication.ui.viewmodels.TaskViewModel

class TasksFragment : Fragment() {

    private var _binding: FragmentTasksBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var taskViewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTasksBinding.inflate(inflater, container, false)

        initViewModel()
        initRecyclerView()

        return binding.root
    }

    private fun initRecyclerView() {
        val recyclerView = binding.tasksRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)

        val taskAdapter = TaskListAdapter()
        recyclerView.adapter = taskAdapter

        taskAdapter.setViewModel(taskViewModel)

        taskViewModel.allTasks.observe(viewLifecycleOwner, {
            taskAdapter.setTasksList(it)
        })
    }

    private fun initViewModel() {
        taskViewModel = ViewModelProvider(requireActivity())[TaskViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_TasksFragment_to_AddTaskFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}