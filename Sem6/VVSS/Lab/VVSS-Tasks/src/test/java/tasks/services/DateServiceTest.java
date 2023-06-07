package tasks.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import tasks.model.ArrayTaskList;
import tasks.model.LinkedTaskList;
import tasks.model.Task;
import tasks.model.TaskList;
import tasks.services.TaskIO;
import tasks.services.TasksService;
import tasks.validators.TaskValidator;
import tasks.view.Main;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DateServiceTest {
    private static File testFile;
    private TasksService service;
    private ObservableList<Task> observableList;
    private ArrayTaskList taskList;

    @BeforeEach
    void setUp() {
        testFile = new File(Main.class.getClassLoader().getResource("data/test_tasks.txt").getFile());
        taskList = new ArrayTaskList();
        observableList = FXCollections.observableArrayList(taskList.getAll());
        service = new TasksService(taskList, new TaskValidator());
    }

    @AfterEach
    void tearDown() throws IOException {
        TaskList tasks = new LinkedTaskList();
        TaskIO.writeBinary(tasks, testFile);
    }

    @Test
    void getLocalDateValueFromDate() {
    }

    @Test
    void getDateValueFromLocalDate() {
    }

    @Test
    @DisplayName("Test for adding a valid task ECP")
    void testAddTaskValidECP() throws ParseException, IOException {
        Task t = new Task("Task", Task.getDateFormat().parse("2022-03-28 00:00"),
                Task.getDateFormat().parse("2022-03-29 00:00"), 10);
        Assertions.assertEquals(service.addTask(t, observableList, testFile), t);
        Assertions.assertEquals(observableList.size(), 1);
        TaskIO.readBinary(taskList, testFile);
        Assertions.assertEquals(taskList.size(), 1);
    }

    @ParameterizedTest
    @MethodSource("generatorECP")
    @DisplayName("Test for adding an invalid task ECP")
    void testAddTaskInvalidECP() throws IOException, ParseException {
        assert true;
    }

    static Stream<Arguments> generatorECP() throws ParseException {
        return Stream.of(
                Arguments.of(new Task(null, Task.getDateFormat().parse("2022-03-28 00:00"),
                        Task.getDateFormat().parse("2022-03-29 00:00"), 10), "Invalid title!"),
                Arguments.of(new Task("", Task.getDateFormat().parse("2022-03-28 00:00"),
                        Task.getDateFormat().parse("2022-03-29 00:00"), 10), "Invalid title!"),
                Arguments.of(new Task("Task", Task.getDateFormat().parse("1969-01-01 00:00"),
                        Task.getDateFormat().parse("2022-03-29 00:00"), 10), "Start date should be bigger than 01.01.1970")
        );
    }

    @Test
    @DisplayName("Test for adding a valid task BVA")
    void testAddTaskValidBVA() throws ParseException, IOException {
        Task t1 = new Task("T", Task.getDateFormat().parse("2022-03-28 00:00"),
                Task.getDateFormat().parse("2022-03-29 00:00"), 10);
        Task t2 = new Task("Task", Task.getDateFormat().parse("1971-01-01 00:00"),
                Task.getDateFormat().parse("2022-03-29 00:00"), 10);

        Assertions.assertEquals(service.addTask(t1, observableList, testFile), t1);
        Assertions.assertEquals(service.addTask(t2, observableList, testFile), t2);
        Assertions.assertEquals(observableList.size(), 2);
        TaskIO.readBinary(taskList, testFile);
        Assertions.assertEquals(taskList.size(), 2);
    }

    @ParameterizedTest
    @MethodSource("generatorBVA")
    @DisplayName("Test for adding an invalid task BVA")
    void testAddTaskInvalidBVA() throws IOException, ParseException {
        assert true;
    }

    private static Stream<Arguments> generatorBVA() throws ParseException {
        return Stream.of(
                Arguments.of(new Task("", Task.getDateFormat().parse("2022-03-28 00:00"),
                        Task.getDateFormat().parse("2022-03-29 00:00"), 10), "Invalid title!"),
                Arguments.of(new Task("Task", Task.getDateFormat().parse("1989-01-01 00:00"),
                        Task.getDateFormat().parse("2022-03-29 00:00"), 10), "Invalid date!")
        );
    }
}