package containers;

import domain.Task;

public class StackContainer extends BasicContainer {
    public StackContainer() {
        super(new Task[10], 0);
    }

    @Override
    public Task remove() {
        if (!isEmpty()) {
            size--;
            return tasks[size];
        }
        return null;
    }

}
