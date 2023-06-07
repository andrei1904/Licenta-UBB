package com.example.tasksapplication.ui.fragments;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\u0006\u0010\u0017\u001a\u00020\u000bJ\u0018\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u0016H\u0002J\u0012\u0010\u001f\u001a\u00020\u00162\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J$\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\'2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\b\u0010(\u001a\u00020\u0016H\u0016J\u001a\u0010)\u001a\u00020\u00162\u0006\u0010*\u001a\u00020#2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\b\u0010+\u001a\u00020\u0016H\u0002J\b\u0010,\u001a\u00020\u0016H\u0002J\b\u0010-\u001a\u00020\u000bH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006."}, d2 = {"Lcom/example/tasksapplication/ui/fragments/AddTaskFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/example/tasksapplication/databinding/FragmentAddTaskBinding;", "binding", "getBinding", "()Lcom/example/tasksapplication/databinding/FragmentAddTaskBinding;", "calendar", "Ljava/util/Calendar;", "selectedDeadlnie", "", "taskViewModel", "Lcom/example/tasksapplication/ui/viewmodels/TaskViewModel;", "textInputDescription", "Lcom/google/android/material/textfield/TextInputLayout;", "textInputDomain", "textInputPriority", "textInputTitle", "textViewDeadline", "Landroid/widget/TextView;", "addTask", "", "checkEmptyInputs", "getDateTimeFromMillis", "", "millis", "", "formatter", "Ljava/text/SimpleDateFormat;", "initPriorityDropdownMenu", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroyView", "onViewCreated", "view", "pickDateTime", "setBackPressAction", "validateInputs", "app_debug"})
public final class AddTaskFragment extends androidx.fragment.app.Fragment {
    private com.example.tasksapplication.databinding.FragmentAddTaskBinding _binding;
    private com.google.android.material.textfield.TextInputLayout textInputDomain;
    private com.google.android.material.textfield.TextInputLayout textInputTitle;
    private com.google.android.material.textfield.TextInputLayout textInputPriority;
    private com.google.android.material.textfield.TextInputLayout textInputDescription;
    private android.widget.TextView textViewDeadline;
    private boolean selectedDeadlnie = false;
    private com.example.tasksapplication.ui.viewmodels.TaskViewModel taskViewModel;
    private java.util.Calendar calendar;
    
    public AddTaskFragment() {
        super();
    }
    
    private final com.example.tasksapplication.databinding.FragmentAddTaskBinding getBinding() {
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
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void initPriorityDropdownMenu() {
    }
    
    private final void addTask() {
    }
    
    private final void setBackPressAction() {
    }
    
    public final boolean checkEmptyInputs() {
        return false;
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