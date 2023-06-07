package queue;

import model.VerifiedRequest;

public class VerifiedRequestQueue {

    private final VerifiedRequest[] value;
    private int front;
    private int rear;
    private int size;
    private final int capacity;
    private final boolean[] noMoreElements;

    public VerifiedRequestQueue(int capacity, int functionari) {
        this.value = new VerifiedRequest[capacity];
        this.capacity = capacity;
        this.size = 0;
        this.front = 0;
        this.rear = -1;
        this.noMoreElements = new boolean[functionari];
    }

    public synchronized void enqueue(VerifiedRequest request) {

        try {

            while (isFull()) {
                wait();
            }

            rear = (rear + 1) % capacity;
            value[rear] = request;
            size++;

            notify();

        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    public synchronized VerifiedRequest dequeue() {

        try {
            while (isEmpty() && checkMoreElements()) {
                wait();
            }

            if (isEmpty() && !checkMoreElements()) {
                return null;
            }

            VerifiedRequest result = value[front];

            front = (front + 1) % capacity;
            size--;

            notify();

            return result;

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    private boolean checkMoreElements() {
        for (boolean noMoreElement : noMoreElements) {
            if (!noMoreElement) {
                return true;
            }
        }
        return false;
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

    public synchronized void setNoMoreElements() {
        int i = 0;
        while (this.noMoreElements[i]) {
            i++;
        }
        this.noMoreElements[i] = true;
        notify();
    }
}
