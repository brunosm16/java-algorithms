package dev.leetcode;

import java.util.HashSet;

public class LinkedListCycle {
    // Definition for singly-linked node.
    private class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public class Solution {
        public boolean hasCycle(ListNode head) {
            ListNode curr = head;
            HashSet<ListNode> seen = new HashSet<>();

            while (curr != null) {
                if (seen.contains(curr)) {
                    return true;
                }

                seen.add(curr);

                curr = curr.next;
            }

            return false;
        }
    }

    public class Solution2 {
        public boolean hasCycle(ListNode head) {
            ListNode slow = head;
            ListNode fast = head;

            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;

                if (fast == slow) {
                    return true;
                }
            }

            return false;
        }
    }
}
