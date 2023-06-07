public class Citizen extends Thread {

    private final int requestCode;
    private final int requestedValue;
    private final RequestQueue requestQueue;

    public Citizen(int requestCode, int requestedValue, RequestQueue requestQueue) {
        this.requestCode = requestCode;
        this.requestedValue = requestedValue;
        this.requestQueue = requestQueue;
    }

    @Override
    public void run() {

        Request request = new Request(requestCode, requestedValue);
        requestQueue.enqueue(request);
    }
}
