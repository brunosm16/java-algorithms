package dev.leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SubtreeOfAnotherTreeTest {
    private final SubtreeOfAnotherTree tree = new SubtreeOfAnotherTree();
    private final SubtreeOfAnotherTree.Solution2 solution = tree.new Solution2();

    @Test
    void isSubtreeReturnsTrueWhenSubRootExistsInRoot() {
        /*
         * root = [3,4,5,1,2]
         *
         *       3
         *      / \
         *     4   5
         *    / \
         *   1   2
         */
        SubtreeOfAnotherTree.TreeNode root = tree.new TreeNode(
                3,
                tree.new TreeNode(
                        4,
                        tree.new TreeNode(1),
                        tree.new TreeNode(2)
                ),
                tree.new TreeNode(5)
        );

        /*
         * subRoot = [4,1,2]
         *
         *     4
         *    / \
         *   1   2
         */
        SubtreeOfAnotherTree.TreeNode subRoot = tree.new TreeNode(
                4,
                tree.new TreeNode(1),
                tree.new TreeNode(2)
        );

        boolean isSubtree = solution.isSubtree(root, subRoot);

        assertTrue(isSubtree);
    }

    @Test
    void isSubtreeReturnsFalseWhenSubRootDoesNotExistInRoot() {
        /*
         * root = [3,4,5,1,2]
         *
         *       3
         *      / \
         *     4   5
         *    / \
         *   1   2
         */
        SubtreeOfAnotherTree.TreeNode root = tree.new TreeNode(
                3,
                tree.new TreeNode(
                        4,
                        tree.new TreeNode(1),
                        tree.new TreeNode(2)
                ),
                tree.new TreeNode(5)
        );

        /*
         * subRoot = [4,1,3]
         *
         *     4
         *    / \
         *   1   3
         */
        SubtreeOfAnotherTree.TreeNode subRoot = tree.new TreeNode(
                4,
                tree.new TreeNode(1),
                tree.new TreeNode(3)
        );

        boolean isSubtree = solution.isSubtree(root, subRoot);

        assertFalse(isSubtree);
    }
}
