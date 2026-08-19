package dev.recursion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BinarySearchTest {
    @Test
    void returnsTrueWhenTargetIsFirstElement() {
        int[] data = {1, 3, 5, 7, 9};

        assertTrue(BinarySearch.binarySearch(data, 1));
    }

    @Test
    void returnsTrueWhenTargetIsMiddleElement() {
        int[] data = {1, 3, 5, 7, 9};

        assertTrue(BinarySearch.binarySearch(data, 5));
    }

    @Test
    void returnsTrueWhenTargetIsLastElement() {
        int[] data = {1, 3, 5, 7, 9};

        assertTrue(BinarySearch.binarySearch(data, 9));
    }

    @Test
    void returnsFalseWhenTargetIsNotPresent() {
        int[] data = {1, 3, 5, 7, 9};

        assertFalse(BinarySearch.binarySearch(data, 4));
    }

    @Test
    void returnsFalseForEmptyArray() {
        int[] data = {};

        assertFalse(BinarySearch.binarySearch(data, 1));
    }

    @Test
    void iterativeReturnsTrueWhenTargetIsFirstElement() {
        int[] data = {1, 3, 5, 7, 9};

        assertTrue(BinarySearch.binarySearchIterative(data, 1));
    }

    @Test
    void iterativeReturnsTrueWhenTargetIsMiddleElement() {
        int[] data = {1, 3, 5, 7, 9};

        assertTrue(BinarySearch.binarySearchIterative(data, 5));
    }

    @Test
    void iterativeReturnsTrueWhenTargetIsLastElement() {
        int[] data = {1, 3, 5, 7, 9};

        assertTrue(BinarySearch.binarySearchIterative(data, 9));
    }

    @Test
    void iterativeReturnsFalseWhenTargetIsNotPresent() {
        int[] data = {1, 3, 5, 7, 9};

        assertFalse(BinarySearch.binarySearchIterative(data, 4));
    }

    @Test
    void iterativeReturnsFalseForEmptyArray() {
        int[] data = {};

        assertFalse(BinarySearch.binarySearchIterative(data, 1));
    }
}
