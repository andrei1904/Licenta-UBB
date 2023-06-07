public class Queue {

    private final Monomial[] data;
    private int front;
    private int rear;
    private int size;
    private final int capacity;
    private boolean moreElements;

    public Queue(int capacity) {
        this.data = new Monomial[capacity];
        this.capacity = capacity;
        this.size = 0;
        this.front = 0;
        this.rear = -1;
        this.moreElements = true;
    }

    public synchronized void enqueue(Monomial item) {

        if (isFull()) {
            System.out.println("Queue is full");
            return;
        }

        rear = (rear + 1) % capacity;
        data[rear] = item;
        size++;

        notifyAll();
    }

    public synchronized Monomial dequeue() {

        try {
            while (isEmpty() && moreElements) {
                wait();
            }

            if (isEmpty() && !moreElements) {
                return null;
            }

            Monomial result = data[front];

            front = (front + 1) % capacity;
            size--;

            return result;

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        return null;
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
        notifyAll();
    }
}
