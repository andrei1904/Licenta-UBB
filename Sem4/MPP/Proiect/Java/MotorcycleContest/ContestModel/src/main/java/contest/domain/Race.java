package contest.domain;

import java.io.Serializable;

public class Race extends Entity<Integer> implements Serializable {
    private String name;
    private EngineCapacity requiredEngineCapacity;


    public Race() {
        this("");
    }

    public Race(String name) {
        this(name, EngineCapacity.NEW);
    }

//    public Race(String name, String requiredEngineCapacity) {
//        this.name = name;
//        this.requiredEngineCapacity = EngineCapacity.valueOf(requiredEngineCapacity);
//    }

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
