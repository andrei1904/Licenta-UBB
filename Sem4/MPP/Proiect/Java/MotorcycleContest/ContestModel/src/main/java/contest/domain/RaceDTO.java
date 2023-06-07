package contest.domain;

import java.io.Serializable;

public class RaceDTO implements Serializable {
    private String raceName;
    private EngineCapacity requiredEngineCapacity;
    private int numberOfParticipants;

    public RaceDTO(String raceName, EngineCapacity requiredEngineCapacity, int numberOfParticipants) {
        this.raceName = raceName;
        this.requiredEngineCapacity = requiredEngineCapacity;
        this.numberOfParticipants = numberOfParticipants;
    }

    public String getRaceName() {
        return raceName;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    public EngineCapacity getRequiredEngineCapacity() {
        return requiredEngineCapacity;
    }

    public void setRequiredEngineCapacity(EngineCapacity requiredEngineCapacity) {
        this.requiredEngineCapacity = requiredEngineCapacity;
    }

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(int numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }
}
