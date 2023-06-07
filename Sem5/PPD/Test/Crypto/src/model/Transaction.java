package model;

public class Transaction {

    private int fromWalletId;
    private int toWalletId;
    private int value;

    public Transaction(int fromWalletId, int toWalletId, int value) {
        this.fromWalletId = fromWalletId;
        this.toWalletId = toWalletId;
        this.value = value;
    }

    public int getFromWalletId() {
        return fromWalletId;
    }

    public void setFromWalletId(int fromWalletId) {
        this.fromWalletId = fromWalletId;
    }

    public int getToWalletId() {
        return toWalletId;
    }

    public void setToWalletId(int toWalletId) {
        this.toWalletId = toWalletId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
