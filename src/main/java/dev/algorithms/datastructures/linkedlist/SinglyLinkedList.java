package dev.algorithms.datastructures.linkedlist;

public class SinglyLinkedList<E> {
    private static class Node<E> {
        private E element = null;
        private Node<E> next = null;

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


    // TODO: add constructor
    // TODO: addFirst
    // TODO: removeFirst

    private int size = 0;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E first() {
        return head.getElement();
    }

    public E last() {
        return tail.getElement();
    }
}
