package app.model;

import java.io.Serializable;

public class UserPointsDto implements Serializable {
    String username;
    Integer points;

    public UserPointsDto(String username, Integer points) {
        this.username = username;
        this.points = points;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
