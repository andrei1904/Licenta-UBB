package com.example.tasksapplication.repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0087@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u0019\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000eH\u0087@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ\u001d\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00120\u0011H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013J\u0016\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\n0\u00152\u0006\u0010\r\u001a\u00020\u000eH\u0007J\u0014\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00120\u0011H\u0002J\u0014\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00120\u0011H\u0002J\u0019\u0010\u0018\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0087@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0019"}, d2 = {"Lcom/example/tasksapplication/repository/TasksRepository;", "", "taskDao", "Lcom/example/tasksapplication/database/dao/TaskDao;", "connectivityManager", "Landroid/net/ConnectivityManager;", "(Lcom/example/tasksapplication/database/dao/TaskDao;Landroid/net/ConnectivityManager;)V", "add", "", "task", "Lcom/example/tasksapplication/model/Task;", "(Lcom/example/tasksapplication/model/Task;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delete", "taskId", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAll", "Lio/reactivex/Observable;", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getById", "Landroidx/lifecycle/LiveData;", "getFromApi", "getFromDb", "update", "app_debug"})
public final class TasksRepository {
    private final com.example.tasksapplication.database.dao.TaskDao taskDao = null;
    private final android.net.ConnectivityManager connectivityManager = null;
    
    public TasksRepository(@org.jetbrains.annotations.NotNull()
    com.example.tasksapplication.database.dao.TaskDao taskDao, @org.jetbrains.annotations.NotNull()
    android.net.ConnectivityManager connectivityManager) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super io.reactivex.Observable<java.util.List<com.example.tasksapplication.model.Task>>> p0) {
        return null;
    }
    
    private final io.reactivex.Observable<java.util.List<com.example.tasksapplication.model.Task>> getFromDb() {
        return null;
    }
    
    private final io.reactivex.Observable<java.util.List<com.example.tasksapplication.model.Task>> getFromApi() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @androidx.annotation.WorkerThread()
    @kotlin.Suppress(names = {"RedundantSuspendModifier"})
    public final java.lang.Object add(@org.jetbrains.annotations.NotNull()
    com.example.tasksapplication.model.Task task, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p1) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @androidx.annotation.WorkerThread()
    @kotlin.Suppress(names = {"RedundantSuspendModifier"})
    public final java.lang.Object update(@org.jetbrains.annotations.NotNull()
    com.example.tasksapplication.model.Task task, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p1) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @androidx.annotation.WorkerThread()
    @kotlin.Suppress(names = {"RedundantSuspendModifier"})
    public final java.lang.Object delete(int taskId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> p1) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @androidx.annotation.WorkerThread()
    public final androidx.lifecycle.LiveData<com.example.tasksapplication.model.Task> getById(int taskId) {
        return null;
    }
}