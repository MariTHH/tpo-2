package org.example.math;

public class SecFunc {
    public static double calculate(double x) {
        double sin = SinFunc.calculate(x);
        double cos = Math.sqrt(1 - sin * sin) * (Math.abs(x) % (2 * Math.PI) < Math.PI ? 1 : -1);

        if (Math.abs(cos) < 1e-10) throw new ArithmeticException("sec(x) undefined for x = " + x);

        return 1 / cos;
    }
}
