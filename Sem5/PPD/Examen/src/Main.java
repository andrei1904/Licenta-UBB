import model.Parkings;
import queue.RequestQueue;
import queue.VerifiedRequestQueue;
import thread.Centralizator;
import thread.Citizen;
import thread.Functionar;

public class Main {

    public static void main(String[] args) {

        int citizens = 50;
        int functionari = 4;
        int centralizators = 1;

        RequestQueue requestQueue = new RequestQueue(20, citizens);
        VerifiedRequestQueue verifiedRequestQueue = new VerifiedRequestQueue(20, functionari);

        Parkings parkings = new Parkings(5, 7, 10);
        
        run(citizens, functionari, centralizators, requestQueue, verifiedRequestQueue, parkings);
    }

    private static void run(int nOfCitizens, int nOfFunctionari, int nOfCentralizators,
                            RequestQueue requestQueue, VerifiedRequestQueue verifiedRequestQueue, Parkings parkings) {

        Citizen[] citizens = new Citizen[nOfCitizens];

        for (int i = 0; i < nOfCitizens; i++) {

            citizens[i] = new Citizen(i, requestQueue);
            citizens[i].start();
        }

        Functionar[] functionari = new Functionar[nOfFunctionari];

        for (int i = 0; i < nOfFunctionari; i++) {

            functionari[i] = new Functionar(i, requestQueue, verifiedRequestQueue, parkings);
            functionari[i].start();
        }

        Centralizator[] centralizators = new Centralizator[nOfCentralizators];

        for (int i = 0; i < nOfCentralizators; i++) {

            centralizators[i] = new Centralizator(verifiedRequestQueue);
            centralizators[i].start();
        }


        try {
            for (int i = 0; i < nOfCitizens; i++) {
                citizens[i].join();
            }
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        try {
            for (int i = 0; i < nOfFunctionari; i++) {
                functionari[i].join();
            }
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        try {
            for (int i = 0; i < nOfCentralizators; i++) {
                centralizators[i].join();
            }
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}
