package com.example.tasksapplication.ui.fragments

import android.app.AlertDialog
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
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tasksapplication.R
import com.example.tasksapplication.databinding.FragmentAddTaskBinding
import com.example.tasksapplication.model.Priority
import com.example.tasksapplication.model.Status
import com.example.tasksapplication.model.Task
import com.example.tasksapplication.ui.viewmodels.TaskViewModel
import com.example.tasksapplication.utils.Constants
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.*

class AddTaskFragment : Fragment() {

    private var _binding: FragmentAddTaskBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var textInputDomain: TextInputLayout
    private lateinit var textInputTitle: TextInputLayout
    private lateinit var textInputPriority: TextInputLayout
    private lateinit var textInputDescription: TextInputLayout
    private lateinit var textViewDeadline: TextView
    private var selectedDeadlnie = false

    private lateinit var taskViewModel: TaskViewModel

    private lateinit var calendar: Calendar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddTaskBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textInputDomain = binding.textInputDomain
        textInputTitle = binding.textInputTitle
        textInputDescription = binding.textInputDescription
        textInputPriority = binding.textInputPriority
        textViewDeadline = binding.textViewDeadline

        initPriorityDropdownMenu()

        binding.fab.setOnClickListener {
            addTask()
        }

        taskViewModel = ViewModelProvider(requireActivity())[TaskViewModel::class.java]

        calendar = Calendar.getInstance()
        pickDateTime()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setBackPressAction()
    }

    private fun initPriorityDropdownMenu() {
        (textInputPriority.editText as? AutoCompleteTextView)?.setAdapter(
            ArrayAdapter(
                requireContext(),
                R.layout.list_item,
                listOf(Priority.LOW.value, Priority.MEDIUM.value, Priority.HIGH.value)
            )
        )
    }

    private fun addTask() {
        if (!validateInputs()) {
            Toast.makeText(context, "Add data", Toast.LENGTH_LONG).show()
            return
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
            domain = textInputDomain.editText?.text.toString(),
            title = textInputTitle.editText?.text.toString(),
            description = textInputDescription.editText?.text.toString(),
            createdTime = System.currentTimeMillis(),
            priority = taskPriority,
            deadline = calendar.timeInMillis,
            status = Status.NEW,
            progress = 0
        )
        taskViewModel.add(task)
        findNavController().navigate(AddTaskFragmentDirections.actionAddTaskFragmentToTasksFragment())
    }

    // if the user added data an alert will show asking if he wants to leave
    private fun setBackPressAction() {
        activity?.onBackPressedDispatcher
            ?.addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {

                    if (checkEmptyInputs()) {
                        AlertDialog.Builder(context)
                            .setTitle("You will lose the data")
                            .setMessage("Do you really want to leave this page?")
                            .setIcon(R.drawable.ic_baseline_warning_24)
                            .setPositiveButton("Yes") { _, _ ->
                                isEnabled = false
                                activity?.onBackPressed()
                            }
                            .setNegativeButton("No") { _, _ ->
                            }
                            .show()
                    } else {
                        isEnabled = false
                        activity?.onBackPressed()
                    }
                }
            })
    }

    fun checkEmptyInputs(): Boolean {

        if (textInputDomain.editText?.text!!.isNotBlank() ||
            textInputTitle.editText?.text!!.isNotBlank() ||
            textInputPriority.editText?.text!!.isNotBlank() ||
            textInputDescription.editText?.text!!.isNotBlank() || selectedDeadlnie
        ) {
            return true
        }
        return false
    }

    private fun validateInputs(): Boolean {
        if (textInputDomain.editText?.text.toString() != "" &&
            textInputTitle.editText?.text.toString() != "" &&
            textInputPriority.editText?.text.toString() != "" &&
            textInputDescription.editText?.text.toString() != "" &&
            selectedDeadlnie
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
                            selectedDeadlnie = true
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