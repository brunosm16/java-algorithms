package dev.list;

public class ArrayList<E> implements List<E> {
    public static final int CAPACITY = 16;

    private E[] data;

    private int size = 0;

    public ArrayList() {
        this(CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) throws IllegalArgumentException {
        if (capacity <= 0) {
            throw new IllegalArgumentException("'Capacity' must be a positive integer");
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
    public E get(int idx) throws IndexOutOfBoundsException {
        validateIndex(idx, size);

        return data[idx];
    }

    @Override
    public void add(int idx, E e) throws IndexOutOfBoundsException {
        validateIndex(idx, size + 1);
        validateMaxCapacity();

        for (int i = size - 1; i >= idx; i--) {
            data[i + 1] = data[i];
        }

        data[idx] = e;
        size++;
    }

    @Override
    public E replace(int idx, E e) throws IndexOutOfBoundsException {
        validateIndex(idx, size);

        E toBeReplaced = data[idx];

        data[idx] = e;

        return toBeReplaced;
    }

    @Override
    public E remove(int idx) throws IndexOutOfBoundsException {
        validateIndex(idx, size);

        E toRemove = data[idx];

        for (int i = idx; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        data[size - 1] = null;
        size--;

        return toRemove;
    }

    private void validateIndex(int idx, int range) throws IndexOutOfBoundsException {
        if (idx >= range || idx < 0) {
            throw new IndexOutOfBoundsException("Index not valid. It must be a positive integer less than current array size");
        }
    }

    private void validateMaxCapacity() throws IndexOutOfBoundsException {
        if (size == data.length) {
            resize(data.length * 2);
        }
    }

    @SuppressWarnings("unchecked")
    protected void resize(int capacity) {
        E[] temp = (E[]) new Object[capacity];

        for (int i = 0; i < size; i++) {
            temp[i] = data[i];
        }

        data = temp;
    }
}
