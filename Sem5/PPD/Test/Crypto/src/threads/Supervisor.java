package threads;

import model.Transaction;
import model.VerifiedTransaction;
import queue.TransactionsQueue;
import queue.VerifiedTransactionsQueue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Supervisor extends Thread {

    private final int supervisorId;
    private final int numberOfUsers;
    private final TransactionsQueue transactions;
    private final VerifiedTransactionsQueue verifiedTransactionsQueue;

    public Supervisor(int supervisorId, int numberOfUsers, TransactionsQueue transactions,
                      VerifiedTransactionsQueue verifiedTransactionsQueue) {
        this.supervisorId = supervisorId;
        this.numberOfUsers = numberOfUsers;
        this.transactions = transactions;
        this.verifiedTransactionsQueue = verifiedTransactionsQueue;
    }

    private synchronized List<Transaction> getTransactions() {
        List<Transaction> transactions = new ArrayList<>();

        try {
            File file = new File("blockchain.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] results = line.split(",");

                Transaction transaction = new Transaction(
                        Integer.parseInt(results[0]),
                        Integer.parseInt(results[2]),
                        Integer.parseInt(results[1])
                );

                transactions.add(transaction);
            }

        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }

        return transactions;
    }

    private synchronized int getAmmountForWallet(int walletId) {
        List<Transaction> transactions = getTransactions();

        int ammountRecived = transactions.stream()
                .filter(transaction -> transaction.getToWalletId() == walletId)
                .mapToInt(Transaction::getValue)
                .sum();

        int ammountSpend = transactions.stream()
                .filter(transaction -> transaction.getFromWalletId() == walletId)
                .mapToInt(Transaction::getValue)
                .sum();

        return ammountRecived - ammountSpend;
    }

    @Override
    public void run() {

        while (true) {

            Transaction transaction = transactions.dequeue();
            if (transaction == null) {
                break;
            }
            System.out.printf("Tranzactia initiata de wallet %d, a fost preluata de supervizorul %d\n%n",
                    transaction.getFromWalletId(), supervisorId);

            // check dest id
            if (transaction.getToWalletId() < 0 || transaction.getToWalletId() > numberOfUsers) {
                System.out.printf("Pentru tranzactia iniatata de wallet %d, preluata de supervizorul %d, NU exista walletul destinatie %d\n%n",
                        transaction.getFromWalletId(), supervisorId, transaction.getToWalletId());
                continue;
            }

            int total = getAmmountForWallet(transaction.getFromWalletId());
            // check ammount
            if (total > transaction.getValue()) {

                // add to verified transactions
                verifiedTransactionsQueue.enqueue(
                        new VerifiedTransaction(transaction, supervisorId)
                );
                System.out.printf("Tranzactia initiata de wallet %d cu valoarea %d catre wallet %d a fost accepata de supervizorul %d, sold total inainte de opeartie: %d\n%n",
                        transaction.getFromWalletId(), transaction.getValue(),
                        transaction.getToWalletId(), supervisorId, total);
            } else {

                System.out.printf("Tranzactia initiata de wallet %d cu valoarea %d catre wallet %d a fost respinsa de supervizorul %d, sold total inainte de opeartie: %d\n%n",
                        transaction.getFromWalletId(), transaction.getValue(),
                        transaction.getToWalletId(), supervisorId, total);
            }

        }

        verifiedTransactionsQueue.setNoMoreElements();
    }
}
