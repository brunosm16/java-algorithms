package dev.list;

public interface Position<E> {
    /**
     * Retrieve the element stored at given position. If position was previously removed
     * an error is thrown.
     * @return - stored element
     * @throws IllegalStateException - if position no longer valid.
     */
    E getElement() throws IllegalStateException;
}
