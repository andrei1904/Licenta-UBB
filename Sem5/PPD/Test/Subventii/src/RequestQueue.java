public class RequestQueue {

    private final Request[] value;
    private int front;
    private int rear;
    private int size;
    private final int capacity;
    private boolean moreElements;

    public RequestQueue(int capacity) {
        this.value = new Request[capacity];
        this.capacity = capacity;
        this.size = 0;
        this.front = 0;
        this.rear = -1;
        this.moreElements = true;
    }

    public synchronized void enqueue(Request request) {

        try {
            while (isFull()) {
                wait();
            }

            rear = (rear + 1) % capacity;
            value[rear] = request;
            size++;

        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    public synchronized Request dequeue() {

        if (isEmpty()) {
            return null;
        }

        Request result = value[front];

        front = (front + 1) % capacity;
        size--;

        notify();

        return result;
    }

    public synchronized int size() {
        return size;
    }

    public synchronized boolean isEmpty() {
        return size() == 0;
    }

    public synchronized Boolean isFull() {
        return size() == capacity;
    }

    public synchronized void setMoreElements(boolean moreElements) {
        this.moreElements = moreElements;
        notify();
    }
}
