package dev.recursion;

public class LinearSum {
    public static int linearSum(int[] values, int n) {
        if(n == 0) {
            return 0;
        }
        return linearSum(values, n) + values[n - 1];
    }
}
