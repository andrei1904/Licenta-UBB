package contest.domain;

import java.io.Serializable;

public class ParticipantDTO implements Serializable {
    private String name;
    private EngineCapacity engineCapacity;

    public ParticipantDTO(String name, EngineCapacity engineCapacity) {
        this.name = name;
        this.engineCapacity = engineCapacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EngineCapacity getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(EngineCapacity engineCapacity) {
        this.engineCapacity = engineCapacity;
    }
}
