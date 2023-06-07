public class Worker extends Thread {

    private final int workedId;
    private final Data data;
    private final RequestQueue requestQueue;

    public Worker(int workedId, Data data, RequestQueue requestQueue) {
        this.workedId = workedId;
        this.data = data;
        this.requestQueue = requestQueue;
    }

    @Override
    public void run() {

        while (true) {

            Request request = requestQueue.dequeue();
            if (request == null) {
                break;
            }
            System.out.printf("Cererea cu nr %d a fost preluata de catre angajatul %d\n%n",
                    request.getCode(), workedId);

            synchronized (data) {
                if (data.getCurrentValue() >= request.getValue()) {

                    data.setAlocatedValue(data.getAlocatedValue() + request.getValue());
                    System.out.printf("Cererea cu nr %d a fost acceptata de catre angajatul %d deoarece valoarea ceruta %d poate fi onorata fondurilor disponibile %d\n%n",
                            request.getCode(), workedId, request.getValue(), data.getCurrentValue());
                } else {

                    System.out.printf("Cererea cu nr %d nu a fost acceptata de catre angajatul %d deoarece valoarea ceruta %d depaseste fondurile disponibile %d\n%n",
                            request.getCode(), workedId, request.getValue(), data.getCurrentValue());
                }
            }
        }
    }
}
