package com.example.tasksapplication.api;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0014\u001a\u00020\u0013J\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u00122\u0006\u0010\u0017\u001a\u00020\u0018J\u0012\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u001a0\u0012J\b\u0010\u001b\u001a\u00020\u001cH\u0002J\u001c\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0014\u001a\u00020\u0013R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R#\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u001e"}, d2 = {"Lcom/example/tasksapplication/api/Proxy;", "", "()V", "BASE_URL", "", "retrofit", "Lretrofit2/Retrofit;", "kotlin.jvm.PlatformType", "getRetrofit", "()Lretrofit2/Retrofit;", "retrofit$delegate", "Lkotlin/Lazy;", "service", "Lcom/example/tasksapplication/api/RetrofitInterface;", "getService", "()Lcom/example/tasksapplication/api/RetrofitInterface;", "service$delegate", "addTask", "Lretrofit2/Response;", "Lcom/example/tasksapplication/model/Task;", "task", "deleteTask", "", "id", "", "getTasks", "", "okHttpClient", "Lokhttp3/OkHttpClient;", "updateTask", "app_debug"})
public final class Proxy {
    @org.jetbrains.annotations.NotNull()
    public static final com.example.tasksapplication.api.Proxy INSTANCE = null;
    private static final java.lang.String BASE_URL = "http://10.0.2.2:5000/api/";
    private static final kotlin.Lazy retrofit$delegate = null;
    private static final kotlin.Lazy service$delegate = null;
    
    private Proxy() {
        super();
    }
    
    private final retrofit2.Retrofit getRetrofit() {
        return null;
    }
    
    private final com.example.tasksapplication.api.RetrofitInterface getService() {
        return null;
    }
    
    private final okhttp3.OkHttpClient okHttpClient() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final retrofit2.Response<java.util.List<com.example.tasksapplication.model.Task>> getTasks() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final retrofit2.Response<com.example.tasksapplication.model.Task> addTask(@org.jetbrains.annotations.NotNull()
    com.example.tasksapplication.model.Task task) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final retrofit2.Response<com.example.tasksapplication.model.Task> updateTask(int id, @org.jetbrains.annotations.NotNull()
    com.example.tasksapplication.model.Task task) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final retrofit2.Response<java.lang.Boolean> deleteTask(int id) {
        return null;
    }
}