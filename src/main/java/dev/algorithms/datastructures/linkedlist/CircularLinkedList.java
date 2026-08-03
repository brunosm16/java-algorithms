package dev.algorithms.datastructures.linkedlist;

public class CircularLinkedList<E> {
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

    private Node<E> tail = null;
    private int size = 0;

    public CircularLinkedList() {
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
        return tail.getNext().getElement();
    }

    public E last() {
        if (isEmpty()) {
            return null;
        }
        return tail.getElement();
    }

    public void addFirst(E element) {
        if (isEmpty()) {
            tail = new Node<>(element, null);
            tail.setNext(tail);
        } else {
            Node<E> newest = new Node<>(element, tail.getNext());
            tail.setNext(newest);
        }
        size++;
    }

    public void rotate() {
        if (!isEmpty()) {
            tail = tail.getNext();
        }
    }

    public void addLast(E element) {
        addFirst(element);
        tail = tail.getNext();
    }

    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }

        Node<E> currentHead = tail.getNext();

        if (currentHead == tail) {
            tail = null;
        } else {
            tail.setNext(currentHead.getNext());
        }
        size--;

        return currentHead.getElement();
    }
}
