package dev.leetcode;

import java.util.ArrayList;
import java.util.Collections;

public class SquaresOfaSortedArray {
    // Two Pointers
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
}
