package dev.leetcode;

public class IsPalindrome {
    public class Solution {
        public boolean isPalindrome(int x) {
            if(x < 0 || (x != 0 && x % 10 == 0)) {
                return false;
            }

            int reversedHalf = 0;

            while (x > reversedHalf) {
                int rest = x % 10;

                reversedHalf = reversedHalf * 10 + rest;

                x /= 10;
            }

            return x == reversedHalf || x == reversedHalf / 10;
        }
    }
}
