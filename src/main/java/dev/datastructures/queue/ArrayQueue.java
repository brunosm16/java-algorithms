package dev.datastructures.queue;

public class ArrayQueue<E> implements Queue<E> {
    private static final int QUEUE_LIMIT = 100;

    private final E[] data;

    private int front = 0;

    private int size = 0;

    public ArrayQueue() {
        this(QUEUE_LIMIT);
    }

    @SuppressWarnings("unchecked")
    public ArrayQueue(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative");
        }

        data = (E[]) new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enqueue(E e) throws IllegalStateException {
        if (isFull()) throw new IllegalStateException("Queue is full");

        int nextIndex = (front + size) % data.length;

        data[nextIndex] = e;

        size++;
    }

    @Override
    public E first() {
        if (isEmpty()) return null;
        return data[front];
    }

    @Override
    public E dequeue() {
        if (isEmpty()) return null;

        E result = data[front];

        data[front] = null;

        front = (front + 1) % data.length;

        size--;

        return result;
    }

    private boolean isFull() {
        return size == data.length;
    }
}
