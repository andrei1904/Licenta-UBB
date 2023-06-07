package tasks.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tasks.model.ArrayTaskList;
import tasks.model.Task;
import tasks.validators.TaskValidator;
import tasks.view.Main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class TasksServiceWithMockedTaskValidatorTest {

    private TaskValidator taskValidator;
    private TasksService tasksService;
    private ObservableList<Task> taskList;

    @BeforeEach
    void setUp() {
        taskValidator = mock(TaskValidator.class);
        tasksService = new TasksService(new ArrayTaskList(), taskValidator);
        taskList = FXCollections.observableList(new ArrayList<>());
    }

    @Test
    @Tag("INT")
    void add_ValidTask_ReturnsAddedTask() {
        int taskListSizeBefore = taskList.size();
        Task validTask = new Task("title", new Date(2022, Calendar.MARCH, 13, 17, 0));
        Mockito.doNothing().when(taskValidator).validate(validTask);
        Task returnedTask = tasksService.addTask(validTask, taskList, Main.savedTasksFile);
        assertEquals(returnedTask, validTask);
        assertEquals(taskList.size(), taskListSizeBefore + 1);
    }

    @Test
    @Tag("INT")
    void add_InvalidTask_ThrowsIllegalArgumentException() {
        int taskListSizeBefore = taskList.size();
        Task invalidTask = new Task("", new Date(2022, Calendar.MARCH, 13, 17, 0));
        Mockito.doThrow(new IllegalArgumentException()).when(taskValidator).validate(invalidTask);
        assertThrows(IllegalArgumentException.class, () -> tasksService.addTask(
                invalidTask,
                taskList,
                Main.savedTasksFile
        ));
        assertEquals(taskList.size(), taskListSizeBefore);
    }
}
