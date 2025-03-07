package org.example.math;

public class TrigonometricFuncs {
    public static double sin(double x){
        return SinFunc.calculate(x);
    }
    public static double cot(double x){
        return CotFunc.calculate(x);
    }
    public static double sec(double x){
        return SecFunc.calculate(x);
    }
    public static double csc(double x){
        return CscFunc.calculate(x);
    }
}
