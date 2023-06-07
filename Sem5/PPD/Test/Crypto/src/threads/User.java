package threads;

import model.Transaction;
import queue.TransactionsQueue;

public class User extends Thread {

    private final int userId;
    private final int toWalletId;
    private final int value;
    private final TransactionsQueue transactions;

    public User(int userId, int toWalletId, int value, TransactionsQueue transactions) {
        this.userId = userId;
        this.toWalletId = toWalletId;
        this.value = value;
        this.transactions = transactions;
    }

    @Override
    public void run() {

        Transaction transaction = new Transaction(userId, toWalletId, value);
        transactions.enqueue(transaction);
        System.out.println("Add transaction" + userId + "\n");
    }
}
