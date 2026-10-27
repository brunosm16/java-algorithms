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
        return false;
    }

    @Override
    public E get(int idx) throws IndexOutOfBoundsException {
        return null;
    }

    @Override
    public void add(int idx, E e) throws IndexOutOfBoundsException {

    }

    @Override
    public E replace(int idx, E e) throws IndexOutOfBoundsException {
        return null;
    }

    @Override
    public E remove(int idx) throws IndexOutOfBoundsException {
        return null;
    }
}
