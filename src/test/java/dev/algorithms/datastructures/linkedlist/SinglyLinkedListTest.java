package dev.algorithms.datastructures.linkedlist;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SinglyLinkedListTest {
    @Test
    void startsEmpty() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
        assertNull(list.first());
        assertNull(list.last());
    }

    @Test
    void addFirstPrependsElements() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.addFirst("first");
        list.addFirst("latest");
        assertEquals(2, list.size());
        assertEquals("latest", list.first());
        assertEquals("first", list.last());
    }

    @Test
    void addLastWhenIsEmpty() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.addLast("first");
        assertEquals(1, list.size());
        assertEquals("first", list.first());
        assertEquals("first", list.last());
    }

    @Test
    void addLastAppendElements() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();

        list.addLast("first");
        list.addLast("second");
        assertEquals(2, list.size());
        assertEquals("first", list.first());
        assertEquals("second", list.last());
    }

    @Test()
    void removeFirstReturnsNullWhenIsEmpty() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        assertNull(list.removeFirst());
    }

    @Test
    void removeFirstRemovesElementsFromHead() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.addLast("first");
        list.addLast("second");
        list.addLast("third");

        assertEquals("first", list.removeFirst());
        assertEquals("second", list.first());
        assertEquals("third", list.last());
        assertEquals(2, list.size());
    }

    @Test
    void removeFirstClearsTailAndHeadWhenRemoveLastElement() {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.addLast("first");

        assertEquals("first", list.removeFirst());
        assertNull(list.last());
        assertNull(list.first());
        assertEquals(0, list.size());
    }

    @Test
    void addFirstAndAddLastMaintainHeadAndTail() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        list.addFirst(2);
        list.addLast(3);
        list.addFirst(1);

        assertEquals(1, list.first());
        assertEquals(3, list.last());
        assertEquals(3, list.size());
    }

    @Test
    void equalsReturnsTrueForSameElementsInSameOrder() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        SinglyLinkedList<Integer> other = new SinglyLinkedList<>();

        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        other.addLast(1);
        other.addLast(2);
        other.addLast(3);

        // Symmetry
        assertEquals(list, other);
        assertEquals(other, list);
    }

    @Test
    void equalsReturnsTrueForEmptyLists() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        SinglyLinkedList<Integer> other = new SinglyLinkedList<>();

        assertEquals(list, other);
    }

    @Test
    void equalsReturnsTrueForSameInstance() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.addLast(1);

        // Reflexivity
        assertEquals(list, list);
    }

    @Test
    void equalsReturnsFalseForNull() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        // Treatment of null
        assertFalse(list.equals(null));
    }

    @Test
    void equalsReturnsFalseForDifferentType() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        assertNotEquals("not a list", list);
    }

    @Test
    void equalsReturnsFalseWhenSizesAreDifferent() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        SinglyLinkedList<Integer> other = new SinglyLinkedList<>();

        list.addLast(1);
        list.addLast(2);
        other.addLast(1);

        assertNotEquals(list, other);
    }

    @Test
    void equalsReturnsFalseWhenElementsDiffer() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        SinglyLinkedList<Integer> other = new SinglyLinkedList<>();

        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        other.addLast(1);
        other.addLast(4);
        other.addLast(3);

        assertNotEquals(list, other);
    }

    @Test
    void equalsReturnsFalseWhenElementOrderDiffers() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        SinglyLinkedList<Integer> other = new SinglyLinkedList<>();

        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        other.addLast(3);
        other.addLast(2);
        other.addLast(1);

        assertNotEquals(list, other);
    }

    @Test
    void equalsIsTransitive() {
        SinglyLinkedList<Integer> first = new SinglyLinkedList<>();
        SinglyLinkedList<Integer> second = new SinglyLinkedList<>();
        SinglyLinkedList<Integer> third = new SinglyLinkedList<>();

        first.addLast(1);
        first.addLast(2);
        first.addLast(3);
        second.addLast(1);
        second.addLast(2);
        second.addLast(3);
        third.addLast(1);
        third.addLast(2);
        third.addLast(3);

        assertEquals(first, second);
        assertEquals(second, third);
        assertEquals(first, third);
    }
}
