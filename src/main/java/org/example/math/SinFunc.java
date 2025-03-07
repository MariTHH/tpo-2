package org.example.math;


public class SinFunc {
    public static double calculate(double x) {
        x = x % (2 * Math.PI);

        double term = x, sum = x;
        int n = 1;

        while (Math.abs(term) > 1e-10 && n < 100) {
            term *= -x * x / ((2 * n) * (2 * n + 1));
            sum += term;
            n++;
        }

        return sum;
    }
}

