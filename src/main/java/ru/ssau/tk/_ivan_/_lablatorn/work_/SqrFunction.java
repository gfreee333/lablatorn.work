package ru.ssau.tk._ivan_._lablatorn.work_;

public class SqrFunction implements MathFunction {
    public double apply(double x) {

        x = java.lang.Math.pow(x, 2);
        return x;
    }
}
