package queue;

import model.Transaction;

public class TransactionsQueue {

    private final Transaction[] value;
    private int front;
    private int rear;
    private int size;
    private final int capacity;
    private boolean moreElements;

    public TransactionsQueue(int capacity) {
        this.value = new Transaction[capacity];
        this.capacity = capacity;
        this.size = 0;
        this.front = 0;
        this.rear = -1;
        this.moreElements = true;
    }

    public synchronized void enqueue(Transaction transaction) {

        try {
            // wait if the queue is full
            while (isFull()) {
                wait();
            }

            // add to the queue
            rear = (rear + 1) % capacity;
            value[rear] = transaction;
            size++;

        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    public synchronized Transaction dequeue() {

        if (isEmpty()) {
            return null;
        }

        Transaction result = value[front];

        front = (front + 1) % capacity;
        size--;

        // notify a thread that is waiting (for users)
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
