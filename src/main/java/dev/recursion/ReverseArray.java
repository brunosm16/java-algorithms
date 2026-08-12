package dev.recursion;

public class ReverseArray {
    public static void reverseArray(int[] values, int low, int high) {
        if (low < high) {
            int temp = values[low];
            values[low] = values[high];
            values[high] = temp;
            reverseArray(values, low + 1, high - 1);
        }
    }
}
