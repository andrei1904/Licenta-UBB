package com.example.tasksapplication.ui.fragments

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.tasksapplication.R

public class EditTaskFragmentDirections private constructor() {
  public companion object {
    public fun actionEditTaskFragmentToTasksFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_EditTaskFragment_to_TasksFragment)
  }
}
