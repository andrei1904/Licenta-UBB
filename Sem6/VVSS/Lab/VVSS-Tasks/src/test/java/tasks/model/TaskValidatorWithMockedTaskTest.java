package tasks.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tasks.validators.TaskValidator;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class TaskValidatorWithMockedTaskTest {

    private TaskValidator taskValidator;

    @BeforeEach
    void setUp() {
        taskValidator = new TaskValidator();
    }

    @Test
    @Tag("INT")
    void validate_ValidTask_DoesNothing() {
        Task validTask = mock(Task.class);

        Mockito.when(validTask.isRepeated()).thenReturn(false);
        Mockito.when(validTask.getTitle()).thenReturn("task with valid title");
        Mockito.when(validTask.getStartTime()).thenReturn(new Date(2022, Calendar.MAY, 15, 12, 0));

        assertDoesNotThrow(() -> taskValidator.validate(validTask));
    }

    @Test
    @Tag("INT")
    void validate_InvalidTask_ThrowsIllegalArgumentException() {
        Task invalidTask = mock(Task.class);

        Mockito.when(invalidTask.isRepeated()).thenReturn(false);
        Mockito.when(invalidTask.getTitle()).thenReturn("");
        Mockito.when(invalidTask.getStartTime()).thenReturn(new Date(2022, Calendar.MAY, 15, 12, 0));

        assertThrows(IllegalArgumentException.class, () -> taskValidator.validate(invalidTask));
    }
}
