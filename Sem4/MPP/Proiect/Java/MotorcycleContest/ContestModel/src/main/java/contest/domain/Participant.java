package contest.domain;

import java.io.Serializable;

public class Participant extends Entity<Integer> implements Serializable {
    private String name;
    private Motorcycle motorcycle;
    private Team team;

    public Participant(String name, Motorcycle motorcycle, Team team) {
        this.name = name;
        this.motorcycle = motorcycle;
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Motorcycle getMotorcycle() {
        return motorcycle;
    }

    public void setMotorcycle(Motorcycle motorcycle) {
        this.motorcycle = motorcycle;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
