package org.example.math;

public class LnFunc  {
    public static double calculate(double x) {
        if (x <= 0) throw new IllegalArgumentException("ln(x) must be positive");

        double sum = 0, term = (x - 1) / (x + 1);
        double current = term;
        int n = 1;

        while (Math.abs(current) > 1e-10 && n < 100) {
            sum += current / n;
            current *= term * term;
            n += 2;
        }

        return 2 * sum;
    }
}
