package dev.recursion;

public class Fibonacci {
    public static long[] fibonacciGood(int n) {
        if (n <= 1) {
            long[] answer = {n, 0};
            return new long[]{
                    n, 0
            };
        } else {
            long[] temp = fibonacciGood(n - 1);
            return new long[]{temp[0] + temp[1], temp[0]};
        }
    }
}
