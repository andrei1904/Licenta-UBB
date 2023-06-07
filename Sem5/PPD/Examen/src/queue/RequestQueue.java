package queue;

import model.Request;

public class RequestQueue {

    private final Request[] value;
    private int front;
    private int rear;
    private int size;
    private final int capacity;
    private final boolean[] noMoreElements;

    public RequestQueue(int capacity, int citizens) {
        this.value = new Request[capacity];
        this.capacity = capacity;
        this.size = 0;
        this.front = 0;
        this.rear = -1;
        this.noMoreElements = new boolean[citizens];
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

        try {
            while (isEmpty() && checkMoreElements()) {
                wait();
            }

            if (isEmpty() && !checkMoreElements()) {
                return null;
            }

            Request result = value[front];

            front = (front + 1) % capacity;
            size--;

            notify();

            return result;

        } catch (InterruptedException exception) {
            exception.printStackTrace();
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
