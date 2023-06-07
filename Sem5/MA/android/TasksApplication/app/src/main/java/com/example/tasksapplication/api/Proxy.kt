package com.example.tasksapplication.api

import com.example.tasksapplication.model.Task
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Proxy {
    private const val BASE_URL = "http://10.0.2.2:5000/api/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient())
            .build()
    }

    private val service: RetrofitInterface by lazy {
        retrofit.create(RetrofitInterface::class.java)
    }

    private fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }

    fun getTasks(): Response<List<Task>> = service.getTasks().execute()

    fun addTask(task: Task): Response<Task> = service.addTask(task).execute()

    fun updateTask(id: Int, task: Task): Response<Task> = service.updateTask(id, task).execute()

    fun deleteTask(id: Int): Response<Boolean> = service.deleteTask(id).execute()
}