package dev.recursion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LinearSumTest {
    @Test
    void returnsZeroWhenSummingZeroElements() {
        int[] values = {2, 4, 6};

        assertEquals(0, LinearSum.linearSum(values, 0));
    }

    @Test
    void returnsFirstElementWhenSummingOneElement() {
        int[] values = {2, 4, 6};

        assertEquals(2, LinearSum.linearSum(values, 1));
    }

    @Test
    void returnsSumOfFirstNElements() {
        int[] values = {2, 4, 6, 8};

        assertEquals(12, LinearSum.linearSum(values, 3));
    }

    @Test
    void returnsSumOfAllElements() {
        int[] values = {2, 4, 6, 8};

        assertEquals(20, LinearSum.linearSum(values, values.length));
    }
}
