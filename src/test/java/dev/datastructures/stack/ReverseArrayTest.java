package dev.datastructures.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ReverseArrayTest {
    @Test
    void reverseArrayReversesArrayWithEvenNumberOfElements() {
        Integer[] values = {1, 2, 3, 4};

        ReverseArray.reverseArray(values);

        assertArrayEquals(new Integer[]{4, 3, 2, 1}, values);
    }

    @Test
    void reverseArrayReversesArrayWithOddNumberOfElements() {
        String[] values = {"one", "two", "three"};

        ReverseArray.reverseArray(values);

        assertArrayEquals(new String[]{"three", "two", "one"}, values);
    }

    @Test
    void reverseArrayLeavesSingleElementArrayUnchanged() {
        Integer[] values = {1};

        ReverseArray.reverseArray(values);

        assertArrayEquals(new Integer[]{1}, values);
    }

    @Test
    void reverseArrayLeavesEmptyArrayUnchanged() {
        Integer[] values = {};

        ReverseArray.reverseArray(values);

        assertArrayEquals(new Integer[]{}, values);
    }

    @Test
    void reverseArrayPreservesNullElements() {
        String[] values = {"first", null, "last"};

        ReverseArray.reverseArray(values);

        assertArrayEquals(new String[]{"last", null, "first"}, values);
    }
}
