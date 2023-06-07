package com.example.tasksapplication.api.accesLayers

import android.util.Log
import com.example.tasksapplication.api.Proxy
import com.example.tasksapplication.model.Task
import io.reactivex.Single

object TaskAccesLayer {

    fun getTasksObservable(): Single<List<Task>> = Single.create {
        Log.d("Server opperation", "Get tasks")
        val response = Proxy.getTasks()

        if (!response.isSuccessful) {
            it.onError(Exception("Failed to get tasks! Error code: " + response.code()))
        } else {
            response.body()?.let { it1 -> it.onSuccess(it1) }
        }
    }

    fun addTask(task: Task): Task? {
        Log.d("Server opperation", "Add task")
        val response = Proxy.addTask(task)

        return if (!response.isSuccessful) {
            null
        } else {
            response.body()
        }
    }

    fun updateTask(task: Task): Task? {
        Log.d("Server opperation", "Update task")
        val response = Proxy.updateTask(task.id, task)

        return if (!response.isSuccessful) {
            null
        } else {
            response.body()
        }
    }

    fun deleteTask(id: Int): Boolean? {
        Log.d("Server opperation", "Delete task")
        val response = Proxy.deleteTask(id)

        return if (!response.isSuccessful) {
            false
        } else {
            response.body()
        }
    }
}