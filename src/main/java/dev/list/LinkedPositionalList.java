package dev.list;

public class LinkedPositionalList<E> implements PositionalList<E> {
    /**
     * Nested Node class
     *
     * @param <E> - generic type for List.
     */
    private static class Node<E> implements Position<E> {
        private E element;

        private Node<E> prev;

        private Node<E> next;

        private Node(E e, Node<E> p, Node<E> n) {
            element = e;
            prev = p;
            next = n;
        }

        @Override
        public E getElement() throws IllegalStateException {
            /*
             * Convention for defunct node, consider that a node with next
             * equal to null represents a node that has been removed.
             */
            if (next == null) {
                throw new IllegalStateException("Position no longer valid");
            }

            return element;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setElement(E e) {
            element = e;
        }

        public void setPrev(Node<E> e) {
            prev = e;
        }

        public void setNext(Node<E> e) {
            next = e;
        }
    }

    private Node<E> header;

    private Node<E> trailer;

    private int size = 0;

    public LinkedPositionalList() {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);

        header.setNext(trailer);
    }

    private Node<E> getPositionAsNode(Position<E> p) throws IllegalArgumentException {
        if(!(p instanceof Node<E> node)) {
            throw new IllegalArgumentException("Invalid position p instance");
        }

        if(node.getNext() == null) {
            throw new IllegalArgumentException("Position no longer valid");
        }

        return node;
    }

    private Position<E> getNodeAsPosition(Node<E> node) {
        // Do not expose sentinel nodes.
        if(node == trailer || node == header) {
            return null;
        }

        return node;
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
    public Position<E> first() {
        return getNodeAsPosition(header.getNext());
    }

    @Override
    public Position<E> last() {
        return getNodeAsPosition(trailer.getPrev());
    }

    @Override
    public Position<E> before(Position<E> p) {
        Node<E> node = getPositionAsNode(p);
        return getNodeAsPosition(node.getPrev());
    }

    @Override
    public Position<E> after(Position<E> p) {
        Node<E> node = getPositionAsNode(p);
        return getNodeAsPosition(node.getNext());
    }

    @Override
    public Position<E> addFirst(E e) {
        return null;
    }

    @Override
    public Position<E> addLast(E e) {
        return null;
    }

    @Override
    public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
        return null;
    }

    @Override
    public E replaceEl(Position<E> p, E e) throws IllegalArgumentException {
        return null;
    }

    @Override
    public E remove(Position<E> p) throws IllegalArgumentException {
        return null;
    }
}
