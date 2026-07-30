package dev.algorithms.datastructures.linkedlist;

public class SinglyLinkedList<E> {
    private static class Node<E> {
        private final E element;
        private Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }

    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    public SinglyLinkedList() {
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E first() {
        if (this.isEmpty()) {
            return null;
        }
        return head.getElement();
    }

    public E last() {
        if (this.isEmpty()) {
            return null;
        }

        return tail.getElement();
    }

    public void addFirst(E element) {
        head = new Node<>(element, this.head);

        if (this.isEmpty()) {
            tail = head;
        }

        size++;
    }

    public void addLast(E element) {
        Node<E> newest = new Node<>(element, null);

        if (this.isEmpty()) {
            head = newest;
        } else {
            tail.setNext(newest);
        }

        tail = newest;
        size++;
    }

    public E removeFirst() {
        if (this.isEmpty()) {
            return null;
        }

        E current = head.getElement();
        head = head.getNext();
        size--;

        if(size == 0) {
            tail = null;
        }
        return current;
    }
}
