package dev.datastructures.queue;

import dev.datastructures.linkedlist.SinglyLinkedList;

import java.util.LinkedList;

public class LinkedQueue<E> implements Queue<E>{
    private final SinglyLinkedList<E> list = new SinglyLinkedList<>();

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
