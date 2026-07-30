package dev.algorithms.datastructures.linkedlist;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
}
