package domain;

public class Motorcycle extends Entity<Integer> {
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
