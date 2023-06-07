package factory;

import containers.Container;
import enums.Strategy;

public interface Factory {
    Container createContainer(Strategy startegy);
}
