package ru.ssau.tk.ivan.lablatorn.work.function;

public class SqrFunction implements MathFunction {
    public double apply(double x) {
        x = java.lang.Math.pow(x, 2);
        return x;
    }
}
