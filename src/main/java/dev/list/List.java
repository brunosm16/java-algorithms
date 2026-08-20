package dev.list;

/**
 * Alternative version of the {@link java.util.List} interface.
 *
 * @param <E> the type of elements stored in the list
 */
public interface List<E> {
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
     * Returns, but does not remove, the element at the specified index.
     *
     * @param idx the index of the element to return
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if the index is outside the valid range
     *                                   of the list
     */
    E get(int idx) throws IndexOutOfBoundsException;

    /**
     * Inserts an element at the specified index, shifting the element currently
     * at that position and any subsequent elements to the right.
     *
     * @param idx the index at which the element should be inserted
     * @param e   the element to insert
     * @throws IndexOutOfBoundsException if the index is outside the valid range
     *                                   of the list
     */
    void add(int idx, E e) throws IndexOutOfBoundsException;

    /**
     * Replaces the element at the specified index.
     *
     * @param idx the index of the element to replace
     * @param e   the new element
     * @return the element previously stored at the specified index
     * @throws IndexOutOfBoundsException if the index is outside the valid range
     *                                   of the list
     */
    E replace(int idx, E e) throws IndexOutOfBoundsException;

    /**
     * Removes and returns the element at the specified index, shifting any
     * subsequent elements to the left.
     *
     * @param idx the index of the element to remove
     * @return the removed element
     * @throws IndexOutOfBoundsException if the index is outside the valid range
     *                                   of the list
     */
    E remove(int idx) throws IndexOutOfBoundsException;
}
