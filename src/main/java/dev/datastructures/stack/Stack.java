package dev.datastructures.stack;

/**
 * A collection of elements that are inserted and removed according to the
 * last-in, first-out principle.
 *
 * @param <E> the type of elements stored in the stack
 */
public interface Stack<E> {
    /**
     * Returns, but does not remove, the element at the top of the stack.
     *
     * @return the top element, or {@code null} if the stack is empty
     */
    E top();

    /**
     * Removes and returns the element at the top of the stack.
     *
     * @return the removed top element, or {@code null} if the stack is empty
     */
    E pop();

    /**
     * Inserts an element at the top of the stack.
     *
     * @param e the element to insert
     */
    void push(E e);

    /**
     * Returns the number of elements currently stored in the stack.
     *
     * @return the number of elements in the stack
     */
    int size();

    /**
     * Tests whether the stack contains no elements.
     *
     * @return {@code true} if the stack is empty, otherwise {@code false}
     */
    boolean isEmpty();
}
