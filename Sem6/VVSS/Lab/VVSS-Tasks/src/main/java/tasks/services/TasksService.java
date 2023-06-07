package tasks.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tasks.model.ArrayTaskList;
import tasks.model.Task;
import tasks.model.TasksOperations;
import tasks.validators.TaskValidator;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class TasksService {

    private ArrayTaskList tasks;
    private TaskValidator taskValidator;

    public TasksService(ArrayTaskList tasks, TaskValidator taskValidator){
        this.tasks = tasks;
        this.taskValidator = taskValidator;
    }


    public ObservableList<Task> getObservableList() {
        return FXCollections.observableArrayList(tasks.getAll());
    }

    public String getIntervalInHours(Task task) {
        int seconds = task.getRepeatInterval();
        int minutes = seconds / DateService.SECONDS_IN_MINUTE;
        int hours = minutes / DateService.MINUTES_IN_HOUR;
        minutes = minutes % DateService.MINUTES_IN_HOUR;
        return formTimeUnit(hours) + ":" + formTimeUnit(minutes);//hh:MM
    }

    public String formTimeUnit(int timeUnit) {
        StringBuilder sb = new StringBuilder();
        if (timeUnit < 10) sb.append("0");
        if (timeUnit == 0) sb.append("0");
        else {
            sb.append(timeUnit);
        }
        return sb.toString();
    }

    public int parseFromStringToSeconds(String stringTime) {//hh:MM
        String[] units = stringTime.split(":");
        int hours = Integer.parseInt(units[0]);
        int minutes = Integer.parseInt(units[1]);
        return (hours * DateService.MINUTES_IN_HOUR + minutes) * DateService.SECONDS_IN_MINUTE;
    }

    public Iterable<Task> filterTasks(ObservableList<Task> tasks, Date start, Date end) {
        ArrayList<Task> incomingTasks = new ArrayList<>();
        if (!end.before(start)) {
            for (Task currentTask : tasks) {
                Date nextTime = currentTask.nextTimeAfter(start);
                if (nextTime != null) {
                    if ((nextTime.before(end) || nextTime.equals(end)))
                        incomingTasks.add(currentTask);
                }
            }
        }

        return incomingTasks;
    }

    public Task addTask(Task task, ObservableList<Task> tasks, File tasksFile) {
        taskValidator.validate(task);
        tasks.add(task);
        TaskIO.rewriteFile(tasks, tasksFile);
        return task;
    }
}
