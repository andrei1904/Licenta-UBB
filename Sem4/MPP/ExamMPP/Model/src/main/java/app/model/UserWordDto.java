package app.model;

import java.io.Serializable;

public class UserWordDto implements Serializable {
    private String username;
    private String word;

    public UserWordDto() {

    }

    public UserWordDto(String username, String word) {
        this.username = username;
        this.word = word;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
