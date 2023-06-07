package domain;

import java.util.Objects;

public abstract class Task {
    private String taskId;
    private String descriere;

    public Task(String taskId, String desc) {
        this.taskId = taskId;
        this.descriere = desc;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return getTaskId().equals(task.getTaskId()) &&
                getDescriere().equals(task.getDescriere());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTaskId(), getDescriere());
    }

    @Override
    public String toString() {
        return taskId + " " + descriere;
    }

    public abstract void execute();
}
