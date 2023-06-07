import queue.TransactionsQueue;
import queue.VerifiedTransactionsQueue;
import threads.Miner;
import threads.Supervisor;
import threads.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Random;

public class Main {

    public static void writeStartingFile(int users, String fileName) {
        try {
            Path filePath = Paths.get(fileName);

            Files.write(filePath, "".getBytes(), StandardOpenOption.TRUNCATE_EXISTING);

            for (int i = 0; i < users; i++) {
                Files.write(filePath,
                        "-1,100,%d,-1\n".formatted(i).getBytes(),
                        StandardOpenOption.APPEND);
            }

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private static void run(int noOfUsers, int noOfSupervisors, int noOfMiners,
                            TransactionsQueue transactions,
                            VerifiedTransactionsQueue verifiedTransactions) {

        Random random = new Random();
        User[] users = new User[noOfUsers];

        for (int i = 0; i < noOfUsers; i++) {

            int value = random.nextInt(199) + 1;
            int toWalletId = random.nextInt(70);

            users[i] = new User(i, toWalletId, value, transactions);
            users[i].start();
        }

        Supervisor[] supervisors = new Supervisor[noOfSupervisors];

        for (int i = 0; i < noOfSupervisors; i++) {

            supervisors[i] = new Supervisor(i, noOfUsers, transactions, verifiedTransactions);
            supervisors[i].start();
        }

        Miner[] miners = new Miner[noOfMiners];

        for (int i = 0; i < noOfMiners; i++) {

            miners[i] = new Miner(verifiedTransactions);
            miners[i].start();
        }

        for (int i = 0; i < noOfUsers; i++) {
            try {
                users[i].join();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }

        for (int i = 0; i < noOfSupervisors; i++) {
            try {
                supervisors[i].join();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }

        for (int i = 0; i < noOfMiners; i++) {
            try {
                miners[i].join();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {

        int users = 30;
        int supervisors = 4;
        int miners = 1;

        writeStartingFile(users, "blockchain.txt");

        TransactionsQueue transactions = new TransactionsQueue(20);
        VerifiedTransactionsQueue verifiedTransactions = new VerifiedTransactionsQueue(20, supervisors);

        run(users, supervisors, miners, transactions, verifiedTransactions);

    }
}
