package tasks.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tasks.model.ArrayTaskList;
import tasks.model.Task;
import tasks.validators.TaskValidator;
import tasks.view.Main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskIntegrationTest {
    private TasksService tasksService;
    private ObservableList<Task> taskList;

    @BeforeEach
    void setUp() {
        TaskValidator taskValidator = new TaskValidator();
        tasksService = new TasksService(new ArrayTaskList(), taskValidator);
        taskList = FXCollections.observableList(new ArrayList<>());
    }

    @Test
    void add_ValidTaskObject_ReturnsAddedTask() {
        int taskListSizeBefore = taskList.size();
        Task validTask = new Task("title", new Date(2022, Calendar.MARCH, 13, 17, 0));
        Task returnedTask = tasksService.addTask(validTask, taskList, Main.savedTasksFile);
        assertEquals(returnedTask, validTask);
        assertEquals(taskList.size(), taskListSizeBefore + 1);
    }

    @Test
    @DisplayName("TC_Int_Step3")
    void add_InvalidTaskObject_ThrowsIllegalArgumentException() {
        int taskListSizeBefore = taskList.size();
        Task invalidTask = new Task("", new Date(2022, Calendar.MARCH, 13, 17, 0));
        assertThrows(IllegalArgumentException.class, () -> tasksService.addTask(
                invalidTask,
                taskList,
                Main.savedTasksFile
        ));
        assertEquals(taskList.size(), taskListSizeBefore);
    }
}
