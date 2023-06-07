package thread;

import model.VerifiedRequest;
import queue.VerifiedRequestQueue;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Centralizator extends Thread {

    private final VerifiedRequestQueue verifiedRequestQueue;

    public Centralizator(VerifiedRequestQueue verifiedRequestQueue) {
        this.verifiedRequestQueue = verifiedRequestQueue;
    }

    @Override
    public void run() {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("condica.txt"));

            while (true) {

                VerifiedRequest verifiedRequest = verifiedRequestQueue.dequeue();
                if (verifiedRequest == null) {
                    break;
                }

                // write in file
                writer.append("%d,%d,%d\n".formatted(
                        verifiedRequest.getRequestCode(),
                        verifiedRequest.getParkId(),
                        verifiedRequest.getFunctionarId()));

                System.out.printf("Centralizatorul a salvat în condica cererea cu codul %d pentru Parkingul %d\n%n",
                        verifiedRequest.getRequestCode(), verifiedRequest.getParkId());
            }

            writer.close();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
