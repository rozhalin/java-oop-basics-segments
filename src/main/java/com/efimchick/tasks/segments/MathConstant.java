package com.efimchick.tasks.segments;

public class MathConstant {
    final static Double EPSILON = 0.000001d;

    public static int doubleCompare(double a, double b) {
        if (Math.abs(a - b) < EPSILON) {
            return 0;
        } else if (a > b) {
            return 1;
        } else {
            return -1;
        }
    }
}
