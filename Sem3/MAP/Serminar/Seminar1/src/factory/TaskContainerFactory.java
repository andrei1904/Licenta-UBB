package factory;

import containers.Container;
import containers.QueueContainer;
import containers.StackContainer;
import enums.Strategy;

public class TaskContainerFactory implements Factory {

    private static TaskContainerFactory instance = null;

    private TaskContainerFactory() {
    }

    public static TaskContainerFactory getInstance() {
        if (instance == null) {
            instance = new TaskContainerFactory();
        }
        return instance;
    }

    @Override
    public Container createContainer(Strategy strategy) {
        if (Strategy.LIFO == strategy) {
            return new StackContainer();
        } else {
            return new QueueContainer();
        }

    }
}
