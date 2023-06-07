package contest.domain;

import java.io.Serializable;

public class Team extends Entity<Integer> implements Serializable {
    private String name;

    public Team(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
