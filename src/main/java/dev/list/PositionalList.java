package dev.list;

/**
 * Defines a list whose elements are accessed through position objects rather
 * than numeric indexes.
 *
 * @param <E> the type of elements stored in the list
 */
public interface PositionalList <E> {
    /**
     * Returns the number of elements currently stored in the list.
     *
     * @return the number of elements in the list
     */
    int size();

    /**
     * Tests whether the list contains no elements.
     *
     * @return {@code true} if the list is empty, otherwise {@code false}
     */
    boolean isEmpty();

    /**
     * Returns the first position in the list.
     *
     * @return the first position, or {@code null} if the list is empty
     */
    Position<E> first();

    /**
     * Returns the last position in the list.
     *
     * @return the last position, or {@code null} if the list is empty
     */
    Position<E> last();

    /**
     * Returns the position immediately before the given position.
     *
     * @param p the reference position
     * @return the position immediately before {@code p}, or {@code null} if
     *         {@code p} is the first position
     * @throws IllegalArgumentException if {@code p} is not a valid position for
     *                                  this list
     */
    Position<E> before(Position<E> p) throws IllegalArgumentException;

    /**
     * Returns the position immediately after the given position.
     *
     * @param p the reference position
     * @return the position immediately after {@code p}, or {@code null} if
     *         {@code p} is the last position
     * @throws IllegalArgumentException if {@code p} is not a valid position for
     *                                  this list
     */
    Position<E> after(Position<E> p) throws IllegalArgumentException;

    /**
     * Inserts an element at the front of the list.
     *
     * @param e the element to insert
     * @return the position of the inserted element
     */
    Position<E> addFirst(E e);

    /**
     * Inserts an element at the back of the list.
     *
     * @param e the element to insert
     * @return the position of the inserted element
     */
    Position<E> addLast(E e);

    /**
     * Inserts an element immediately before the given position.
     *
     * @param p the reference position
     * @param e the element to insert
     * @return the position of the inserted element
     * @throws IllegalArgumentException if {@code p} is not a valid position for
     *                                  this list
     */
    Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException;

    /**
     * Inserts an element immediately after the given position.
     *
     * @param p the reference position
     * @param e the element to insert
     * @return the position of the inserted element
     * @throws IllegalArgumentException if {@code p} is not a valid position for
     *                                  this list
     */
    Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException;

    /**
     * Replaces the element stored at the given position.
     *
     * @param p the position containing the element to replace
     * @param e the replacement element
     * @return the element previously stored at {@code p}
     * @throws IllegalArgumentException if {@code p} is not a valid position for
     *                                  this list
     */
    E replace(Position<E> p, E e) throws IllegalArgumentException;

    /**
     * Removes and returns the element stored at the given position.
     *
     * @param p the position of the element to remove
     * @return the removed element
     * @throws IllegalArgumentException if {@code p} is not a valid position for
     *                                  this list
     */
    E remove(Position<E> p) throws IllegalArgumentException;
}
