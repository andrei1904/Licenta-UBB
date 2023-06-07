package tasks.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
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

class TaskValidatorIntegrationTest {
    private TasksService tasksService;
    private ObservableList<Task> taskList;

    @BeforeEach
    void setUp() {
        TaskValidator taskValidator = new TaskValidator();
        tasksService = new TasksService(new ArrayTaskList(), taskValidator);
        taskList = FXCollections.observableList(new ArrayList<>());
    }

    @Test
    void add_ValidTaskMock_ReturnsAddedTask() {
        int taskListSizeBefore = taskList.size();

        Task validTask = mock(Task.class);
        Mockito.when(validTask.isRepeated()).thenReturn(false);
        Mockito.when(validTask.getTitle()).thenReturn("title");
        Mockito.when(validTask.getStartTime()).thenReturn(new Date(2022, Calendar.MAY, 2, 17, 0));

        Task returnedTask = tasksService.addTask(validTask, taskList, Main.savedTasksFile);
        assertEquals(returnedTask, validTask);
        assertEquals(taskList.size(), taskListSizeBefore + 1);
    }

    @Test
    void add_InvalidTaskMock_ThrowsIllegalArgumentException() {
        int taskListSizeBefore = taskList.size();

        Task invalidTask = mock(Task.class);
        Mockito.when(invalidTask.isRepeated()).thenReturn(false);
        Mockito.when(invalidTask.getTitle()).thenReturn("");
        Mockito.when(invalidTask.getStartTime()).thenReturn(new Date(2022, Calendar.MAY, 2, 17, 0));

        assertThrows(IllegalArgumentException.class, () -> tasksService.addTask(
                invalidTask,
                taskList,
                Main.savedTasksFile
        ));
        assertEquals(taskList.size(), taskListSizeBefore);
    }
}
