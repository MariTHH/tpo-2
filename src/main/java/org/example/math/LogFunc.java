package org.example.math;

public class LogFunc {
    public static double ln(double x) {
        return LnFunc.calculate(x);
    }
    public static double log(double x, int base) {
        return LogBaseFunc.calculate(x, base);
    }
}