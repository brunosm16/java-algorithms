package dev.leetcode;

public class SubtreeOfAnotherTree {
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
     * Depth First Search Solution
     * Time Complexity: O(m * n), where m is the number of nodes in subRoot and n is the number
     * of nodes in root.
     * Space Complexity: O(m + n)
     */
    class Solution {
        public boolean isSubtree(TreeNode root, TreeNode subRoot) {
            if (subRoot == null) {
                return true;
            }

            if (root == null && subRoot != null) {
                return false;
            }

            if (isSameTree(root, subRoot)) {
                return true;
            }

            return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
        }

        private boolean isSameTree(TreeNode root, TreeNode subRoot) {
            if (root == null && subRoot == null) {
                return true;
            }

            if (root != null && subRoot != null && root.val == subRoot.val) {
                return isSameTree(root.left, subRoot.left) && isSameTree(root.right, subRoot.right);
            }

            return false;
        }
    }

    /**
     * Serialization and Pattern Matching
     * Time Complexity: O(m + n)
     * Space Complexity: O(m + n)
     */
    class Solution2 {
        public boolean isSubtree(TreeNode root, TreeNode subRoot) {
            String serialized_subRoot = serialize(subRoot);
            String serialized_Root = serialize(root);

            String combinedTrees = serialized_subRoot + "|" + serialized_Root;

            int[] z_values = z_function(combinedTrees);

            int subRootLength = serialized_subRoot.length();

            for(int i = subRootLength + 1; i < combinedTrees.length(); i++) {
                if(z_values[i] == subRootLength) {
                    return true;
                }
            }

            return false;
        }

        private String serialize(TreeNode root) {
            StringBuilder res = new StringBuilder();

            serialize(root, res);

            return res.toString();
        }

        private void serialize(TreeNode root, StringBuilder res) {
            if (root == null) {
                res.append("$#");
                return;
            }

            res.append("$").append(root.val);

            serialize(root.left, res);
            serialize(root.right, res);
        }

        private int[] z_function(String s) {
            int l = 0;
            int r = 0;
            int n = s.length();

            int[] z = new int[n];

            for (int i = 1; i < n; i++) {
                if (i <= r) {
                    z[i] = Math.min(r - i + 1, z[i - l]);
                }

                while (i + z[i] < n && s.charAt(z[i]) == s.charAt(z[i] + i)) {
                    z[i]++;
                }

                if (i + z[i] - 1 > n) {
                    l = i;
                    r = i + z[i] - 1;
                }
            }

            return z;
        }
    }
}
