package containers;

import domain.Task;

public class QueueContainer extends BasicContainer {
    public QueueContainer() {
        super(new Task[10], 0);
    }

    @Override
    public Task remove() {
        if (!isEmpty()) {
            Task first = tasks[0];
            if (size - 1 >= 0) {
                System.arraycopy(tasks, 1, tasks, 0, size - 1);
            }
            size--;
            return first;
        }
        return null;
    }

}
