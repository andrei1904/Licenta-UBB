package com.example.tasksapplication.ui.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tasksapplication.TasksApplication
import com.example.tasksapplication.database.TaskRoomDatabase
import com.example.tasksapplication.model.Task
import com.example.tasksapplication.repository.TasksRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TasksRepository

    val allTasks = MutableLiveData<List<Task>>()
    private val disposables: CompositeDisposable = CompositeDisposable()

    init {
        val tasksDao = TaskRoomDatabase.getDatabase(application, viewModelScope).taskDao()

        val connectivityManager = getApplication<TasksApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        repository = TasksRepository(tasksDao, connectivityManager)

        loadTasks()
    }

    private fun loadTasks() = viewModelScope.launch(Dispatchers.IO) {
        disposables.add(
            repository.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { allTasks.postValue(it) },
                    { Log.e("TaskViewModel", "Get all error", it) }
                )
        )
    }

    fun getById(taskId: Int): LiveData<Task> {
        return repository.getById(taskId)
    }

    fun add(task: Task) = viewModelScope.launch(Dispatchers.IO) {
        repository.add(task)
    }

    fun update(task: Task) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(task)
    }

    fun delete(taskId: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(taskId)
    }

    override fun onCleared() {
        disposables.clear()
    }
}