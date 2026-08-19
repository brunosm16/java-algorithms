package dev.recursion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ReverseArrayTest {
    @Test
    void reversesArrayWithEvenNumberOfElements() {
        int[] values = {1, 2, 3, 4};

        ReverseArray.reverseArray(values, 0, values.length - 1);

        assertArrayEquals(new int[]{4, 3, 2, 1}, values);
    }

    @Test
    void reversesArrayWithOddNumberOfElements() {
        int[] values = {1, 2, 3, 4, 5};

        ReverseArray.reverseArray(values, 0, values.length - 1);

        assertArrayEquals(new int[]{5, 4, 3, 2, 1}, values);
    }

    @Test
    void leavesSingleElementArrayUnchanged() {
        int[] values = {1};

        ReverseArray.reverseArray(values, 0, values.length - 1);

        assertArrayEquals(new int[]{1}, values);
    }

    @Test
    void leavesEmptyArrayUnchanged() {
        int[] values = {};

        ReverseArray.reverseArray(values, 0, values.length - 1);

        assertArrayEquals(new int[]{}, values);
    }

    @Test
    void reversesOnlyRequestedRange() {
        int[] values = {1, 2, 3, 4, 5};

        ReverseArray.reverseArray(values, 1, 3);

        assertArrayEquals(new int[]{1, 4, 3, 2, 5}, values);
    }

    @Test
    void iterativeReversesArrayWithEvenNumberOfElements() {
        int[] values = {1, 2, 3, 4};

        ReverseArray.reverseArrayIterative(values);

        assertArrayEquals(new int[]{4, 3, 2, 1}, values);
    }

    @Test
    void iterativeReversesArrayWithOddNumberOfElements() {
        int[] values = {1, 2, 3, 4, 5};

        ReverseArray.reverseArrayIterative(values);

        assertArrayEquals(new int[]{5, 4, 3, 2, 1}, values);
    }

    @Test
    void iterativeLeavesSingleElementArrayUnchanged() {
        int[] values = {1};

        ReverseArray.reverseArrayIterative(values);

        assertArrayEquals(new int[]{1}, values);
    }

    @Test
    void iterativeLeavesEmptyArrayUnchanged() {
        int[] values = {};

        ReverseArray.reverseArrayIterative(values);

        assertArrayEquals(new int[]{}, values);
    }
}
