package dev.list;

public interface PositionalList<E> extends Iterable<E> {
    /**
     * Returns the number of elements in the list.
     *
     * @return - the number of elements in the list.
     */
    int size();

    /**
     * Indicates whether the list is empty.
     *
     * @return - true if the list contains no elements, false otherwise.
     */
    boolean isEmpty();

    /**
     * @return - returns the first Position in the list, if empty returns null.
     */
    Position<E> first();

    /**
     * @return - returns the last Position in the list, if empty returns null.
     */
    Position<E> last();

    /**
     * @param p - reference position.
     * @return - returns Position before given position p or null if p is first.
     */
    Position<E> before(Position<E> p);

    /**
     * @param p - reference position.
     * @return - returns Position after given position p or null if p is last.
     */
    Position<E> after(Position<E> p);

    /**
     * Inserts an element at the beginning of the list.
     *
     * @param e - the element to insert.
     * @return - the position of the inserted element.
     */
    Position<E> addFirst(E e);

    /**
     * Inserts an element at the end of the list.
     *
     * @param e - the element to insert.
     * @return - the position of the inserted element.
     */
    Position<E> addLast(E e);

    /**
     * Inserts an element immediately before the given position.
     *
     * @param p - the reference position.
     * @param e - the element to insert.
     * @return - the position of the inserted element.
     * @throws IllegalArgumentException - if p is not a valid position in this list.
     */
    Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException;

    /**
     * Inserts an element immediately after the given position.
     *
     * @param p - the reference position.
     * @param e - the element to insert.
     * @return - the position of the inserted element.
     * @throws IllegalArgumentException - if p is not a valid position in this list.
     */
    Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException;

    /**
     * Replaces the element stored at the given position.
     *
     * @param p - the position of the element to replace.
     * @param e - the replacement element.
     * @return - the element previously stored at p.
     * @throws IllegalArgumentException - if p is not a valid position in this list.
     */
    E replaceEl(Position<E> p, E e) throws IllegalArgumentException;

    /**
     * Removes the element at the given position, invalidating that position.
     *
     * @param p - the position of the element to remove.
     * @return - the removed element.
     * @throws IllegalArgumentException - if p is not a valid position in this list.
     */
    E remove(Position<E> p) throws IllegalArgumentException;
}
