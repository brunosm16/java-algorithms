package dev.datastructures.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArrayStackTest {
    @Test
    void startsEmpty() {
        ArrayStack<Integer> stack = new ArrayStack<>();

        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
        assertNull(stack.top());
    }

    @Test
    void pushAddsElementToTop() {
        ArrayStack<String> stack = new ArrayStack<>();

        stack.push("first");

        assertFalse(stack.isEmpty());
        assertEquals(1, stack.size());
        assertEquals("first", stack.top());
    }

    @Test
    void topDoesNotRemoveElement() {
        ArrayStack<String> stack = new ArrayStack<>();
        stack.push("first");
        stack.push("second");

        assertEquals("second", stack.top());
        assertEquals("second", stack.top());
        assertEquals(2, stack.size());
    }

    @Test
    void popRemovesElementsInLastInFirstOutOrder() {
        ArrayStack<Integer> stack = new ArrayStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    void popReturnsNullWhenStackIsEmpty() {
        ArrayStack<Integer> stack = new ArrayStack<>();

        assertNull(stack.pop());
    }

    @Test
    void constructorRejectsNegativeCapacity() {
        assertThrows(IllegalArgumentException.class, () -> new ArrayStack<>(-1));
    }

    @Test
    void pushRejectsElementsBeyondConfiguredCapacity() {
        ArrayStack<Integer> stack = new ArrayStack<>(2);
        stack.push(1);
        stack.push(2);

        assertThrows(IllegalStateException.class, () -> stack.push(3));
        assertEquals(2, stack.size());
        assertEquals(2, stack.top());
    }
}
