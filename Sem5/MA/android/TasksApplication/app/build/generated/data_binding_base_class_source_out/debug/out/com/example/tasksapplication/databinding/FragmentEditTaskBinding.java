// Generated by view binder compiler. Do not edit!
package com.example.tasksapplication.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.example.tasksapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.slider.Slider;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentEditTaskBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final FloatingActionButton fab;

  @NonNull
  public final Slider taskProgressBar;

  @NonNull
  public final TextInputLayout textInputDescription;

  @NonNull
  public final TextInputLayout textInputDomain;

  @NonNull
  public final TextInputLayout textInputPriority;

  @NonNull
  public final TextInputLayout textInputTitle;

  @NonNull
  public final TextView textViewDeadline;

  @NonNull
  public final TextView textViewProgress;

  private FragmentEditTaskBinding(@NonNull ConstraintLayout rootView,
      @NonNull FloatingActionButton fab, @NonNull Slider taskProgressBar,
      @NonNull TextInputLayout textInputDescription, @NonNull TextInputLayout textInputDomain,
      @NonNull TextInputLayout textInputPriority, @NonNull TextInputLayout textInputTitle,
      @NonNull TextView textViewDeadline, @NonNull TextView textViewProgress) {
    this.rootView = rootView;
    this.fab = fab;
    this.taskProgressBar = taskProgressBar;
    this.textInputDescription = textInputDescription;
    this.textInputDomain = textInputDomain;
    this.textInputPriority = textInputPriority;
    this.textInputTitle = textInputTitle;
    this.textViewDeadline = textViewDeadline;
    this.textViewProgress = textViewProgress;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentEditTaskBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentEditTaskBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_edit_task, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentEditTaskBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.fab;
      FloatingActionButton fab = rootView.findViewById(id);
      if (fab == null) {
        break missingId;
      }

      id = R.id.task_progress_bar;
      Slider taskProgressBar = rootView.findViewById(id);
      if (taskProgressBar == null) {
        break missingId;
      }

      id = R.id.text_input_description;
      TextInputLayout textInputDescription = rootView.findViewById(id);
      if (textInputDescription == null) {
        break missingId;
      }

      id = R.id.text_input_domain;
      TextInputLayout textInputDomain = rootView.findViewById(id);
      if (textInputDomain == null) {
        break missingId;
      }

      id = R.id.text_input_priority;
      TextInputLayout textInputPriority = rootView.findViewById(id);
      if (textInputPriority == null) {
        break missingId;
      }

      id = R.id.text_input_title;
      TextInputLayout textInputTitle = rootView.findViewById(id);
      if (textInputTitle == null) {
        break missingId;
      }

      id = R.id.text_view_deadline;
      TextView textViewDeadline = rootView.findViewById(id);
      if (textViewDeadline == null) {
        break missingId;
      }

      id = R.id.text_view_progress;
      TextView textViewProgress = rootView.findViewById(id);
      if (textViewProgress == null) {
        break missingId;
      }

      return new FragmentEditTaskBinding((ConstraintLayout) rootView, fab, taskProgressBar,
          textInputDescription, textInputDomain, textInputPriority, textInputTitle,
          textViewDeadline, textViewProgress);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}