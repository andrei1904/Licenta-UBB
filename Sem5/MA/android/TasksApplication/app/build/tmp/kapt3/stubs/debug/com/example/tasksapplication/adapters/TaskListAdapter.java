package com.example.tasksapplication.adapters;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u0019B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0014\u0010\t\u001a\u00020\n2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u000bJ\u0010\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u000eH\u0016J\u001c\u0010\u0010\u001a\u00020\n2\n\u0010\u0011\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u000eH\u0016J\u001c\u0010\u0013\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000eH\u0016J\u0014\u0010\u0017\u001a\u00020\n2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u000bJ\u000e\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0004\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/example/tasksapplication/adapters/TaskListAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/example/tasksapplication/adapters/TaskListAdapter$TaskViewHolder;", "()V", "taskViewModel", "Lcom/example/tasksapplication/ui/viewmodels/TaskViewModel;", "tasks", "", "Lcom/example/tasksapplication/model/Task;", "addTasks", "", "", "deleteTask", "id", "", "getItemCount", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setTasksList", "setViewModel", "TaskViewHolder", "app_debug"})
public final class TaskListAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.example.tasksapplication.adapters.TaskListAdapter.TaskViewHolder> {
    private java.util.List<com.example.tasksapplication.model.Task> tasks;
    private com.example.tasksapplication.ui.viewmodels.TaskViewModel taskViewModel;
    
    public TaskListAdapter() {
        super();
    }
    
    public final void setTasksList(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.tasksapplication.model.Task> tasks) {
    }
    
    public final void addTasks(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.tasksapplication.model.Task> tasks) {
    }
    
    public final void setViewModel(@org.jetbrains.annotations.NotNull()
    com.example.tasksapplication.ui.viewmodels.TaskViewModel taskViewModel) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.example.tasksapplication.adapters.TaskListAdapter.TaskViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.example.tasksapplication.adapters.TaskListAdapter.TaskViewHolder holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    private final void deleteTask(int id) {
    }
    
    @kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aJ\u0010\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\u0018\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\nH\u0002J\u0010\u0010#\u001a\u00020\u00182\u0006\u0010$\u001a\u00020%H\u0002J \u0010#\u001a\u00020\u00182\u0006\u0010&\u001a\u00020\u001d2\u0006\u0010\'\u001a\u00020\u001d2\u0006\u0010(\u001a\u00020\u001dH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006)"}, d2 = {"Lcom/example/tasksapplication/adapters/TaskListAdapter$TaskViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/example/tasksapplication/databinding/TaskItemBinding;", "(Lcom/example/tasksapplication/adapters/TaskListAdapter;Lcom/example/tasksapplication/databinding/TaskItemBinding;)V", "buttonDelete", "Landroid/widget/ImageButton;", "cardView", "Landroidx/cardview/widget/CardView;", "dateTimeFormatCreatedAt", "Ljava/text/SimpleDateFormat;", "dateTimeFormatDeadline", "progressBar", "Landroid/widget/ProgressBar;", "textViewDeadline", "Landroid/widget/TextView;", "textViewDescription", "textViewDomain", "textViewPriority", "textViewProgress", "textViewStatus", "textViewTimeCreatedValue", "textViewTitle", "bind", "", "task", "Lcom/example/tasksapplication/model/Task;", "deleteTaskAlert", "id", "", "getDateTimeFromMillis", "", "millis", "", "formatter", "setStatus", "status", "Lcom/example/tasksapplication/model/Status;", "backgroundColorId", "progressBarColorId", "statusBackgroundColorId", "app_debug"})
    public final class TaskViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        private final android.widget.TextView textViewDomain = null;
        private final android.widget.TextView textViewTitle = null;
        private final android.widget.TextView textViewTimeCreatedValue = null;
        private final android.widget.TextView textViewDeadline = null;
        private final android.widget.TextView textViewDescription = null;
        private final android.widget.TextView textViewStatus = null;
        private final android.widget.TextView textViewProgress = null;
        private final android.widget.ProgressBar progressBar = null;
        private final android.widget.TextView textViewPriority = null;
        private final androidx.cardview.widget.CardView cardView = null;
        private final android.widget.ImageButton buttonDelete = null;
        private final java.text.SimpleDateFormat dateTimeFormatCreatedAt = null;
        private final java.text.SimpleDateFormat dateTimeFormatDeadline = null;
        
        public TaskViewHolder(@org.jetbrains.annotations.NotNull()
        com.example.tasksapplication.databinding.TaskItemBinding binding) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.example.tasksapplication.model.Task task) {
        }
        
        private final java.lang.String getDateTimeFromMillis(long millis, java.text.SimpleDateFormat formatter) {
            return null;
        }
        
        private final void setStatus(com.example.tasksapplication.model.Status status) {
        }
        
        private final void setStatus(int backgroundColorId, int progressBarColorId, int statusBackgroundColorId) {
        }
        
        private final void deleteTaskAlert(int id) {
        }
    }
}