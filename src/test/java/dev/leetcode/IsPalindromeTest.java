package dev.leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsPalindromeTest {
    private final IsPalindrome palindrome = new IsPalindrome();
    private final IsPalindrome.Solution solution = palindrome.new Solution();

    @Test
    void returnsFalseWhenNumberIsNegative() {
        assertFalse(solution.isPalindrome(-121));
    }

    @Test
    void returnsFalseWhenNumberEndsWithZero() {
        assertFalse(solution.isPalindrome(10));
    }

    @Test
    void returnsTrueWhenNumberIsZero() {
        assertTrue(solution.isPalindrome(0));
    }

    @Test
    void returnsTrueWhenNumberHasSingleDigit() {
        assertTrue(solution.isPalindrome(7));
    }

    @Test
    void returnsTrueWhenNumberHasOddLengthPalindrome() {
        assertTrue(solution.isPalindrome(12321));
    }

    @Test
    void returnsTrueWhenNumberHasEvenLengthPalindrome() {
        assertTrue(solution.isPalindrome(1221));
    }

    @Test
    void returnsFalseWhenNumberIsNotPalindrome() {
        assertFalse(solution.isPalindrome(12345));
    }
}
