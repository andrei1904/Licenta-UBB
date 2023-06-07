package app.model;

import java.io.Serializable;

public class UserGameDto implements Serializable {
    String username;
    String letter;
    Integer points;

    public UserGameDto(String username, String letter, Integer points) {
        this.username = username;
        this.letter = letter;
        this.points = points;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
