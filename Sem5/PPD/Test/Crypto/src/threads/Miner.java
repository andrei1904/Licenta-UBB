package threads;

import model.VerifiedTransaction;
import queue.VerifiedTransactionsQueue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Miner extends Thread {

    private final VerifiedTransactionsQueue verifiedTransactions;

    public Miner(VerifiedTransactionsQueue verifiedTransactions) {
        this.verifiedTransactions = verifiedTransactions;
    }

    private synchronized void writeInFile(VerifiedTransaction verifiedTransaction) {
        try {

            Path filePath = Paths.get("blockchain.txt");

            Files.write(filePath,
                    "%d,%d,%d,%d\n".formatted(
                            verifiedTransaction.getFromWalletId(),
                            verifiedTransaction.getValue(),
                            verifiedTransaction.getToWalletId(),
                            verifiedTransaction.getSupervisorId())
                            .getBytes(),
                    StandardOpenOption.APPEND);


        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void run() {

        while (true) {

            VerifiedTransaction verifiedTransaction = verifiedTransactions.dequeue();
            if (verifiedTransaction == null) {
                break;
            }

            writeInFile(verifiedTransaction);

            System.out.printf("Minerul %d a salvat trazactia initiata de wallet %d cu valoarea %d catre wallet %d, validata de supervizorul %d\n%n",
                    0, verifiedTransaction.getFromWalletId(), verifiedTransaction.getValue(),
                    verifiedTransaction.getToWalletId(), verifiedTransaction.getSupervisorId());
        }
    }
}
