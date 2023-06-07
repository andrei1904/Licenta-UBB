package com.example.tasksapplication.adapters

import android.app.AlertDialog
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.tasksapplication.R
import com.example.tasksapplication.ui.viewmodels.TaskViewModel
import com.example.tasksapplication.databinding.TaskItemBinding
import com.example.tasksapplication.ui.fragments.TasksFragmentDirections
import com.example.tasksapplication.model.Status
import com.example.tasksapplication.model.Task
import com.example.tasksapplication.utils.Constants
import java.text.SimpleDateFormat
import java.util.*

class TaskListAdapter :
    RecyclerView.Adapter<TaskListAdapter.TaskViewHolder>() {

    private var tasks = mutableListOf<Task>()
    private lateinit var taskViewModel: TaskViewModel

    fun setTasksList(tasks: List<Task>) {
        this.tasks = tasks.toMutableList()
        notifyDataSetChanged()
    }

    fun addTasks(tasks: List<Task>) {
        val initPosition = this.tasks.size
        this.tasks.addAll(tasks)
        notifyItemRangeChanged(initPosition, this.tasks.size)
    }

    fun setViewModel(taskViewModel: TaskViewModel) {
        this.taskViewModel = taskViewModel
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = TaskItemBinding.inflate(inflater, parent, false)

        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    override fun getItemCount() = tasks.size

    private fun deleteTask(id: Int) {
        taskViewModel.delete(id)
        notifyDataSetChanged()
    }

    inner class TaskViewHolder(binding: TaskItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val textViewDomain = binding.textViewDomain
        private val textViewTitle = binding.textViewTitle
        private val textViewTimeCreatedValue = binding.textViewTimeCreatedValue
        private val textViewDeadline = binding.textViewDeadlineDate
        private val textViewDescription = binding.textViewDescription
        private val textViewStatus = binding.textViewStatus
        private val textViewProgress = binding.textViewProgress
        private val progressBar = binding.progressBar
        private val textViewPriority = binding.textViewPriority
        private val cardView = binding.taskCard
        private val buttonDelete = binding.buttonDelete
        private val dateTimeFormatCreatedAt =
            SimpleDateFormat(Constants.DATE_TIME_FORMAT_CREATED_AT, Locale.ENGLISH)
        private val dateTimeFormatDeadline =
            SimpleDateFormat(Constants.DATE_TIME_FORMAT_DEADLINE, Locale.ENGLISH)


        fun bind(task: Task) {
            textViewDomain.text = task.domain
            textViewTitle.text = task.title

            textViewTimeCreatedValue.text =
                getDateTimeFromMillis(task.createdTime, dateTimeFormatCreatedAt)
            textViewDeadline.text = getDateTimeFromMillis(task.deadline, dateTimeFormatDeadline)

            setStatus(task.status)
            textViewPriority.text = task.priority.value

            textViewDescription.text = task.description

            textViewProgress.text = itemView.resources.getString(R.string.task_done, task.progress)
            progressBar.progress = task.progress

            buttonDelete.setOnClickListener {
                deleteTaskAlert(task.id)
            }

            cardView.setOnClickListener {
                val directions =
                    TasksFragmentDirections.actionTasksFragmentToEditTaskFragment(task.id)
                itemView.findNavController().navigate(directions)
            }
        }

        private fun getDateTimeFromMillis(millis: Long, formatter: SimpleDateFormat): String {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = millis

            return formatter.format(calendar.time)
        }

        private fun setStatus(status: Status) {
            textViewStatus.text = status.value

            when (status) {
                Status.NEW -> setStatus(
                    R.color.task_blue_card,
                    R.color.task_blue,
                    R.color.task_blue
                )

                Status.IN_PROGRESS -> setStatus(
                    R.color.white,
                    R.color.task_blue,
                    R.color.task_dark_grey_lower_opacity
                )

                Status.DONE -> setStatus(R.color.white, R.color.task_green, R.color.task_green)
            }
        }

        private fun setStatus(
            backgroundColorId: Int, progressBarColorId: Int,
            statusBackgroundColorId: Int
        ) {
            cardView.setBackgroundColor(
                ResourcesCompat.getColor(itemView.resources, backgroundColorId, null)
            )
            progressBar.progressTintList = ColorStateList.valueOf(
                ResourcesCompat.getColor(itemView.resources, progressBarColorId, null)
            )
            textViewStatus.setBackgroundColor(
                ResourcesCompat.getColor(itemView.resources, statusBackgroundColorId, null)
            )
        }

        private fun deleteTaskAlert(id: Int) {
            AlertDialog.Builder(itemView.context)
                .setTitle("You will delete this task")
                .setMessage("Do you really want to delete this task?")
                .setIcon(R.drawable.ic_baseline_warning_24)
                .setPositiveButton("Yes") { _, _ ->
                    deleteTask(id)
                }
                .setNegativeButton("No") { _, _ ->
                }
                .show()
        }
    }

}