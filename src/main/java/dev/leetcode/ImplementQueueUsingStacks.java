package dev.leetcode;

import java.util.Stack;

public class ImplementQueueUsingStacks {
    class MyQueue {
        Stack<Integer> stack;
        Stack<Integer> queue;

        public MyQueue() {
            this.stack = new Stack<>();
            this.queue = new Stack<>();
        }

        /**
         * Pushes element x to the back of queue
         *
         * @param x - element to push
         */
        public void push(int x) {
            this.stack.push(x);
        }

        /**
         * Removes the element from the front of the queue and returns it.
         *
         * @return - Element removed
         */
        public int pop() {
            reverseStackIntoQueue();
            return queue.pop();
        }

        /**
         * Returns the element at the front of the queue.
         *
         * @return - Element at the front of the queue
         */
        public int peek() {
            reverseStackIntoQueue();
            return queue.peek();
        }

        /**
         * @return - Returns true if the queue is empty, false otherwise.
         */
        public boolean empty() {
            return stack.isEmpty() && queue.isEmpty();
        }

        private void reverseStackIntoQueue() {
            if (queue.isEmpty()) {
                while (!stack.isEmpty()) {
                    queue.push(stack.pop());
                }
            }
        }
    }
}
