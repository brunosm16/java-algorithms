package dev.recursion;

public class RecursiveAlgorithms {
    public static int factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("'n' must me a positive integer");
        }

        if (n == 0) {
            return 1;
        }

        return n * factorial(n - 1);
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();
        long elapsed = endTime - startTime;
        System.out.println("Measure test execution: " + endTime);
    }
}
