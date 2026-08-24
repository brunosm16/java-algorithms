package dev.list;

/**
 * Represents the location of an element within a positional list.
 *
 * @param <E> the type of element stored at the position
 */
public interface Position <E> {
    /**
     * Returns the element stored at this position.
     *
     * @return the stored element
     * @throws IllegalStateException if this position is no longer valid
     */
    E getElement() throws IllegalStateException;
}
