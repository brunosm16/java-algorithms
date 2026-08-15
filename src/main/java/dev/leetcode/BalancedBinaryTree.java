package dev.leetcode;

public class BalancedBinaryTree {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    public static class TreeNode {
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

    class Solution {
        public boolean isBalanced(TreeNode root) {
            return dfs(root)[0] == 1;
        }

        private int[] dfs(TreeNode root) {
            if (root == null) {
                return new int[]{1, 0};
            }

            int[] leftSubtree = dfs(root.left);
            int[] rightSubtree = dfs(root.right);

            boolean isBalanced = leftSubtree[0] == 1 && rightSubtree[0] == 1 && Math.abs(leftSubtree[1] - rightSubtree[1]) <= 1;

            int height = 1 + Math.max(leftSubtree[1], rightSubtree[1]);

            return new int[]{isBalanced ? 1 : 0, height};
        }
    }
}
