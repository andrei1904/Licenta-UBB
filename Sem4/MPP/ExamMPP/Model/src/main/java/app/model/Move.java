package app.model;

import java.io.Serializable;

public class Move implements Serializable {
    private Integer id;
    private Integer gameId;
    private Integer round;
    private User user;
    private String chosenWord;
    private String letter;
    private String chosenUser;
    private Integer points;

    public Move() {

    }

    public Move(Integer gameId, Integer round, User user, String chosenWord, String letter, String chosenUser, Integer points) {
        this.gameId = gameId;
        this.round = round;
        this.user = user;
        this.chosenWord = chosenWord;
        this.letter = letter;
        this.chosenUser = chosenUser;
        this.points = points;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getChosenUser() {
        return chosenUser;
    }

    public void setChosenUser(String chosenUser) {
        this.chosenUser = chosenUser;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getChosenWord() {
        return chosenWord;
    }

    public void setChosenWord(String chosenWord) {
        this.chosenWord = chosenWord;
    }
}
