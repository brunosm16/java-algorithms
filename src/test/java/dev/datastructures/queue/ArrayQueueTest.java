package dev.datastructures.queue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArrayQueueTest {
    @Test
    void startsEmpty() {
        ArrayQueue<Integer> queue = new ArrayQueue<>();

        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
        assertNull(queue.first());
    }

    @Test
    void enqueueAddsElementToBack() {
        ArrayQueue<String> queue = new ArrayQueue<>();

        queue.enqueue("first");

        assertFalse(queue.isEmpty());
        assertEquals(1, queue.size());
        assertEquals("first", queue.first());
    }

    @Test
    void firstDoesNotRemoveElement() {
        ArrayQueue<String> queue = new ArrayQueue<>();
        queue.enqueue("first");
        queue.enqueue("second");

        assertEquals("first", queue.first());
        assertEquals("first", queue.first());
        assertEquals(2, queue.size());
    }

    @Test
    void dequeueRemovesElementsInFirstInFirstOutOrder() {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    @Test
    void dequeueReturnsNullWhenQueueIsEmpty() {
        ArrayQueue<Integer> queue = new ArrayQueue<>();

        assertNull(queue.dequeue());
    }

    @Test
    void constructorRejectsNegativeCapacity() {
        assertThrows(IllegalArgumentException.class, () -> new ArrayQueue<>(-1));
    }

    @Test
    void enqueueRejectsElementsBeyondConfiguredCapacity() {
        ArrayQueue<Integer> queue = new ArrayQueue<>(2);
        queue.enqueue(1);
        queue.enqueue(2);

        assertThrows(IllegalStateException.class, () -> queue.enqueue(3));
        assertEquals(2, queue.size());
        assertEquals(1, queue.first());
    }

    @Test
    void enqueueReusesFreedSlotsAfterDequeue() {
        ArrayQueue<Integer> queue = new ArrayQueue<>(3);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertEquals(1, queue.dequeue());
        queue.enqueue(4);

        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());
        assertEquals(4, queue.dequeue());
        assertTrue(queue.isEmpty());
    }
}
