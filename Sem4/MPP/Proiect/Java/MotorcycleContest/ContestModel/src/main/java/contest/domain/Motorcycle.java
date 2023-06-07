package contest.domain;

import java.io.Serializable;

public class Motorcycle extends Entity<Integer> implements Serializable {
    private EngineCapacity engineCapacity;

    public Motorcycle(EngineCapacity engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public EngineCapacity getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(EngineCapacity engineCapacity) {
        this.engineCapacity = engineCapacity;
    }
}
