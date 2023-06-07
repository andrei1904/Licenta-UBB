package com.example.tasksapplication.repository

import android.net.ConnectivityManager
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.tasksapplication.api.accesLayers.TaskAccesLayer
import com.example.tasksapplication.database.dao.TaskDao
import com.example.tasksapplication.model.Task
import com.example.tasksapplication.utils.InternetConnection
import io.reactivex.Observable
import kotlin.streams.toList

class TasksRepository(
    private val taskDao: TaskDao,
    private val connectivityManager: ConnectivityManager
) {

    suspend fun getAll(): Observable<List<Task>> {

        val hasConnection = InternetConnection(connectivityManager).isConnectedToInternet()

        val observableFromDb = getFromDb()
        val observableFromApi = getFromApi()

        return if (hasConnection) {
            Observable.concatArrayEager(observableFromApi, observableFromDb)
        } else {
            observableFromDb
        }

    }

    private fun getFromDb(): Observable<List<Task>> {
        return taskDao.getAll()
    }

    private fun getFromApi(): Observable<List<Task>> {
        return TaskAccesLayer.getTasksObservable()
            .map { result ->
                result.map { task ->
                    Task(
                        id = task.id,
                        domain = task.domain,
                        title = task.title,
                        description = task.description,
                        createdTime = task.createdTime,
                        priority = task.priority,
                        deadline = task.deadline,
                        status = task.status,
                        progress = task.progress
                    )
                }
            }
            .toObservable()
            .doOnNext {
                taskDao.deleteIfNotPresent(it.stream().map { task -> task.id }.toList())
                taskDao.insertAll(it)
            }
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun add(task: Task) {

        val taskResult = TaskAccesLayer.addTask(task)

        if (taskResult != null) {
            taskDao.insert(taskResult)
        }
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(task: Task) {

        val taskResult = TaskAccesLayer.updateTask(task)

        if (taskResult != null) {
            taskDao.update(taskResult)
        }
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(taskId: Int) {

        val result = TaskAccesLayer.deleteTask(taskId)

        if (result != null && result == true) {
            taskDao.delete(taskId)
        }
    }

    @WorkerThread
    fun getById(taskId: Int): LiveData<Task> {
        return taskDao.getById(taskId)
    }

}