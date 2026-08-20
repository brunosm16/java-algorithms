package dev.datastructures.queue;

/**
 * A collection of elements that are inserted and removed according to the
 * first-in, first-out principle.
 *
 * @param <E> the type of elements stored in the queue
 */
public interface Queue<E> {
    /**
     * Returns the number of elements currently stored in the queue.
     *
     * @return the number of elements in the queue
     */
    int size();

    /**
     * Tests whether the queue contains no elements.
     *
     * @return {@code true} if the queue is empty, otherwise {@code false}
     */
    boolean isEmpty();

    /**
     * Inserts an element at the back of the queue.
     *
     * @param e the element to insert
     */
    void enqueue(E e);

    /**
     * Returns, but does not remove, the element at the front of the queue.
     *
     * @return the front element, or {@code null} if the queue is empty
     */
    E first();

    /**
     * Removes and returns the element at the front of the queue.
     *
     * @return the removed front element, or {@code null} if the queue is empty
     */
    E dequeue();
}
