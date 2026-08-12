package dev.recursion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RecursiveAlgorithmsTest {
    @Test
    void factorialOfZeroReturnsOne() {
        assertEquals(1, RecursiveAlgorithms.factorial(0));
    }

    @Test
    void factorialOfOneReturnsOne() {
        assertEquals(1, RecursiveAlgorithms.factorial(1));
    }

    @Test
    void factorialReturnsProductOfAllPositiveIntegersUpToInput() {
        assertEquals(120, RecursiveAlgorithms.factorial(5));
        assertEquals(3_628_800, RecursiveAlgorithms.factorial(10));
    }

    @Test
    void factorialRejectsNegativeInput() {
        assertThrows(IllegalArgumentException.class, () -> RecursiveAlgorithms.factorial(-1));
    }
}
