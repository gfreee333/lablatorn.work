package ru.ssau.tk._ivan_._lablatorn.work_;

public class ThreeRootFunction implements MathFunction {
    @Override
    public double apply(double x) {
        return Math.pow(x,1.0/3.0);
    }
}
