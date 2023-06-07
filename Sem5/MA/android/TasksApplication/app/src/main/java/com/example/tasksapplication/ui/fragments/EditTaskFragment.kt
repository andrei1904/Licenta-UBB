package com.example.tasksapplication.ui.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tasksapplication.R
import com.example.tasksapplication.ui.viewmodels.TaskViewModel
import com.example.tasksapplication.databinding.FragmentEditTaskBinding
import com.example.tasksapplication.model.Priority
import com.example.tasksapplication.model.Status
import com.example.tasksapplication.model.Task
import com.example.tasksapplication.utils.Constants
import com.google.android.material.slider.Slider
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.*

class EditTaskFragment : Fragment() {

    private var _binding: FragmentEditTaskBinding? = null

    private lateinit var textInputDomain: TextInputLayout
    private lateinit var textInputTitle: TextInputLayout
    private lateinit var textInputPriority: TextInputLayout
    private lateinit var textViewDeadline: TextView
    private lateinit var textInputDescription: TextInputLayout
    private lateinit var sliderProgress: Slider
    private lateinit var textViewProgress: TextView
    private var taskId: Int = 0
    private var taskCreatedTime: Long = 0

    private lateinit var taskViewModel: TaskViewModel

    private lateinit var calendar: Calendar

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditTaskBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textInputDomain = binding.textInputDomain
        textInputTitle = binding.textInputTitle
        textInputPriority = binding.textInputPriority
        textViewDeadline = binding.textViewDeadline
        textInputDescription = binding.textInputDescription
        sliderProgress = binding.taskProgressBar
        textViewProgress = binding.textViewProgress

        binding.fab.setOnClickListener {
            updateTask()
        }

        taskViewModel = ViewModelProvider(requireActivity())[TaskViewModel::class.java]

        calendar = Calendar.getInstance()
        pickDateTime()

        initElements()
    }

    private fun initElements() {

        (textInputPriority.editText as? AutoCompleteTextView)?.setAdapter(
            ArrayAdapter(
                requireContext(),
                R.layout.list_item,
                listOf(Priority.LOW.value, Priority.MEDIUM.value, Priority.HIGH.value)
            )
        )

        sliderProgress.addOnChangeListener { _, value, _ ->
            textViewProgress.text = view?.resources?.getString(R.string.task_done, value.toInt())
        }

        taskId = arguments?.getInt("taskId")!!
        taskViewModel.getById(taskId).observe(viewLifecycleOwner, { task ->
            loadTaskData(task)
        })
    }

    private fun loadTaskData(task: Task) {
        textInputDomain.editText?.setText(task.domain)
        textInputTitle.editText?.setText(task.title)
        (textInputPriority.editText as? AutoCompleteTextView)?.setText(
            task.priority.value,
            false
        )
        textViewDeadline.text = getDateTimeFromMillis(
            task.deadline,
            SimpleDateFormat(
                Constants.DATE_TIME_FORMAT_DEADLINE,
                Locale.ENGLISH
            )
        )
        textInputTitle.editText?.setText(task.title)
        textInputDescription.editText?.setText(task.description)
        textViewProgress.text = view?.resources?.getString(R.string.task_done, task.progress)
        sliderProgress.value = task.progress.toFloat()
        taskCreatedTime = task.createdTime

        calendar.timeInMillis = task.deadline
    }

    private fun updateTask() {
        if (!validateInputs()) {
            Toast.makeText(context, "Add data", Toast.LENGTH_LONG).show()
            return
        }

        val progress = sliderProgress.value.toInt()
        val taskStatus: Status = when {
            progress == 0 -> {
                Status.NEW
            }
            progress < 100 -> {
                Status.IN_PROGRESS
            }
            else -> {
                Status.DONE
            }
        }

        val taskPriority: Priority = when {
            textInputPriority.editText?.text.toString() == "Low priority" -> {
                Priority.LOW
            }
            textInputPriority.editText?.text.toString() == "Medium priority" -> {
                Priority.MEDIUM
            }
            else -> {
                Priority.HIGH
            }
        }

        val task = Task(
            taskId,
            textInputDomain.editText?.text.toString(),
            textInputTitle.editText?.text.toString(),
            textInputDescription.editText?.text.toString(),
            taskCreatedTime,
            taskPriority,
            calendar.timeInMillis,
            taskStatus,
            sliderProgress.value.toInt()
        )

        taskViewModel.update(task)
        findNavController().navigate(EditTaskFragmentDirections.actionEditTaskFragmentToTasksFragment())
    }

    private fun validateInputs(): Boolean {
        if (textInputDomain.editText?.text.toString() != "" &&
            textInputTitle.editText?.text.toString() != "" &&
            textInputPriority.editText?.text.toString() != "" &&
            textInputDescription.editText?.text.toString() != "" &&
            textViewDeadline.text != ""
        ) {
            return true
        }
        return false
    }

    private fun getDateTimeFromMillis(millis: Long, formatter: SimpleDateFormat): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = millis

        return formatter.format(calendar.time)
    }

    private fun pickDateTime() {
        val currentDateTime = Calendar.getInstance()
        val startYear = currentDateTime.get(Calendar.YEAR)
        val startMonth = currentDateTime.get(Calendar.MONTH)
        val startDay = currentDateTime.get(Calendar.DAY_OF_MONTH)
        val startHour = currentDateTime.get(Calendar.HOUR_OF_DAY)
        val startMinute = currentDateTime.get(Calendar.MINUTE)

        textViewDeadline.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                { _, year, month, day ->
                    TimePickerDialog(
                        requireContext(),
                        { _, hour, minute ->
                            calendar.set(year, month, day, hour, minute)
                            textViewDeadline.text = getDateTimeFromMillis(
                                calendar.timeInMillis,
                                SimpleDateFormat(
                                    Constants.DATE_TIME_FORMAT_DEADLINE,
                                    Locale.ENGLISH
                                )
                            )
                        },
                        startHour,
                        startMinute,
                        false
                    ).show()
                },
                startYear,
                startMonth,
                startDay
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}