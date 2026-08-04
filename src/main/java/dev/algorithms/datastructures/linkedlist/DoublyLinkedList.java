package dev.algorithms.datastructures.linkedlist;

public class DoublyLinkedList<E> {
    private static class Node<E> {
        private final E element;
        private Node<E> prev;
        private Node<E> next;

        public Node(E el, Node<E> pre, Node<E> ne){
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
    }
}
