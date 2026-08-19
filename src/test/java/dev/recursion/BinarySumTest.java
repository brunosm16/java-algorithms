package dev.recursion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinarySumTest {
    @Test
    void returnsZeroWhenLowIsGreaterThanHigh() {
        int[] data = {1, 2, 3};

        assertEquals(0, BinarySum.binarySum(data, 2, 1));
    }

    @Test
    void returnsSingleElementWhenLowEqualsHigh() {
        int[] data = {1, 2, 3};

        assertEquals(2, BinarySum.binarySum(data, 1, 1));
    }

    @Test
    void returnsSumOfAllElements() {
        int[] data = {1, 2, 3, 4, 5};

        assertEquals(15, BinarySum.binarySum(data, 0, data.length - 1));
    }

    @Test
    void returnsSumOfRequestedRange() {
        int[] data = {1, 2, 3, 4, 5};

        assertEquals(9, BinarySum.binarySum(data, 1, 3));
    }

    @Test
    void returnsSumWithNegativeValues() {
        int[] data = {-3, 2, -1, 4};

        assertEquals(2, BinarySum.binarySum(data, 0, data.length - 1));
    }
}
