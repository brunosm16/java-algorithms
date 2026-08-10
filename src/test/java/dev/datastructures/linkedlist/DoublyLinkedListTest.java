package dev.datastructures.linkedlist;

import dev.datastructures.linkedlist.DoublyLinkedList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DoublyLinkedListTest {
    @Test
    void startsEmpty() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
        assertNull(list.first());
        assertNull(list.last());
    }

    @Test
    void addFirstPrependsElements() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        list.addFirst("first");
        list.addFirst("latest");

        assertEquals(2, list.size());
        assertEquals("latest", list.first());
        assertEquals("first", list.last());
    }

    @Test
    void addLastWhenIsEmpty() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        list.addLast("first");

        assertEquals(1, list.size());
        assertEquals("first", list.first());
        assertEquals("first", list.last());
    }

    @Test
    void addLastAppendElements() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        list.addLast("first");
        list.addLast("second");

        assertEquals(2, list.size());
        assertEquals("first", list.first());
        assertEquals("second", list.last());
    }

    @Test
    void addFirstAndAddLastMaintainHeadAndTail() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        list.addFirst(2);
        list.addLast(3);
        list.addFirst(1);

        assertEquals(3, list.size());
        assertEquals(1, list.first());
        assertEquals(3, list.last());
    }

    @Test
    void removeFirstEmptyReturnsNull() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        assertNull(list.removeFirst());
    }

    @Test
    void shouldRemoveFirstElement() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);

        assertEquals(3, list.size());

        Integer removedElement = list.removeFirst();

        assertEquals(3, removedElement);
        assertEquals(2, list.size());
        assertEquals(2, list.first());
    }

    @Test
    void shouldRemoveFirstElementWhenItsTheOnlyElement() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        list.addFirst(1);

        assertEquals(1, list.size());

        Integer removedElement = list.removeFirst();

        assertEquals(1, removedElement);
        assertEquals(0, list.size());
        assertNull(list.last());
        assertNull(list.first());
    }

    @Test
    void shouldRemoveLastElement() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        list.addLast(1);
        list.addLast(2);
        list.addLast(3);

        assertEquals(3, list.size());

        Integer removedElement = list.removeLast();

        assertEquals(3, removedElement);
        assertEquals(2, list.size());
        assertEquals(2, list.last());
    }

    @Test
    void shouldRemoveLastElementWhenItsTheOnlyElement() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        list.addFirst(1);

        assertEquals(1, list.size());

        Integer removedElement = list.removeLast();

        assertEquals(1, removedElement);
        assertEquals(0, list.size());
        assertNull(list.last());
        assertNull(list.first());
    }

    @Test
    void removeLastEmptyReturnsNull() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        assertNull(list.removeLast());
    }

    @Test
    void removeLastWhenEmptyReturnsNull() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        assertNull(list.removeLast());
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        assertNull(list.first());
        assertNull(list.last());
    }

    @Test
    void removeFirstKeepsLastElement() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        list.addLast(1);
        list.addLast(2);
        list.addLast(3);

        assertEquals(1, list.removeFirst());
        assertEquals(2, list.first());
        assertEquals(3, list.last());
        assertEquals(2, list.size());
    }

    @Test
    void removeLastKeepsFirstElement() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        list.addLast(1);
        list.addLast(2);
        list.addLast(3);

        assertEquals(3, list.removeLast());
        assertEquals(1, list.first());
        assertEquals(2, list.last());
        assertEquals(2, list.size());
    }

    @Test
    void removeFirstAndRemoveLastCanEmptyList() {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        list.addLast(1);
        list.addLast(2);

        assertEquals(1, list.removeFirst());
        assertEquals(2, list.removeLast());
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        assertNull(list.first());
        assertNull(list.last());
    }
}
