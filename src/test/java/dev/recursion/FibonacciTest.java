package dev.recursion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class FibonacciTest {
    @Test
    void returnsZeroAndPreviousValueWhenInputIsZero() {
        assertArrayEquals(new long[]{0, 0}, Fibonacci.fibonacciGood(0));
    }

    @Test
    void returnsOneAndPreviousValueWhenInputIsOne() {
        assertArrayEquals(new long[]{1, 0}, Fibonacci.fibonacciGood(1));
    }

    @Test
    void returnsFibonacciNumberAndPreviousValueForSmallInput() {
        assertArrayEquals(new long[]{5, 3}, Fibonacci.fibonacciGood(5));
    }

    @Test
    void returnsFibonacciNumberAndPreviousValueForLargerInput() {
        assertArrayEquals(new long[]{55, 34}, Fibonacci.fibonacciGood(10));
    }
}
