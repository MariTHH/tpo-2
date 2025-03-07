package org.example.math;

public class CscFunc {
    public static double calculate(double x) {
        double sin = SinFunc.calculate(x);
        if (Math.abs(sin) < 1e-10) throw new ArithmeticException("csc(x) undefined for x = " + x);
        return 1 / sin;
    }
}
