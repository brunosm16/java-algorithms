package dev.leetcode;

import java.util.ArrayList;
import java.util.Collections;

public class SquaresOfaSortedArray {
    /**
     * Two Pointers approach
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    class Solution {
        public int[] sortedSquares(int[] nums) {
            int left = 0;
            int right = nums.length - 1;

            ArrayList<Integer> result = new ArrayList<>();

            while (left <= right) {
                if (nums[left] * nums[left] < nums[right] * nums[right]) {
                    result.add(nums[right] * nums[right]);
                    right--;
                } else {
                    result.add(nums[left] * nums[left]);
                    left++;
                }
            }

            Collections.reverse(result);

            return result.stream().mapToInt(i -> i).toArray();
        }
    }

    /**
     * Two Pointers second approach, use a pointerIndex to store the elements in the result
     * array maintaining the expected order.
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    class SecondSolution {
        public int[] sortedSquares(int[] nums) {
            int arrayLength = nums.length;
            int[] result = new int[arrayLength];
            int left = 0;
            int right = arrayLength - 1;
            int pointerIndex = arrayLength - 1;

            while (left <= right) {
                if (nums[left] * nums[left] < nums[right] * nums[right]) {
                    result[pointerIndex] = nums[right] * nums[right];
                    right--;
                } else {
                    result[pointerIndex] = nums[left] * nums[left];
                    left++;
                }
                pointerIndex--;
            }

            return result;
        }
    }
}
