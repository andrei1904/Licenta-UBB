package com.example.tasksapplication.api;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0004H\'J\u0018\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u00032\b\b\u0001\u0010\b\u001a\u00020\tH\'J\u0014\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u000b0\u0003H\'J\"\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\b\u001a\u00020\t2\b\b\u0001\u0010\u0005\u001a\u00020\u0004H\'\u00a8\u0006\r"}, d2 = {"Lcom/example/tasksapplication/api/RetrofitInterface;", "", "addTask", "Lretrofit2/Call;", "Lcom/example/tasksapplication/model/Task;", "task", "deleteTask", "", "id", "", "getTasks", "", "updateTask", "app_debug"})
public abstract interface RetrofitInterface {
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "task")
    public abstract retrofit2.Call<java.util.List<com.example.tasksapplication.model.Task>> getTasks();
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "task")
    public abstract retrofit2.Call<com.example.tasksapplication.model.Task> addTask(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.example.tasksapplication.model.Task task);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.PUT(value = "task/{id}")
    public abstract retrofit2.Call<com.example.tasksapplication.model.Task> updateTask(@retrofit2.http.Path(value = "id")
    int id, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.example.tasksapplication.model.Task task);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.DELETE(value = "task/{id}")
    public abstract retrofit2.Call<java.lang.Boolean> deleteTask(@retrofit2.http.Path(value = "id")
    int id);
}