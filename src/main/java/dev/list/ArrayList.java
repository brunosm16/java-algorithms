package dev.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<E> implements List<E> {
    /**
     * Default array capacity only allows a maximum of
     * 16 elements fixed elements.
     */
    public static final int MAX_CAPACITY = 16;

    /**
     * Generic array used for storage.
     */
    private E[] data;

    private int size = 0;

    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IndexOutOfBoundsException("Capacity of array cannot be negative: " + capacity);
        }

        data = (E[]) new Object[capacity];
    }

    public ArrayList() {
        this(MAX_CAPACITY);
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
    public E get(int idx) throws IndexOutOfBoundsException {
        validateIdx(idx, size);
        return data[idx];
    }

    @Override
    public void add(int idx, E e) throws IndexOutOfBoundsException {
        validateIdx(idx, size + 1);

        if (isArrayCapacityFull()) {
            resize(Math.max(1, size * 2));
        }

        for (int i = size - 1; i >= idx; i--) {
            // shift elements to right to build space for
            // the element to be inserted
            data[i + 1] = data[i];
        }

        putElement(idx, e);
    }

    private void putElement(int idx, E e) {
        data[idx] = e;
        size++;
    }

    @Override
    public E replace(int idx, E e) throws IndexOutOfBoundsException {
        validateIdx(idx, size);
        E replaced = data[idx];
        data[idx] = e;
        return replaced;
    }

    @Override
    public E remove(int idx) throws IndexOutOfBoundsException {
        validateIdx(idx, size);

        E removed = data[idx];

        for (int i = idx; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        data[size - 1] = null;
        size--;
        return removed;
    }

    private boolean isArrayCapacityFull() {
        return size == data.length;
    }

    protected void validateIdx(int idx, int upperLimit) {
        if (idx < 0 || idx >= upperLimit) {
            throw new IllegalArgumentException("Current index is out of bounds" + "Upper Limit");
        }
    }

    @SuppressWarnings("unchecked")
    protected void resize(int capacity) {
        E[] temp = (E[]) new Object[capacity];

        if (size > 0) {
            System.arraycopy(data, 0, temp, 0, size);
        }

        data = temp;
    }

    private class ArrayIterator implements Iterator<E> {
        private int nextIdx = 0;
        private boolean isAllowedToRemove = false;


        @Override
        public boolean hasNext() {
            return nextIdx < size;
        }

        @Override
        public E next() throws NoSuchElementException {
            if (nextIdx == size) {
                throw new NoSuchElementException("Next element not available");
            }

            isAllowedToRemove = true;

            return data[nextIdx++];
        }

        public void remove() throws IllegalStateException {
            // Freeze remove only when next() is called
            if (!isAllowedToRemove) {
                throw new IllegalStateException("No element available to be removed");
            }

            ArrayList.this.remove(nextIdx - 1);
            nextIdx--;

            // Remove only allowed when next() is called again
            isAllowedToRemove = false;
        }
    }

    public Iterator<E> iterator() {
        return new ArrayIterator();
    }
}
