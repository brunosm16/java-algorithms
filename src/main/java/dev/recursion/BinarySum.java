package dev.recursion;

public class BinarySum {
    public static int binarySum(int[] data, int low, int high) {
        if(low > high) {
            return 0;
        }

        if(low == high) {
            return data[low];
        }

       int mid = (low + high) / 2;
       return binarySum(data, low, mid) + binarySum(data, mid + 1, high);
    }
}
