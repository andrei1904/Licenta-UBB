package tasks.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

public class TasksServiceWithMockedTaskTest {
    private TaskValidator taskValidator;
    private TasksService tasksService;
    private ObservableList<Task> taskList;

    @BeforeEach
    void setUp() {
        taskValidator = new TaskValidator();
        tasksService = new TasksService(new ArrayTaskList(), taskValidator);
        taskList = FXCollections.observableList(new ArrayList<>());
    }

    @Test
    @Tag("INT")
    @DisplayName("TC_Int_Step2")
    void add_ValidTask_ReturnsAddedTask() {
        int taskListSizeBefore = taskList.size();
        Task validTask = mock(Task.class);

        Mockito.when(validTask.isRepeated()).thenReturn(false);
        Mockito.when(validTask.getTitle()).thenReturn("valid title");
        Mockito.when(validTask.getStartTime()).thenReturn(new Date(2022, Calendar.MARCH, 13, 17, 0));

        Task returnedTask = tasksService.addTask(validTask, taskList, Main.savedTasksFile);

        assertEquals(returnedTask, validTask);
        assertEquals(taskList.size(), taskListSizeBefore + 1);
    }

    @Test
    @Tag("INT")
    void add_InvalidTask_ThrowsIllegalArgumentException() {
        int taskListSizeBefore = taskList.size();
        Task invalidTask = mock(Task.class);

        Mockito.when(invalidTask.isRepeated()).thenReturn(true);
        Mockito.when(invalidTask.getTitle()).thenReturn("");
        Mockito.when(invalidTask.getStartTime()).thenReturn(new Date(2022, Calendar.MARCH, 13, 17, 0));
        Mockito.when(invalidTask.getEndTime()).thenReturn(new Date(2022, Calendar.MARCH, 11, 17, 0));
        Mockito.when(invalidTask.getRepeatInterval()).thenReturn(120);

        assertThrows(IllegalArgumentException.class, () -> tasksService.addTask(
                invalidTask,
                taskList,
                Main.savedTasksFile
        ));

        assertEquals(taskList.size(), taskListSizeBefore);
    }
}
