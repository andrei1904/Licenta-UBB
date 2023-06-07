package model;

public class VerifiedTransaction extends Transaction {

    private int supervisorId;

    public VerifiedTransaction(int fromWalletId, int toWalletId, int value, int supervisorId) {
        super(fromWalletId, toWalletId, value);
        this.supervisorId = supervisorId;
    }

    public VerifiedTransaction(Transaction transaction, int supervisorId) {
        super(transaction.getFromWalletId(), transaction.getToWalletId(), transaction.getValue());
        this.supervisorId = supervisorId;
    }

    public int getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(int supervisorId) {
        this.supervisorId = supervisorId;
    }
}
