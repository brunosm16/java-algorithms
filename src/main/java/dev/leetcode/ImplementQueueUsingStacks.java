package dev.leetcode;

import java.util.Stack;

/**
 * 232. LeetCode Problem
 * The queue keeps newly pushed elements in stack. When a read operation needs
 * the front element, reverseStack() moves all pending elements into aux,
 * reversing their order so the oldest element is on top. The transfer only
 * happens when aux is empty, so elements are not moved back and forth on every
 * operation.
 *
 * Complexity:
 * - push(int x): O(1) Time Complexity and (1) Space Complexity.
 * - pop(): Time Complexity -> O(n) worst-case and O(1) amortized.
 * - peek(): Time Complexity -> O(n) worst-case and O(1) amortized.
 * - empty(): Time Complexity -> O(1) worst-case and O(1) amortized.
 * - Total Storage: O(n), where n is the number of queued elements.
 */
public class ImplementQueueUsingStacks {
    static class MyQueue {
        private final Stack<Integer> stack;
        private final Stack<Integer> aux;

        public MyQueue() {
            stack = new Stack<>();
            aux = new Stack<>();
        }

        public void push(int x) {
            stack.push(x);
        }

        public int pop() {
            reverseStack();
            return aux.pop();
        }

        public int peek() {
            reverseStack();
            return aux.peek();
        }

        public boolean empty() {
            return stack.isEmpty() && aux.isEmpty();
        }

        private void reverseStack() {
            if(aux.isEmpty()) {
                while(!stack.isEmpty()) {
                    aux.push(stack.pop());
                }
            }
        }
    }
}
