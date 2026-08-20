package dev.datastructures.queue;

import dev.datastructures.linkedlist.CircularLinkedList;

public class CircularLinkedQueue<E> implements CircularQueue<E> {
    private final CircularLinkedList<E> list = new CircularLinkedList<>();

    @Override
    public void rotate() {
        list.rotate();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        list.addLast(e);
    }

    @Override
    public E first() {
        return list.first();
    }

    @Override
    public E dequeue() {
        return list.removeFirst();
    }
}
