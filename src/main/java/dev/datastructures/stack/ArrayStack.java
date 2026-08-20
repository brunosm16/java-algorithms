package dev.datastructures.stack;

public class ArrayStack<E> implements Stack<E> {
    public static final int MAX_CAPACITY = 1000;
    private final E[] data;
    private int t = -1;

    public ArrayStack() {
        this(MAX_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayStack(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative");
        }

        data = (E[]) new Object[capacity];
    }

    @Override
    public E top() {
        if(isEmpty()) return null;
        return data[t];
    }

    @Override
    public E pop() {
        if(isEmpty()) return null;

        E result = data[t];
        data[t] = null;
        t--;
        return result;
    }

    @Override
    public void push(E e) throws IllegalStateException{
        if(size() == data.length) throw new IllegalStateException("Max capacity of Stack reached");
        data[++t] = e;
    }

    @Override
    public int size() {
        return t + 1;
    }

    @Override
    public boolean isEmpty() {
        return t == -1;
    }
}
