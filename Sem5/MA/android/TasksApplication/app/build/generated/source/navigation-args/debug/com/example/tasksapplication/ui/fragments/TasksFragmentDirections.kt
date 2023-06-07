package com.example.tasksapplication.ui.fragments

import android.os.Bundle
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.tasksapplication.R
import kotlin.Int

public class TasksFragmentDirections private constructor() {
  private data class ActionTasksFragmentToEditTaskFragment(
    public val taskId: Int = 0
  ) : NavDirections {
    public override fun getActionId(): Int = R.id.action_TasksFragment_to_EditTaskFragment

    public override fun getArguments(): Bundle {
      val result = Bundle()
      result.putInt("taskId", this.taskId)
      return result
    }
  }

  public companion object {
    public fun actionTasksFragmentToAddTaskFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_TasksFragment_to_AddTaskFragment)

    public fun actionTasksFragmentToEditTaskFragment(taskId: Int = 0): NavDirections =
        ActionTasksFragmentToEditTaskFragment(taskId)
  }
}
