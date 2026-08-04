package dev.algorithms.datastructures.linkedlist;

public class DoublyLinkedList<E> {
    private static class Node<E> {
        private final E element;
        private Node<E> prev;
        private Node<E> next;

        public Node(E el, Node<E> pre, Node<E> ne) {
            element = el;
            prev = pre;
            next = ne;
        }

        public void setPrev(Node<E> pre) {
            prev = pre;
        }

        public void setNext(Node<E> ne) {
            next = ne;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        public Node<E> getPrev() {
            return prev;
        }
    }

    private Node<E> header;
    private Node<E> trailer;
    private int size = 0;

    public DoublyLinkedList() {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.setNext(trailer);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E first() {
        if (isEmpty()) {
            return null;
        }
        return header.getNext().getElement();
    }

    public E last() {
        if(isEmpty()) {
            return null;
        }

        return trailer.getPrev().getElement();
    }

    public void addFirst(E el) {
        addBetween(el, header, header.getNext());
    }

    public void addLast(E el) {
        addBetween(el, trailer.getPrev(), trailer);
    }

    private void addBetween(E el, Node<E> predecessor, Node<E> successor) {
        Node<E> newest = new Node<>(el, predecessor, successor);

        predecessor.setNext(newest);
        successor.setPrev(newest);
        size++;
    }
}
