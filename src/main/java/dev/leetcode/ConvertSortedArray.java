package dev.leetcode;

import java.util.Arrays;

public class ConvertSortedArray {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * Time Complexity: O(nlogn). `logn` comes from creating a copy of each array,
     * the algorithm requires `logn` levels of each array.
     * Space Complexity: O(n)
     */
    public class Solution {
        public TreeNode sortedArrayToBST(int[] nums) {
            if (nums.length == 0) {
                return null;
            }

            int mid = nums.length / 2;

            TreeNode root = new TreeNode(nums[mid]);

            root.left = sortedArrayToBST(Arrays.copyOfRange(nums, 0, mid));
            root.right = sortedArrayToBST(Arrays.copyOfRange(nums, mid + 1, nums.length));

            return root;
        }
    }
}
