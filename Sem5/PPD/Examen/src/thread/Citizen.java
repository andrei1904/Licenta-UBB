package thread;

import model.Request;
import queue.RequestQueue;

import java.util.Random;

public class Citizen extends Thread {

    private final int citiezId;
    private final Random random = new Random();
    private final RequestQueue requestQueue;

    public Citizen(int citiezId, RequestQueue requestQueue) {
        this.citiezId = citiezId;
        this.requestQueue = requestQueue;
    }

    @Override
    public void run() {

        int nOfRequests = random.nextInt(3) + 1;

        for (int i = 0; i < nOfRequests; i++) {

            int parkingId = random.nextInt(3) + 1;
            int requestCode = random.nextInt(1000);

            Request request = new Request(requestCode, parkingId);
            requestQueue.enqueue(request);

            System.out.printf("Cetateanul %d a initiat cererea cu codul %d pentru Parkingul %d\n%n",
                    citiezId, requestCode, parkingId);

        }

        requestQueue.setNoMoreElements();
    }
}
