package com.example.tasksapplication.ui.fragments;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\b\u0010\u001f\u001a\u00020 H\u0002J\u0010\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020#H\u0002J$\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\'2\b\u0010(\u001a\u0004\u0018\u00010)2\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\b\u0010,\u001a\u00020 H\u0016J\u001a\u0010-\u001a\u00020 2\u0006\u0010.\u001a\u00020%2\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\b\u0010/\u001a\u00020 H\u0002J\b\u00100\u001a\u00020 H\u0002J\b\u00101\u001a\u000202H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0013X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0013X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0018X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u00063"}, d2 = {"Lcom/example/tasksapplication/ui/fragments/EditTaskFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/example/tasksapplication/databinding/FragmentEditTaskBinding;", "binding", "getBinding", "()Lcom/example/tasksapplication/databinding/FragmentEditTaskBinding;", "calendar", "Ljava/util/Calendar;", "sliderProgress", "Lcom/google/android/material/slider/Slider;", "taskCreatedTime", "", "taskId", "", "taskViewModel", "Lcom/example/tasksapplication/ui/viewmodels/TaskViewModel;", "textInputDescription", "Lcom/google/android/material/textfield/TextInputLayout;", "textInputDomain", "textInputPriority", "textInputTitle", "textViewDeadline", "Landroid/widget/TextView;", "textViewProgress", "getDateTimeFromMillis", "", "millis", "formatter", "Ljava/text/SimpleDateFormat;", "initElements", "", "loadTaskData", "task", "Lcom/example/tasksapplication/model/Task;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "pickDateTime", "updateTask", "validateInputs", "", "app_debug"})
public final class EditTaskFragment extends androidx.fragment.app.Fragment {
    private com.example.tasksapplication.databinding.FragmentEditTaskBinding _binding;
    private com.google.android.material.textfield.TextInputLayout textInputDomain;
    private com.google.android.material.textfield.TextInputLayout textInputTitle;
    private com.google.android.material.textfield.TextInputLayout textInputPriority;
    private android.widget.TextView textViewDeadline;
    private com.google.android.material.textfield.TextInputLayout textInputDescription;
    private com.google.android.material.slider.Slider sliderProgress;
    private android.widget.TextView textViewProgress;
    private int taskId = 0;
    private long taskCreatedTime = 0L;
    private com.example.tasksapplication.ui.viewmodels.TaskViewModel taskViewModel;
    private java.util.Calendar calendar;
    
    public EditTaskFragment() {
        super();
    }
    
    private final com.example.tasksapplication.databinding.FragmentEditTaskBinding getBinding() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void initElements() {
    }
    
    private final void loadTaskData(com.example.tasksapplication.model.Task task) {
    }
    
    private final void updateTask() {
    }
    
    private final boolean validateInputs() {
        return false;
    }
    
    private final java.lang.String getDateTimeFromMillis(long millis, java.text.SimpleDateFormat formatter) {
        return null;
    }
    
    private final void pickDateTime() {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
}