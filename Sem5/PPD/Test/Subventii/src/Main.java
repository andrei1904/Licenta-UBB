import java.util.Random;

public class Main {

    public static void main(String[] args) {

        int citizens = 5;
        int workers = 4;
        int supervisors = 1;

        Data data = new Data(0, 0);

        RequestQueue requestQueue = new RequestQueue(20);

        run(citizens, workers, supervisors, data, requestQueue);
    }

    private static void run(int nOfCitizens, int nOfWorkers, int nOfSupervisors,
                            Data data, RequestQueue requestQueue) {

        Random random = new Random();
        Citizen[] citizens = new Citizen[nOfCitizens];

        for (int i = 0; i < nOfCitizens; i++) {

            int requestCode = random.nextInt(nOfCitizens + 50);
            int requestedValue = random.nextInt(29) + 1;

            citizens[i] = new Citizen(requestCode, requestedValue, requestQueue);
            citizens[i].start();
        }

        Worker[] workers = new Worker[nOfWorkers];

        for (int i = 0; i < nOfWorkers; i++) {

            workers[i] = new Worker(i, data, requestQueue);
            workers[i].start();
        }

        Supervisor[] supervisors = new Supervisor[nOfSupervisors];

        for (int i = 0; i < nOfSupervisors; i++) {

            supervisors[i] = new Supervisor(data);
            supervisors[i].start();
        }

        try {
            for (int i = 0; i < nOfCitizens; i++) {
                citizens[i].join();
            }
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        try {
            for (int i = 0; i < nOfWorkers; i++) {
                workers[i].join();
            }
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        try {
            for (int i = 0; i < nOfSupervisors; i++) {
                supervisors[i].join();
            }
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}
