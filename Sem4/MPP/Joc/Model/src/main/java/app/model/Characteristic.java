package app.model;

public class Characteristic {
    private String word;
    private String characteristic;

    public Characteristic() {

    }

    public Characteristic(String word, String characteristic) {
        this.word = word;
        this.characteristic = characteristic;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }
}
