package dev.recursion;

public class BinarySearch {
    public static boolean binarySearch(int[] data, int target) {
        return binarySearch(data, target, 0, data.length - 1);
    }

    public static boolean binarySearchIterative(int[] data, int target) {
        int low = 0;
        int high = data.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (data[mid] == target) {
                return true;
            }

            if (data[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return false;
    }

    private static boolean binarySearch(int[] data, int target, int low, int high) {
        if (low > high) {
            return false;
        }

        int mid = (low + high) / 2;

        if (data[mid] == target) {
            return true;
        }

        if (target < data[mid]) {
            return binarySearch(data, target, low, mid - 1);
        } else {
            return binarySearch(data, target, mid + 1, high);
        }
    }
}
