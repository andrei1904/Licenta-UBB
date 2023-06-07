package thread;

import model.Parkings;
import model.Request;
import model.VerifiedRequest;
import queue.RequestQueue;
import queue.VerifiedRequestQueue;

public class Functionar extends Thread {

    private final int functionarId;
    private final RequestQueue requestQueue;
    private final VerifiedRequestQueue verifiedRequestQueue;
    private final Parkings parkings;

    public Functionar(int functionarId, RequestQueue requestQueue, VerifiedRequestQueue verifiedRequestQueue,
                      Parkings parkings) {
        this.functionarId = functionarId;
        this.requestQueue = requestQueue;
        this.verifiedRequestQueue = verifiedRequestQueue;
        this.parkings = parkings;
    }

    @Override
    public void run() {

        while (true) {

            Request request = requestQueue.dequeue();
            if (request == null) {
                break;
            }

            System.out.printf("Functionarul %d a preluat cererea cu codul %d pentru Parkingul %d\n%n",
                    functionarId, request.getRequestCode(), request.getParkId());

            int nOfAvailableSpaces = parkings.reserveParkingIfAvailable(request.getParkId());
            if (nOfAvailableSpaces != -1) {

                VerifiedRequest verifiedRequest = new VerifiedRequest(
                        request.getRequestCode(),
                        request.getParkId(),
                        functionarId
                );

                verifiedRequestQueue.enqueue(verifiedRequest);

                System.out.printf("Functionarul %d a aprobat cererea cu codul %d pentru Parkingul %d cu locuri disponibile %d din totalul %d\n%n",
                        functionarId, request.getRequestCode(), request.getParkId(),
                        nOfAvailableSpaces, parkings.getTotalSpacesById(request.getParkId()));
            } else {

                System.out.printf("Functionarul %d a respins cererea cu codul %d pentru Parkingul %d cu locuri disponibile %d din totalul %d\n%n",
                        functionarId, request.getRequestCode(), request.getParkId(), 0,
                        parkings.getTotalSpacesById(request.getParkId()));
            }

        }

        verifiedRequestQueue.setNoMoreElements();
    }
}
