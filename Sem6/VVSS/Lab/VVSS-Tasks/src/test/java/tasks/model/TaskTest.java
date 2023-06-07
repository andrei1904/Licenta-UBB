package tasks.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    private Task task;

    @BeforeEach
    void setUp() {
        try {
            task=new Task("new task",Task.getDateFormat().parse("2021-02-12 10:10"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("TC_Int_Step1")
    void testTaskCreation() throws ParseException {
        assert task.getTitle() == "new task";
        System.out.println(task.getFormattedDateStart());
        System.out.println(task.getDateFormat().format(Task.getDateFormat().parse("2021-02-12 10:10")));
        assert task.getFormattedDateStart().equals(task.getDateFormat().format(Task.getDateFormat().parse("2021-02-12 10:10")));

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testNonRepeatableTaskCreation() {
        Task task = new Task(
                "NonRepeatable",
                new Date(2022, Calendar.APRIL, 13, 17, 0)
        );

        assertEquals("NonRepeatable", task.getTitle());
        assertEquals(new Date(2022, Calendar.APRIL, 13, 17, 0), task.getStartTime());
        assertEquals(new Date(2022, Calendar.APRIL, 13, 17, 0), task.getEndTime());
        assertFalse(task.isRepeated());
    }

    @Test
    void testRepeatableTaskCreation() {
        Task task = new Task(
                "Repeatable",
                new Date(2022, Calendar.APRIL, 13, 17, 0),
                new Date(2022, Calendar.APRIL, 14, 17, 0),
                100
        );

        assertEquals("Repeatable", task.getTitle());
        assertEquals(new Date(2022, Calendar.APRIL, 13, 17, 0), task.getStartTime());
        assertEquals(new Date(2022, Calendar.APRIL, 14, 17, 0), task.getEndTime());
        assertEquals(100, task.getRepeatInterval());
        assertTrue(task.isRepeated());
    }
}