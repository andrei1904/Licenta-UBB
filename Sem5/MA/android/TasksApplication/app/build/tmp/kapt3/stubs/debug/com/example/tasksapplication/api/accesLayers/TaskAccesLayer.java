package com.example.tasksapplication.api.accesLayers;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0004J\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0012\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\r0\fJ\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0004\u00a8\u0006\u000f"}, d2 = {"Lcom/example/tasksapplication/api/accesLayers/TaskAccesLayer;", "", "()V", "addTask", "Lcom/example/tasksapplication/model/Task;", "task", "deleteTask", "", "id", "", "(I)Ljava/lang/Boolean;", "getTasksObservable", "Lio/reactivex/Single;", "", "updateTask", "app_debug"})
public final class TaskAccesLayer {
    @org.jetbrains.annotations.NotNull()
    public static final com.example.tasksapplication.api.accesLayers.TaskAccesLayer INSTANCE = null;
    
    private TaskAccesLayer() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Single<java.util.List<com.example.tasksapplication.model.Task>> getTasksObservable() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.example.tasksapplication.model.Task addTask(@org.jetbrains.annotations.NotNull()
    com.example.tasksapplication.model.Task task) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.example.tasksapplication.model.Task updateTask(@org.jetbrains.annotations.NotNull()
    com.example.tasksapplication.model.Task task) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Boolean deleteTask(int id) {
        return null;
    }
}