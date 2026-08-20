package dev.datastructures.stack;

import dev.datastructures.linkedlist.SinglyLinkedList;

public class LinkedStack<E> implements Stack<E> {
    private final SinglyLinkedList<E> list = new SinglyLinkedList<>();

    public LinkedStack() {
    }


    @Override
    public E top() {
        return list.first();
    }

    @Override
    public E pop() {
        return list.removeFirst();
    }

    @Override
    public void push(E e) {
        // Makes the top of the stack as the front of the list
        list.addFirst(e);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
