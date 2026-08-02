package dev.algorithms.datastructures.linkedlist;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CircularLinkedListTest {
    @Test
    void startsEmpty() {
        CircularLinkedList<String> list = new CircularLinkedList<>();

        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
        assertNull(list.first());
        // TODO: Create assertion for last()
    }

    @Test
    void addFirstPrependOneElements() {
        CircularLinkedList<String> list = new CircularLinkedList<>();

        list.addFirst("FOR");
        assertEquals("FOR", list.first());
        assertEquals(1, list.size());
    }

    @Test
    void addFirstPrependElementsWhenHasOneElement() {
        CircularLinkedList<String> list = new CircularLinkedList<>();

        list.addFirst("FOR");
        assertEquals("FOR", list.first());
        assertEquals(1, list.size());
        list.addFirst("GRU");
        assertEquals("GRU", list.first());
        assertEquals(2, list.size());
    }

    @Test
    void addFirstPrependElements() {
        CircularLinkedList<String> list = new CircularLinkedList<>();

        list.addFirst("FOR");
        list.addFirst("GRU");
        list.addFirst("CGH");
        assertEquals("CGH", list.first());
        assertEquals(3, list.size());
    }
}
