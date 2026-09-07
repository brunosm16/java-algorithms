package dev.leetcode;

import java.util.Arrays;
import java.util.Stack;

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

    /**
     * Use Binary Search algorithm to divide array in left and right, building a
     * BST from the two divided arrays.
     * Time Complexity: O(n)
     * Space Complexity:
     * - O(n): space for output.
     * - O(logn): space for recursion stack.
     */
    public class SolutionII {
        public TreeNode sortedArrayToBST(int[] nums) {
            return binarySearchBuilder(nums, 0, nums.length - 1);
        }

        /**
         * Builds an BST by using binary-search algorithm, using two pointers
         * left and right to divide array in two considering the middle of array.
         * Left-array represents left subtree and Right-array represents right subtree.
         *
         * @return - BST transformed from a sorted array.
         */
        private TreeNode binarySearchBuilder(int[] nums, int left, int right) {
            // Already searched through all array elements
            if (left > right) {
                return null;
            }

            int mid = (left + right) / 2;

            TreeNode root = new TreeNode(nums[mid]);

            root.left = binarySearchBuilder(nums, left, mid - 1);
            root.right = binarySearchBuilder(nums, mid + 1, right);

            return root;
        }
    }

    /**
     * Use Binary Search algorithm to divide array in left and right, building a
     * BST from the two divided arrays.
     * <p>
     * This algorithm uses DFS Iterative approach by using a stack data structure
     * to simulate recursion stack.
     * Time Complexity: O(n)
     * Space Complexity:
     * - O(n): space for output.
     * - O(logn): space for the stack.
     */
    public class SolutionIII {
        public TreeNode sortedArrayToBST(int[] nums) {
            if (nums.length == 0) {
                return null;
            }

            Stack<int[]> ranges = new Stack<>();
            ranges.push(new int[]{0, nums.length - 1});

            TreeNode res = new TreeNode(0);

            Stack<TreeNode> nodeTreeStack = new Stack<>();
            nodeTreeStack.push(res);

            while (!ranges.isEmpty()) {
                int[] currentRanges = ranges.pop();

                int leftRange = currentRanges[0];
                int rightRange = currentRanges[1];
                int mid = (leftRange + rightRange) / 2;

                TreeNode currentNode = nodeTreeStack.pop();

                currentNode.val = nums[mid];

                if (leftRange <= mid - 1) {
                    currentNode.left = new TreeNode(0);
                    nodeTreeStack.push(currentNode.left);
                    ranges.push(new int[]{leftRange, mid - 1});
                }

                if (rightRange >= mid + 1) {
                    currentNode.right = new TreeNode(0);
                    nodeTreeStack.push(currentNode.right);
                    ranges.push(new int[]{mid + 1, rightRange});
                }
            }

            return res;
        }
    }
}
