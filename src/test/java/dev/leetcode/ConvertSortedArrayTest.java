package dev.leetcode;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ConvertSortedArrayTest {
    private final ConvertSortedArray tree = new ConvertSortedArray();

    @Nested
    class SolutionITest {
        private final ConvertSortedArray.Solution solution = tree.new Solution();

        @Test
        void returnsNullForEmptyArray() {
            ConvertSortedArray.TreeNode root = solution.sortedArrayToBST(new int[]{});

            assertNull(root);
        }

        @Test
        void convertsSingleValueArrayToSingleNodeTree() {
            ConvertSortedArray.TreeNode root = solution.sortedArrayToBST(new int[]{7});

            assertEquals(7, root.val);
            assertNull(root.left);
            assertNull(root.right);
        }

        @Test
        void convertsSortedArrayToHeightBalancedBinarySearchTree() {
            ConvertSortedArray.TreeNode root = solution.sortedArrayToBST(new int[]{-10, -3, 0, 5, 9});

            assertEquals(0, root.val);
            assertEquals(-3, root.left.val);
            assertEquals(-10, root.left.left.val);
            assertNull(root.left.right);
            assertEquals(9, root.right.val);
            assertEquals(5, root.right.left.val);
            assertNull(root.right.right);
        }

        @Test
        void convertsEvenSizedSortedArrayToHeightBalancedBinarySearchTree() {
            ConvertSortedArray.TreeNode root = solution.sortedArrayToBST(new int[]{1, 2, 3, 4});

            assertEquals(3, root.val);
            assertEquals(2, root.left.val);
            assertEquals(1, root.left.left.val);
            assertNull(root.left.right);
            assertEquals(4, root.right.val);
            assertNull(root.right.left);
            assertNull(root.right.right);
        }
    }

    @Nested
    class SolutionIITest {
        private final ConvertSortedArray.SolutionII solution = tree.new SolutionII();

        @Test
        void returnsNullForEmptyArray() {
            ConvertSortedArray.TreeNode root = solution.sortedArrayToBST(new int[]{});

            assertNull(root);
        }

        @Test
        void convertsSingleValueArrayToSingleNodeTree() {
            ConvertSortedArray.TreeNode root = solution.sortedArrayToBST(new int[]{7});

            assertEquals(7, root.val);
            assertNull(root.left);
            assertNull(root.right);
        }

        @Test
        void convertsSortedArrayToHeightBalancedBinarySearchTree() {
            ConvertSortedArray.TreeNode root = solution.sortedArrayToBST(new int[]{-10, -3, 0, 5, 9});

            assertEquals(0, root.val);
            assertEquals(-10, root.left.val);
            assertNull(root.left.left);
            assertEquals(-3, root.left.right.val);
            assertEquals(5, root.right.val);
            assertNull(root.right.left);
            assertEquals(9, root.right.right.val);
        }

        @Test
        void convertsEvenSizedSortedArrayToHeightBalancedBinarySearchTree() {
            ConvertSortedArray.TreeNode root = solution.sortedArrayToBST(new int[]{1, 2, 3, 4});

            assertEquals(2, root.val);
            assertEquals(1, root.left.val);
            assertNull(root.left.left);
            assertNull(root.left.right);
            assertEquals(3, root.right.val);
            assertNull(root.right.left);
            assertEquals(4, root.right.right.val);
        }
    }
}
