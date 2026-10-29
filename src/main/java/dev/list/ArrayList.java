package dev.list;

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
        isArrayCapacityFull();

        for(int i = size - 1; i >= idx; i--) {
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
        return null;
    }

    protected void isArrayCapacityFull() throws IllegalStateException {
        if (size == data.length) {
            throw new IllegalStateException("Array capacity is full");
        }
    }

    protected void validateIdx(int idx, int upperLimit) {
        if (idx < 0 || idx >= upperLimit) {
            throw new IllegalArgumentException("Current index is out of bounds" + "Upper Limit");
        }
    }
}
