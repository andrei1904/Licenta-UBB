package com.example.tasksapplication.ui.fragments

import android.os.Bundle
import androidx.navigation.NavArgs
import kotlin.Int
import kotlin.jvm.JvmStatic

public data class EditTaskFragmentArgs(
  public val taskId: Int = 0
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putInt("taskId", this.taskId)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): EditTaskFragmentArgs {
      bundle.setClassLoader(EditTaskFragmentArgs::class.java.classLoader)
      val __taskId : Int
      if (bundle.containsKey("taskId")) {
        __taskId = bundle.getInt("taskId")
      } else {
        __taskId = 0
      }
      return EditTaskFragmentArgs(__taskId)
    }
  }
}
