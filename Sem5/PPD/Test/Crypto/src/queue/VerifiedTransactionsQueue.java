package queue;

import model.VerifiedTransaction;

public class VerifiedTransactionsQueue {

    private final VerifiedTransaction[] value;
    private int front;
    private int rear;
    private int size;
    private final int capacity;
    private final boolean[] noMoreElements;

    public VerifiedTransactionsQueue(int capacity, int supervisors) {
        this.value = new VerifiedTransaction[capacity];
        this.capacity = capacity;
        this.size = 0;
        this.front = 0;
        this.rear = -1;
        this.noMoreElements = new boolean[supervisors];
    }

    public synchronized void enqueue(VerifiedTransaction transaction) {

        try {
            // wait if the queue is full
            while (isFull()) {
                wait();
            }

            // add to the queue
            rear = (rear + 1) % capacity;
            value[rear] = transaction;
            size++;

            notify();

        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    public synchronized VerifiedTransaction dequeue() {

        try {
            while (isEmpty() && checkMoreElements()) {
                wait();
            }

            if (isEmpty() && !checkMoreElements()) {
                return null;
            }

            VerifiedTransaction result = value[front];

            front = (front + 1) % capacity;
            size--;

            // notify for supervisior if queue is full
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
