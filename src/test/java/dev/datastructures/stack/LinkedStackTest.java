package dev.datastructures.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LinkedStackTest {
    @Test
    void startsEmpty() {
        LinkedStack<Integer> stack = new LinkedStack<>();

        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
        assertNull(stack.top());
    }

    @Test
    void pushAddsElementToTop() {
        LinkedStack<String> stack = new LinkedStack<>();

        stack.push("first");

        assertFalse(stack.isEmpty());
        assertEquals(1, stack.size());
        assertEquals("first", stack.top());
    }

    @Test
    void topDoesNotRemoveElement() {
        LinkedStack<String> stack = new LinkedStack<>();
        stack.push("first");
        stack.push("second");

        assertEquals("second", stack.top());
        assertEquals("second", stack.top());
        assertEquals(2, stack.size());
    }

    @Test
    void popRemovesElementsInLastInFirstOutOrder() {
        LinkedStack<Integer> stack = new LinkedStack<>();
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
        LinkedStack<Integer> stack = new LinkedStack<>();

        assertNull(stack.pop());
    }
}
