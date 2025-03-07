package org.example.math;

public class FuncSystem {
    public static double calculate(double x) {
        if (x <= 0) {
            return Math.pow(
                    (Math.pow(TrigonometricFuncs.sin(x) + TrigonometricFuncs.sec(x), 3) +TrigonometricFuncs.csc(x))* TrigonometricFuncs.cot(x),
                    2
            ) ;
        } else {
            return Math.pow(
                    Math.pow(LogFunc.log(x, 10), 3) * (LogFunc.ln(x) + LogFunc.log(x, 3)) + LogFunc.ln(x),
                    2
            ) - Math.pow(LogFunc.log(x, 5), 3);
        }
    }
}
