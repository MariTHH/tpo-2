package org.example.math;

public class CotFunc {
    public static double calculate(double x) {
        double sin = SinFunc.calculate(x);
        double cos = Math.sqrt(1 - sin * sin) * (Math.abs(x) % (2 * Math.PI) < Math.PI ? 1 : -1);

        if (Math.abs(sin) < 1e-10) throw new ArithmeticException("cot(x) undefined for x = " + x);

        return cos / sin;
    }
}
