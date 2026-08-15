package dev.leetcode;

import java.util.HashMap;
import java.util.Stack;

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

    class SolutionIterative {
        /**
         * 1. Push nodes into a stack to simulate postorder traversal
         * 2. Use a dictionary to store the depths of each subtrees
         * 3. For each Node
         * - Traverse left subtree when done, do the same for right subtree.
         * - When both subtrees are done(left, right):
         * - Check if the tree is balanced, if isn't return false, otherwise
         * push the depth of trees into dictionary.
         * -
         * 4. If traversing the tree does not return false the means the tree is balanced.
         */
        public boolean isBalanced(TreeNode root) {
            Stack<TreeNode> nodes = new Stack<>();
            HashMap<TreeNode, Integer> depths = new HashMap<>();

            TreeNode currentNode = root;
            TreeNode lastNode = null;

            while (currentNode != null || !nodes.isEmpty()) {
                if (currentNode != null) {
                    nodes.push(currentNode);
                    currentNode = currentNode.left;
                } else {
                    currentNode = nodes.peek();

                    if (currentNode.right == null || lastNode == currentNode.right) {
                        nodes.pop();

                        int leftSubtree = depths.getOrDefault(currentNode.left, 0);
                        int rightSubtree = depths.getOrDefault(currentNode.right, 0);

                        boolean isUnbalanced = Math.abs(leftSubtree - rightSubtree) > 1;
                        if (isUnbalanced) {
                            return false;
                        }
                        depths.put(currentNode, 1 + Math.max(leftSubtree, rightSubtree));

                        lastNode = currentNode;
                        currentNode = null;
                    } else {
                        currentNode = currentNode.right;
                    }
                }
            }

            return true;
        }
    }

    class SolutionBruteForce {
        public boolean isBalanced(TreeNode root) {
            if(root == null) {
                return true;
            }

            int leftSubtree = height(root.left);
            int rightSubtree = height(root.right);

            if(Math.abs(leftSubtree - rightSubtree) > 1) {
                return false;
            }

            return isBalanced(root.left) && isBalanced(root.right);
        }

        private int height(TreeNode root) {
            if (root == null) {
                return 0;
            }

            return 1 + Math.max(height(root.left), height((root.right)));
        }
    }
}
