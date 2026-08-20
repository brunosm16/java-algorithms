package dev.datastructures.queue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JosephusAlgorithmTest {
    @Test
    void returnsNullWhenQueueIsEmpty() {
        CircularLinkedQueue<String> queue = new CircularLinkedQueue<>();

        assertNull(JosephusAlgorithm.josephus(queue, 3));
        assertTrue(queue.isEmpty());
    }

    @Test
    void returnsOnlyElementWhenQueueHasOneElement() {
        CircularLinkedQueue<String> queue = new CircularLinkedQueue<>();
        queue.enqueue("Alice");

        assertEquals("Alice", JosephusAlgorithm.josephus(queue, 3));
        assertTrue(queue.isEmpty());
    }

    @Test
    void returnsWinnerForKnownJosephusSequence() {
        CircularLinkedQueue<String> queue = new CircularLinkedQueue<>();
        queue.enqueue("Alice");
        queue.enqueue("Bob");
        queue.enqueue("Cindy");
        queue.enqueue("Doug");
        queue.enqueue("Ed");
        queue.enqueue("Fred");

        assertEquals("Alice", JosephusAlgorithm.josephus(queue, 3));
        assertTrue(queue.isEmpty());
    }

    @Test
    void eliminationCountOneLeavesLastQueuedElementAsWinner() {
        CircularLinkedQueue<Integer> queue = new CircularLinkedQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);

        assertEquals(4, JosephusAlgorithm.josephus(queue, 1));
        assertTrue(queue.isEmpty());
    }

    @Test
    void rejectsNonPositiveEliminationCount() {
        CircularLinkedQueue<Integer> queue = new CircularLinkedQueue<>();
        queue.enqueue(1);

        assertThrows(IllegalArgumentException.class, () -> JosephusAlgorithm.josephus(queue, 0));
    }
}
