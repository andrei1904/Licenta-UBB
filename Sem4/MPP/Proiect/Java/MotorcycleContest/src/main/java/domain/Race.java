package domain;

import java.util.List;

public class Race extends Entity<Integer> {
    String name;
    private EngineCapacity requiredEngineCapacity;

    public Race(String name, EngineCapacity requiredEngineCapacity) {
        this.name = name;
        this.requiredEngineCapacity = requiredEngineCapacity;
    }

    public EngineCapacity getRequiredEngineCapacity() {
        return requiredEngineCapacity;
    }

    public void setRequiredEngineCapacity(EngineCapacity requiredEngineCapacity) {
        this.requiredEngineCapacity = requiredEngineCapacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
