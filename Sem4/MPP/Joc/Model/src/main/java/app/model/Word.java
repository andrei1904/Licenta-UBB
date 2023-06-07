package app.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class Word implements Serializable {
    private String word;
    private Set<String> characts;

    public Word(String word, Set<String> characts) {
        this.word = word;
        this.characts = characts;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Set<String> getCharacts() {
        return characts;
    }

    public void setCharacts(Set<String> characts) {
        this.characts = characts;
    }
}
