package org.example.math;

public class LogBaseFunc {
    public static double calculate(double x, int base) {
        if (x <= 0) throw new IllegalArgumentException("log(x) undefined for x > 0");
        if (base <= 0 || base == 1) throw new IllegalArgumentException("Base of log must be bigger than 0 and not equals 1");

        return LnFunc.calculate(x) / LnFunc.calculate(base);
    }
}
