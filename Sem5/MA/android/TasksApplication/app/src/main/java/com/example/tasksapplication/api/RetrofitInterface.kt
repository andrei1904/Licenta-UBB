package com.example.tasksapplication.api

import com.example.tasksapplication.model.Task
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*

interface RetrofitInterface {

    @GET("task")
    fun getTasks(): Call<List<Task>>

    @POST("task")
    fun addTask(@Body task: Task): Call<Task>

    @PUT("task/{id}")
    fun updateTask(@Path("id") id: Int, @Body task: Task): Call<Task>

    @DELETE("task/{id}")
    fun deleteTask(@Path("id") id: Int): Call<Boolean>
}