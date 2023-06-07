package runners;

import containers.Container;
import domain.Task;
import enums.Strategy;
import factory.TaskContainerFactory;

public class StrategyTaskRunner implements TaskRunner {

    private Container container;

    public StrategyTaskRunner(Strategy strategy) {
        container = TaskContainerFactory.getInstance().createContainer(strategy);
    }

    @Override
    public void executeOneTask() {
        if (!container.isEmpty()) {
            Task toDo = container.remove();
            toDo.execute();
        }
    }

    @Override
    public void executeAll() {
        while (!container.isEmpty()) {
            Task toDo = container.remove();
            toDo.execute();
        }
    }

    @Override
    public void addTask(Task t) {
        container.add(t);
    }

    @Override
    public boolean hasTask() {
        return !container.isEmpty();
    }
}
