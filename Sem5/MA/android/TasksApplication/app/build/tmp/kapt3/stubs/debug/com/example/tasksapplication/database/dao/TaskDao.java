package com.example.tasksapplication.database.dao;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\b\u0010\u0006\u001a\u00020\u0003H\'J\u0016\u0010\u0007\u001a\u00020\u00032\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tH\'J\u0014\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\t0\u000bH\'J\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000e2\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\fH\'J\u0016\u0010\u0011\u001a\u00020\u00032\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\f0\tH\'J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\fH\'\u00a8\u0006\u0014"}, d2 = {"Lcom/example/tasksapplication/database/dao/TaskDao;", "", "delete", "", "taskId", "", "deleteAll", "deleteIfNotPresent", "tasksIds", "", "getAll", "Lio/reactivex/Observable;", "Lcom/example/tasksapplication/model/Task;", "getById", "Landroidx/lifecycle/LiveData;", "insert", "task", "insertAll", "tasks", "update", "app_debug"})
public abstract interface TaskDao {
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM tasks ORDER BY title ASC")
    public abstract io.reactivex.Observable<java.util.List<com.example.tasksapplication.model.Task>> getAll();
    
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract void insert(@org.jetbrains.annotations.NotNull()
    com.example.tasksapplication.model.Task task);
    
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract void insertAll(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.tasksapplication.model.Task> tasks);
    
    @androidx.room.Query(value = "DELETE FROM tasks WHERE id =:taskId")
    public abstract void delete(int taskId);
    
    @androidx.room.Update()
    public abstract void update(@org.jetbrains.annotations.NotNull()
    com.example.tasksapplication.model.Task task);
    
    @androidx.room.Query(value = "DELETE FROM tasks")
    public abstract void deleteAll();
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM tasks WHERE id =:taskId LIMIT 1")
    public abstract androidx.lifecycle.LiveData<com.example.tasksapplication.model.Task> getById(int taskId);
    
    @androidx.room.Query(value = "DELETE FROM tasks WHERE id NOT IN (:tasksIds)")
    public abstract void deleteIfNotPresent(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Integer> tasksIds);
}