package dev.recursion;

public class Power {
    public static double powerOf(double x, int n) {
        if (x == 1) {
            return 1;
        }
        return x * powerOf(x, n - 1);
    }

    public static double powerOfSquare(double x, int n) {
        if(x == 1) {
            return 1;
        } else {
            double partial = powerOfSquare(x, n / 2);
            double result = partial * partial;

            if(result % 2 == 1) {
                result *= x;
            }

            return result;
        }
    }
}
