package tasks.validators;

import org.apache.log4j.Logger;
import tasks.model.Task;


public class TaskValidator {
    private static final Logger log = Logger.getLogger(Task.class.getName());

    public TaskValidator() {
    }

    public void validate(Task task) {
        if (task.isRepeated()) {
            validateRepeatableTask(task);
        } else {
            validateNonRepeatableTask(task);
        }
    }

    public void validateRepeatableTask(Task task) {
        if (task.getStartTime().after(task.getEndTime()))
            throw new IllegalArgumentException("Start date should be before end");
        if (task.getTitle() == null || task.getTitle().equals("")) {
            throw new IllegalArgumentException("Invalid title!");
        }
        if (task.getStartTime().getTime() < 0 || task.getEndTime().getTime() < 0) {
            log.error("time below bound");
            throw new IllegalArgumentException("Invalid date!");
        }
        if (task.getRepeatInterval() < 1) {
            log.error("interval < than 1");
            throw new IllegalArgumentException("Interval should be > 1!");
        }
    }

    public void validateNonRepeatableTask(Task task) {
        if (task.getTitle() == null || task.getTitle().equals("")) {
            throw new IllegalArgumentException("Invalid title!");
        }
        if (task.getStartTime().getTime() < 0) {
            log.error("time below bound");
            throw new IllegalArgumentException("Invalid date!");
        }
    }

}
