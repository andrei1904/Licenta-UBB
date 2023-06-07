package com.example.tasksapplication.ui.fragments

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.tasksapplication.R

public class AddTaskFragmentDirections private constructor() {
  public companion object {
    public fun actionAddTaskFragmentToTasksFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_AddTaskFragment_to_TasksFragment)
  }
}
