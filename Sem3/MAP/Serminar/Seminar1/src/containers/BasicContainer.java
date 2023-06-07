package containers;

import domain.Task;

public abstract class BasicContainer implements Container {
    Task[] tasks;
    int size;

    public BasicContainer(Task[] tasks, int size) {
        this.tasks = tasks;
        this.size = size;
    }

    @Override
    public abstract Task remove();

    @Override
    public void add(Task task) {
        if (tasks.length == size) {
            Task[] t = new Task[tasks.length * 2];
            System.arraycopy(tasks, 0, t, 0, tasks.length);
            tasks = t;
        }
        tasks[size] = task;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
